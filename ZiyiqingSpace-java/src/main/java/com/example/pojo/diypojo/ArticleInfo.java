package com.example.pojo.diypojo;

import com.example.pojo.Article;
import lombok.Data;

import java.util.List;

@Data
public class ArticleInfo  extends Article {
    List<String> TagName;
}
