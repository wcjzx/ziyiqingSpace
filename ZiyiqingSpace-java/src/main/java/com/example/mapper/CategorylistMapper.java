package com.example.mapper;

import com.example.pojo.Categorylist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yaoyuhang
* @description 针对表【categorylist】的数据库操作Mapper
* @createDate 2024-03-24 13:28:36
* @Entity com.example.pojo.Categorylist
*/
public interface CategorylistMapper extends BaseMapper<Categorylist> {

    void addCategory( String cname);

    int selectLastId();

    Integer getCategoryIdByName(String cname);
}




