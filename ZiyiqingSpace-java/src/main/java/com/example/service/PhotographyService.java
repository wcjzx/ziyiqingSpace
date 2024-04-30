package com.example.service;

import com.example.pojo.Photography;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.diypojo.PhotographyWithTime;

import java.util.List;

/**
* @author yaoyuhang
* @description 针对表【photography】的数据库操作Service
* @createDate 2024-04-18 22:58:01
*/
public interface PhotographyService extends IService<Photography> {
   List<PhotographyWithTime> getPhotographiesGroupedByTime();

}
