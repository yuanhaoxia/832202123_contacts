package com.demo.controller;
import com.baomidou.mybatisplus.plugins.Page;
import com.demo.entity.Users;
import com.demo.entity.model.Result;
import com.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
/**
 * 用户接口
 */
@RestController
@RequestMapping("/users")
@SaCheckLogin
public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
    * 根据条件分页查询用户
    * @param users
    * @return
    */
    @GetMapping("/search/{current}/{size}")
    @SaCheckRole("ADMIN")
    public Result search(@PathVariable Integer current,@PathVariable Integer size ,Users users){
        Page<Users> page = usersService.search(new Page<Users>(current, size), users);
        return Result.success(page);
    }


    /**
    * 新增用户
    * @param users
    * @return
    */
    @PostMapping("/")
    @SaCheckRole("ADMIN")
    public Result add(@RequestBody Users users){
        Result addResult = usersService.add(users);
        return addResult;
    }

    /**
    * 修改用户
    * @param users
    * @return
    */
    @PutMapping("/")
    @SaCheckRole("ADMIN")
    public Result modify(@RequestBody Users users){
        Result modifyResult = usersService.modify(users);
        return modifyResult;
    }
    


    /**
    * 根据id查询用户
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    @SaCheckRole("ADMIN")
    public Result findById(@PathVariable("id") Integer id){
        Users users  = usersService.findById(id);
        return Result.success(users);
    }

    /**
    * 根据id删除用户
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @SaCheckRole("ADMIN")
    public Result removeById(@PathVariable("id") Integer id){
        usersService.removeById(id);
        return Result.success();
    }
    


}