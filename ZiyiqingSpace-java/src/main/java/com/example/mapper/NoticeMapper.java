package com.example.mapper;

import com.example.pojo.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author yaoyuhang
* @description 针对表【notice】的数据库操作Mapper
* @createDate 2024-03-24 16:41:29
* @Entity com.example.pojo.Notice
*/
public interface NoticeMapper extends BaseMapper<Notice> {

    List<Notice> findAll();

    Notice findById(int id);
}




