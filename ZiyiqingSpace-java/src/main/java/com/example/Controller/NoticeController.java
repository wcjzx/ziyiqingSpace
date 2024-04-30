package com.example.Controller;

import com.example.pojo.Notice;
import com.example.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/notice")
@Slf4j
@CrossOrigin("*")
public class NoticeController {
    @Autowired
    private NoticeService noticeServicer;

//    @GetMapping("/notice")
//    public List<Notice> notice() {
//        return noticeServicer.selectAll();
//    }

    /**
     * 查询并返回所有公告信息。
     *
     * @return 返回一个公告信息列表。
     */
    @GetMapping("/findAll")
    public List<Notice> findAll() {
        // 通过noticeServicer查询并返回所有公告
        return noticeServicer.findAll();
    }
    /**
     * 根据ID查找通知信息.
     *
     * @param id 通知的唯一标识符
     * @return 找到的通知对象
     */
    @GetMapping("/findById/{id}")
    public Notice findById(@PathVariable("id") int id) {
        // 通过ID从服务层获取通知
        return noticeServicer.findById(id);
    }
    /**
     * 通过指定ID删除通知信息。
     *
     * @param id 通知的唯一标识符，从URL路径变量中获取。
     * @return 返回删除操作影响的行数。
     */
    @DeleteMapping("/deleteById/{id}")
    public int deleteById(@PathVariable("id") int id) {
        // 调用noticeServicer服务，根据提供的ID删除通知
        return noticeServicer.deleteById(id);
    }
    /**
     * 通过POST请求插入一条通知信息。
     *
     * @param notice 接收前端传来的Notice对象，包含通知的详细信息。
     * @return 返回插入操作影响的行数。
     */
    @PostMapping("/insert")
    public int insert(@RequestBody Notice notice) {
        // 调用服务层方法，将通知对象插入数据库
        return noticeServicer.insert(notice);
    }
    /**
     * 通过Get请求返回最新的公告信息。
     *
     */
    @GetMapping("/latest")
    public Notice latest() {
        // 调用服务层方法，获取最新的公告信息
        int num = noticeServicer.findAll().size()-1;
        int id = noticeServicer.findAll().get(num).getNid();
        return noticeServicer.findById(id);
    }
}