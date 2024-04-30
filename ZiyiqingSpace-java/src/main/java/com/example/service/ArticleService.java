package com.example.service;

import com.example.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.diypojo.ArticleInfo;

import java.util.List;

/**
* @author yaoyuhang
* @description 针对表【article】的数据库操作Service
* @createDate 2024-04-05 15:15:33
*/
public interface ArticleService extends IService<Article> {

    List<String> selectTagByAid(Integer aid);

    List<ArticleInfo> lasterArticle(int i);

    boolean updateLikes(String aid, int likeCount);

    boolean updateCollect(String aid, int collectCount);

    int addArtcileId();
    int lastArticleId();

    Article selectArticleById(int i);

    List<Article> selectArticleByCategory(int categoryId);

    List<Article> searchByTitle(String title);
}
