package com.example.pojo.diypojo;

import com.example.pojo.Article;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;



@Data
public class ArticleCategoryList  {

    //菜品对应的口味数据
    private List<ArticleInfo> flavors = new ArrayList<>();
    private String categoryName;
}
