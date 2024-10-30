package com.demo.controller;

import com.demo.entity.Admin;
import com.demo.entity.Users;
import com.demo.entity.dto.LoginDTO;
import com.demo.entity.model.Result;
import com.demo.service.AdminService;
import com.demo.service.UsersService;
import com.demo.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import cn.dev33.satoken.stp.StpUtil;


/**
 * 系统基础 接口
 */
@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private SystemService systemService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private UsersService usersService;


    /**
     * 登录
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody LoginDTO loginDTO,HttpServletRequest request) {
        return systemService.login(loginDTO,request);
    }

    /**
     * 登录
     * @return
     */
    @PostMapping("register")
    public Result register(@RequestBody LoginDTO loginDTO,HttpServletRequest request) {
        return systemService.register(loginDTO,request);
    }

    /**
     * 根据token获取用户信息
     * @param request
     * @return
     */
    @GetMapping("getInfoByToken")
    public Result getUserInfoByToken(HttpServletRequest request) {
        Integer userId = StpUtil.getLoginIdAsInt();
        String role = StpUtil.getExtra("role").toString();
        if ("ADMIN".equals(role)) {//管理员
            Admin admin = adminService.findById(userId);
            Map map = new HashMap();
            map.put("role", role);
            map.put("user", admin);
            return Result.success("获取用户信息成功",map);
        }else if ("USER".equals(role)) {//用户
            Users user = usersService.findById(userId);
            Map map = new HashMap();
            map.put("role", role);
            map.put("user", user);
            return Result.success("获取用户信息成功",map);
        }
        return Result.error("查询用户信息失败");
    }

}
