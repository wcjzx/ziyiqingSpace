package com.example.service;

import com.example.pojo.Categorylist;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author yaoyuhang
* @description 针对表【categorylist】的数据库操作Service
* @createDate 2024-03-24 13:28:36
*/
public interface CategorylistService extends IService<Categorylist> {

    List<Categorylist> selectAll();

    int addCategory(String name);

    Integer getCategoryIdByName(String cname);
}
