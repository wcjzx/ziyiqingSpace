package com.example.mapper;

import com.example.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.diypojo.ArticleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author yaoyuhang
* @description 针对表【article】的数据库操作Mapper
* @createDate 2024-04-05 15:15:33
* @Entity com.example.pojo.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {

     boolean updateLikes(String aid, int likeCount) ;

    List<String> selectTagByAid(Integer aid);

    List<ArticleInfo> lasterArticle(int i);

    boolean updateCollect(String aid, int collectCount);

    int addArtcileId();

    int lastArticleId();

    Article selectArticleById(int i);

    List<Article> selectArticleByCategoryId(@Param("categoryId") int categoryId);

    List<Article> searchByTitle(String title);
}




