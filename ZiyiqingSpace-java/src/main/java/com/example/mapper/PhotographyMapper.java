package com.example.mapper;

import com.example.pojo.Photography;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author yaoyuhang
* @description 针对表【photography】的数据库操作Mapper
* @createDate 2024-04-18 22:58:01
* @Entity com.example.pojo.Photography
*/
public interface PhotographyMapper extends BaseMapper<Photography> {
    List<Photography> selectAllPhotographies();

}




