package com.demo.controller;
import com.baomidou.mybatisplus.plugins.Page;
import com.demo.entity.Admin;
import com.demo.entity.model.Result;
import com.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
/**
 * 管理员接口
 */
@RestController
@RequestMapping("/admin")
@SaCheckLogin
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
    * 根据条件分页查询管理员
    * @param admin
    * @return
    */
    @GetMapping("/search/{current}/{size}")
    @SaCheckRole("ADMIN")
    public Result search(@PathVariable Integer current,@PathVariable Integer size ,Admin admin){
        Page<Admin> page = adminService.search(new Page<Admin>(current, size), admin);
        return Result.success(page);
    }
    


    /**
    * 新增管理员
    * @param admin
    * @return
    */
    @PostMapping("/")
    @SaCheckRole("ADMIN")
    public Result add(@RequestBody Admin admin){
        Result addResult = adminService.add(admin);
        return addResult;
    }

    /**
    * 修改管理员
    * @param admin
    * @return
    */
    @PutMapping("/")
    @SaCheckRole("ADMIN")
    public Result modify(@RequestBody Admin admin){
        Result modifyResult = adminService.modify(admin);
        return modifyResult;
    }
    


    /**
    * 根据id查询管理员
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    @SaCheckRole("ADMIN")
    public Result findById(@PathVariable("id") Integer id){
        Admin admin  = adminService.findById(id);
        return Result.success(admin);
    }

    /**
    * 根据id删除管理员
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @SaCheckRole("ADMIN")
    public Result removeById(@PathVariable("id") Integer id){
        adminService.removeById(id);
        return Result.success();
    }
    

}