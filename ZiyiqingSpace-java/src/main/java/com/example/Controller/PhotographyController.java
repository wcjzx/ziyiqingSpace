package com.example.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.R;
import com.example.pojo.Article;
import com.example.pojo.Photography;
import com.example.pojo.diypojo.PhotographyWithTime;
import com.example.service.PhotographyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/photography")
@CrossOrigin("*")
public class PhotographyController {
    @Autowired
    private PhotographyService photographyService;
    @Value("${ziyiqingSpace.photographypath}")
    private String photographypath;
//    region 查
    @GetMapping("/getPhotographyByTime")
    public List<PhotographyWithTime> getPhotography(){
        List<PhotographyWithTime> photographyWithTimes =new ArrayList<>();
        return photographyService.getPhotographiesGroupedByTime();
    }
//    endregion
//region 增
@PostMapping("/uploadphotography")
public R<String> upload( MultipartFile file,
                          String currentDate){
    System.out.println(currentDate);
    System.out.println(photographypath);
    //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
    log.info(file.toString());
    //原始文件名
    String originalFilename = file.getOriginalFilename();//abc.jpg
    String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
    //使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
    String fileName = UUID.randomUUID().toString() + suffix;//dfsdfdfd.jpg
    //创建一个目录对象
    File dir = new File(photographypath);
    /*判断当前目录是否存在*/
    if (!dir.exists()) {
        /*目录不存在，需要创建*/
        dir.mkdirs();
    }
    /*将数据存入数据库*/
    Photography photography = new Photography();
    photography.setUrl(fileName);
    photography.setCreatetime(currentDate);
    photographyService.save(photography);
    try {
        /* 将临时文件转存到指定位置*/
        file.transferTo(new File(photographypath + fileName));
    } catch (IOException e) {
        e.printStackTrace();
    }
    return R.success(fileName);
}
    @GetMapping("/PhotographyDownload")
    public void download(String name, HttpServletResponse response) {
        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(photographypath + name));
            //输出流，通过输出流将文件写回浏览器
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    endregion
}
