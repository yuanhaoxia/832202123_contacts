package com.demo.controller;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.demo.entity.AddressBook;
import com.demo.entity.model.Result;
import com.demo.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
/**
 * 通讯录信息接口
 */
@RestController
@RequestMapping("/addressBook")
@SaCheckLogin
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;


    /**
    * 根据条件分页查询通讯录信息
    * @param addressBook
    * @return
    */
    @GetMapping("/search/{current}/{size}")
    public Result search(@PathVariable Integer current,@PathVariable Integer size ,AddressBook addressBook){
        Page<AddressBook> page = addressBookService.search(new Page<AddressBook>(current, size), addressBook);
        return Result.success(page);
    }


    /**
    * 新增通讯录信息
    * @param addressBook
    * @return
    */
    @PostMapping("/")
    public Result add(@RequestBody AddressBook addressBook){
        Object loginId = StpUtil.getLoginId();
        addressBook.setCreateId(Integer.valueOf(loginId.toString()));
        addressBookService.add(addressBook);
        return Result.success();
    }

    /**
    * 修改通讯录信息
    * @param addressBook
    * @return
    */
    @PutMapping("/")
    public Result modify(@RequestBody AddressBook addressBook){
        addressBookService.modify(addressBook);
        return Result.success();
    }


    /**
    * 根据id查询通讯录信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Integer id){
        AddressBook addressBook  = addressBookService.findById(id);
        return Result.success(addressBook);
    }

    /**
    * 根据id删除通讯录信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable("id") Integer id){
        addressBookService.removeById(id);
        return Result.success();
    }
    


}