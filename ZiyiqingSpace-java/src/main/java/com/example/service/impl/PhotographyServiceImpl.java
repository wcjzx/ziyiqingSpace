package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pojo.Photography;
import com.example.pojo.diypojo.PhotographyWithTime;
import com.example.service.PhotographyService;
import com.example.mapper.PhotographyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author yaoyuhang
* @description 针对表【photography】的数据库操作Service实现
* @createDate 2024-04-18 22:58:01
*/
@Service
public class PhotographyServiceImpl extends ServiceImpl<PhotographyMapper, Photography>
    implements PhotographyService{

    @Autowired
    private PhotographyMapper photographyMapper;
@Override
    public List<PhotographyWithTime> getPhotographiesGroupedByTime() {
        List<Photography> allPhotographies = photographyMapper.selectAllPhotographies();

        Map<String, List<Photography>> groupedPhotographies = allPhotographies.stream()
                .collect(Collectors.groupingBy(p ->  {return p.getCreatetime();}));

        List<PhotographyWithTime> photographyWithTimes = new ArrayList<>();
        for (Map.Entry<String, List<Photography>> entry : groupedPhotographies.entrySet()) {
            PhotographyWithTime pwt = new PhotographyWithTime();
            pwt.setPhotographyCreatetime(entry.getKey());
            pwt.setPhotographyWithTime(entry.getValue().stream()
                    .sorted(Comparator.comparing(Photography::getCreatetime))
                    .collect(Collectors.toList()));
            photographyWithTimes.add(pwt);
        }

        return photographyWithTimes;
    }

}




