package com.example.Controller;

import com.example.service.CategorylistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/category")
@Slf4j
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategorylistService categorylistService;

    @PostMapping("/add")
    public int addCategory(@RequestBody Map<String, String> requestBody) {
        String Cname = requestBody.get("Cname");
        return categorylistService.addCategory(Cname);
    }

    /*根据分类名查询分类id*/
    @GetMapping("/CategoryByName")
    public int getCategoryByName(@RequestParam Map<String, String> requestBody) {
        String Cname = requestBody.get("Cname");
        // 调用服务层方法，通过分类名查询分类ID
        Integer categoryId = categorylistService.getCategoryIdByName(Cname);
        if (categoryId != null) {
            // 如果找到了分类ID，则返回该ID
            return categoryId;
        } else {
            // 如果没有找到，返回一个适当的错误消息
            return 0;
        }
    }
}