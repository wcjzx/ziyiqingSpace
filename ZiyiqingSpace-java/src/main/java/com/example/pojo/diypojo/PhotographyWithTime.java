package com.example.pojo.diypojo;

import com.example.pojo.Photography;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PhotographyWithTime   {
    private List<Photography> photographyWithTime  = new ArrayList<>();
    private String photographyCreatetime;

}
