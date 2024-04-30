package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pojo.Categorylist;
import com.example.service.CategorylistService;
import com.example.mapper.CategorylistMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author yaoyuhang
* @description 针对表【categorylist】的数据库操作Service实现
* @createDate 2024-03-24 13:28:36
*/
@Service
public class CategorylistServiceImpl extends ServiceImpl<CategorylistMapper, Categorylist>
    implements CategorylistService{
    @Override
    public List<Categorylist> selectAll() {
List<Categorylist> categorylist=this.baseMapper.selectList(null);
        return categorylist;
    }

    @Override
    public int addCategory(String Cname) {
       baseMapper.addCategory(Cname);
//       查询最后一组数据的id
        return baseMapper.selectLastId();

    }

    @Override
    public Integer getCategoryIdByName(String cname) {
        return baseMapper.getCategoryIdByName(cname);

    }

}




