package com.example.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pojo.Article;
import com.example.pojo.diypojo.ArticleInfo;
import com.example.service.ArticleService;
import com.example.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author yaoyuhang
* @description 针对表【article】的数据库操作Service实现
* @createDate 2024-04-05 15:15:33
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Override
    public List<String> selectTagByAid(Integer aid) {
        return baseMapper.selectTagByAid(aid);
    }

    @Override
    public List<ArticleInfo> lasterArticle(int i) {
       return baseMapper.lasterArticle(i);
    }

    @Override
    public boolean updateLikes(String aid, int likeCount) {
        return baseMapper.updateLikes(aid,likeCount);
    }

    @Override
    public boolean updateCollect(String aid, int collectCount) {
        return baseMapper.updateCollect(aid,collectCount);
    }

    @Override
    public int addArtcileId() {
        return baseMapper.addArtcileId();
    }

    @Override
    public int lastArticleId() {
        return baseMapper.lastArticleId();
    }

    @Override
    public Article selectArticleById(int i) {
        return baseMapper.selectArticleById(i);
    }

    @Override
    public List<Article> selectArticleByCategory(int categoryId) {
        return baseMapper.selectArticleByCategoryId(categoryId);
    }

    @Override
    public List<Article> searchByTitle(String title) {

            return baseMapper.searchByTitle(title);

    }
    }





