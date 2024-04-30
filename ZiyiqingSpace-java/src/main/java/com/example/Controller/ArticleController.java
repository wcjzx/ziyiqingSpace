package com.example.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.R;
import com.example.pojo.Article;
import com.example.pojo.Categorylist;
import com.example.pojo.Notice;
import com.example.pojo.diypojo.ArticleCategoryList;
import com.example.pojo.diypojo.ArticleInfo;
import com.example.pojo.request.CollectUpdateRequest;
import com.example.pojo.request.LikeUpdateRequest;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
@CrossOrigin("*")
public class ArticleController {
    @Autowired
    private CategorylistService categorylistService;
    @Autowired
    private NoticeService noticeServer;
    @Autowired
    private RelationarticleandtabService relationarticleandtabService;
    @Autowired
    private TabService tabService;
    @Autowired
    private ArticleService articleService;

    // region 查询请求
    @GetMapping("/category")
    /*分类*/ public List<Categorylist> category() {
//   从数据库表Categorylist中查询出所有分类信息
        List<Categorylist> categorylist = categorylistService.selectAll();
        System.out.println(categorylist);
        return categorylist;
    }

    /*公告*/
    @GetMapping("/notice")
    public List<Notice> notice() {
        List<Notice> notice = noticeServer.selectAll();
        return notice;
    }

    // region 文章
    /*所有文章*/
    @GetMapping("/allArticle")
    public List<ArticleCategoryList> allArticle() {
        // 初始化文章分类列表
        List<ArticleCategoryList> articleCategoryLists = new ArrayList<>();
        // 获取所有分类信息
        List<Categorylist> categorylists = categorylistService.list();
        // 遍历所有分类，获取每个分类下的所有文章
        for (Categorylist categorylist : categorylists) {
            // 查询当前分类下的所有文章
            List<Article> articles = new ArrayList<>();
            LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Article::getCid, categorylist.getCid());
            articles = articleService.list(lambdaQueryWrapper);
            // 初始化文章信息列表
            List<ArticleInfo> articleInfoList = new ArrayList<>();
            // 遍历文章，将每篇文章的主要信息封装成ArticleInfo对象
            for (Article article : articles) {
                ArticleInfo articleInfo = new ArticleInfo();
                articleInfo.setAid(article.getAid());
                articleInfo.setArticlename(article.getArticlename());
                articleInfo.setArticleintro(article.getArticleintro());
                articleInfo.setArticlephoto(article.getArticlephoto());
                articleInfo.setArticlecreatetime(article.getArticlecreatetime());
                articleInfo.setDianzhan(article.getDianzhan());
                articleInfo.setShouchang(article.getShouchang());
                articleInfo.setCid(article.getCid());
                // 查询并设置文章对应的标签名
                articleInfo.setTagName(articleService.selectTagByAid(article.getAid()));
                articleInfoList.add(articleInfo);
            }
            // 将分类名称和其对应的文章信息列表封装成ArticleCategoryList对象
            ArticleCategoryList articleCategoryList = new ArticleCategoryList();
            articleCategoryList.setCategoryName(categorylist.getCategoryname());
            articleCategoryList.setFlavors(articleInfoList);
            articleCategoryLists.add(articleCategoryList);
        }
        return articleCategoryLists;
    }

    /*分类下的文章*/
    @GetMapping("/getArticleByCategoryId")
    public Page getArticleByCategory(@RequestParam("categoryId") int categoryId,@RequestParam String searchQuery, @RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "2") int pageSize) {
        // 从数据库中查询出该分类下的所有文章，并应用分页6

        Page pageInfo = new Page(currentPage, pageSize);
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
         if(categoryId==-1){
             lambdaQueryWrapper.like(Article::getArticlename, searchQuery);
             lambdaQueryWrapper.orderByDesc(Article::getArticlecreatetime);
        }else {
             lambdaQueryWrapper.eq(Article::getCid, categoryId);
             lambdaQueryWrapper.orderByDesc(Article::getArticlecreatetime);
         }
        articleService.page(pageInfo, lambdaQueryWrapper);
        // 返回分页信息
        return pageInfo;
    }
    //    endregion
    @GetMapping("/newArticle")
    public List<ArticleInfo> newArticle() {
        // 获取文章总数，仅用于后续逻辑判断，非必要步骤
        int num = articleService.list().size();
        // 查询最新的9篇文章信息，实际返回数量可能少于6篇，具体由数据库中文章数量决定
        List<ArticleInfo> articleInfos = articleService.lasterArticle(6);

        // 为每篇文章设置对应的标签名
        for (ArticleInfo article : articleInfos) {
            article.setTagName(articleService.selectTagByAid(article.getAid()));
        }
        // 返回经过处理的文章信息列表
        return articleInfos;
    }
    // endregion

//   region 删
    @DeleteMapping("/deleteArticleById")
    public R<String> deleteArticle(@RequestParam("aid") int aid) {
        /*删除文章*/
        boolean remove = articleService.removeById(aid);
        if (remove) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }
//    endregion

    //region 改
    @PutMapping("/updateArticle")
    public R<String> updateArticle(@RequestBody Article article) {
        /*更新文章*/
        boolean update = articleService.updateById(article);
        if (update) {
            return R.success("更新成功");
        } else {
            return R.error("更新失败");
        }
    }
    // endregion
    /*Search*/
    @GetMapping("/search")
    public List<Article> searchByTitle(@RequestParam String title) {
        return articleService.searchByTitle(title);
    }



    /*通过Id查询*/
    @GetMapping("/getArticleById")
    public Article articleById(@RequestParam("aid") int aid) {
        System.out.println(aid);
        // 获取请求参数中的文章ID
        System.out.println(aid);
        // 查询文章信息
        Article article = articleService.selectArticleById(aid);
        // 设置文章标签名
        // 返回文章信息
        return article;
    }

    /*
     * add点赞数，传回数据库*/
    @PutMapping("/updateLikes")
    public ResponseEntity<String> updateLikes(@RequestBody LikeUpdateRequest request) {
        System.out.println(request.getAid());
        System.out.println(request.getLikeCount());
        boolean updated = articleService.updateLikes(request.getAid(), request.getLikeCount());
        // 根据更新结果返回响应
        if (updated) {
            return ResponseEntity.ok("Likes updated successfully");
        } else {
            return ResponseEntity.status(400).body("Failed to update likes");
        }
    }

    /*
     * 添加收藏数，传回数据库*/
    @PutMapping("/updateCollect")
    public ResponseEntity<String> updateCollect(@RequestBody CollectUpdateRequest request) {
        System.out.println(request.getAid());
        System.out.println(request.getCollectCount());
        boolean updated = articleService.updateCollect(request.getAid(), request.getCollectCount());
        // 根据更新结果返回响应
        if (updated) {
            return ResponseEntity.ok("Collect updated successfully");
        } else {
            return ResponseEntity.status(400).body("Failed to update Collect");
        }
    }

    @GetMapping("/addArtcileId")
    public int addArtcileId() {
//        在数据库中新增一篇空文章，实现文章id自增
        articleService.addArtcileId();
        return articleService.lastArticleId();
    }

    /*新增文章*/
    @PostMapping("/add")
    public String addArticle(@RequestBody Article article) {
        System.out.println(article);
        articleService.save(article); // 调用服务层方法来保存文章
        return "添加成功"; // 返回保存后的文章信息
    }

}