package com.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demo.dao.UsersMapper;
import com.demo.entity.Users;
import com.demo.entity.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.List;

/**
 * 用户业务类
 */
@Service
@Transactional
public class UsersService {

    @Autowired
    private UsersMapper usersMapper;


    public Page<Users> search(Page<Users> page,Users users) {
        List<Users> list = usersMapper.selectPage(page,getEntityWrapper(users));
        return page.setRecords(list);
    }

    //条件构造器
    private EntityWrapper getEntityWrapper(Users users){
        EntityWrapper entityWrapper = new EntityWrapper();
        if(null != users){
            if(!StringUtils.isEmpty(users.getUsername())){
                entityWrapper.like("username",String.valueOf(users.getUsername()));
            }
            if(!StringUtils.isEmpty(users.getPassword())){
                entityWrapper.like("password",String.valueOf(users.getPassword()));
            }
        }
        return entityWrapper;
    }

    public Result modify(Users users) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("username",String.valueOf(users.getUsername()));
        List oldUser = usersMapper.selectObjs(entityWrapper);
        if(!oldUser.isEmpty()){
            return Result.error("该用户已存在，请重新输入账号");
        }
        usersMapper.updateById(users);
        return Result.success("修改成功");
    }

    public Result add(Users users) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("username",String.valueOf(users.getUsername()));
        List oldUser = usersMapper.selectObjs(entityWrapper);
        if(!oldUser.isEmpty()){
            return Result.error("该用户已存在，请重新输入账号");
        }
        usersMapper.insert(users);
        return Result.success("新增成功");
    }

    public Users findById(Integer id) {
    	Users users = usersMapper.selectById(id);
        return users;
    }

    public void removeById(Integer id) {
        usersMapper.deleteById(id);
    }
}
