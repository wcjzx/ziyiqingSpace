package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pojo.Reviews;
import com.example.service.ReviewsService;
import com.example.mapper.ReviewsMapper;
import org.springframework.stereotype.Service;

/**
* @author yaoyuhang
* @description 针对表【reviews】的数据库操作Service实现
* @createDate 2024-04-05 22:19:01
*/
@Service
public class ReviewsServiceImpl extends ServiceImpl<ReviewsMapper, Reviews>
    implements ReviewsService{

}




