package com.example.Controller;

//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.R;
import com.example.pojo.User;
import com.example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public R login(@RequestBody User user) {
        String username = user.getUsername();
//        查询数据库表user中是否存在username
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User usr = userService.getOne(queryWrapper);
        String pwd = usr.getPassword();

        if (usr == null) {
            return R.error("用户不存在");
        }
        if (user.getPassword().equals(usr.getPassword())) {
            return R.success(usr);
        } else {
            return R.error("密码错误");
        }


    }

    @GetMapping("/userinfo")
    public User Userinfo() {
        User user = new User();
        user.setUid(1);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUid, user.getUid());
        User usr = userService.getOne(queryWrapper);
        return usr;
    }
}

