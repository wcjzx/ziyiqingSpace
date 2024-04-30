package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pojo.Categorylist;
import com.example.pojo.Notice;
import com.example.service.NoticeService;
import com.example.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author yaoyuhang
* @description 针对表【notice】的数据库操作Service实现
* @createDate 2024-03-24 16:41:29
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{

    @Override
    public List<Notice> selectAll(){
        List<Notice> Notice=this.baseMapper.selectList(null);
        return Notice;
    }

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> findAll() {
        return noticeMapper.findAll();
    }

    @Override
    public Notice findById(int id) {
        return noticeMapper.findById(id);
    }

    @Override
    public int deleteById(int id) {
        return noticeMapper.deleteById(id);
    }

    @Override
    public int insert(Notice notice) {
        return noticeMapper.insert(notice);
    }
}




