package com.example.service;

import com.example.pojo.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author yaoyuhang
* @description 针对表【notice】的数据库操作Service
* @createDate 2024-03-24 16:41:29
*/
public interface NoticeService extends IService<Notice> {

    List<Notice> selectAll();
    List<Notice> findAll();

    Notice findById(int id);

    int deleteById(int id);

    int insert(Notice notice);
}
