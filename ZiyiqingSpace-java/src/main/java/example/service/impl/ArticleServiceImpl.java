package example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import example.entry.Article;
import example.service.ArticleService;
import example.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author yaoyuhang
* @description 针对表【article】的数据库操作Service实现
* @createDate 2024-04-05 15:07:30
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




