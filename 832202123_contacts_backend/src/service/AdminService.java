package com.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demo.dao.AdminMapper;
import com.demo.entity.Admin;
import com.demo.entity.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.List;

/**
 * 管理员业务类
 */
@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;


    public Page<Admin> search(Page<Admin> page,Admin admin) {
        List<Admin> list = adminMapper.selectPage(page,getEntityWrapper(admin));
        return page.setRecords(list);
    }

    //条件构造器
    private EntityWrapper getEntityWrapper(Admin admin){
        EntityWrapper entityWrapper = new EntityWrapper();
        if(null != admin){
            if(!StringUtils.isEmpty(admin.getUsername())){
                entityWrapper.like("username",String.valueOf(admin.getUsername()));
            }
            if(!StringUtils.isEmpty(admin.getPassword())){
                entityWrapper.like("password",String.valueOf(admin.getPassword()));
            }
        }
        return entityWrapper;
    }

    public Result modify(Admin admin) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("username",String.valueOf(admin.getUsername()));
        List oldAdmin = adminMapper.selectObjs(entityWrapper);
        if(!oldAdmin.isEmpty()){
            return Result.error("该管理员账号已存在，请重新输入账号");
        }
        adminMapper.updateById(admin);
        return Result.success("修改成功");
    }

    public Result add(Admin admin) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("username",String.valueOf(admin.getUsername()));
        List oldAdmin = adminMapper.selectObjs(entityWrapper);
        if(!oldAdmin.isEmpty()){
            return Result.error("该管理员账号已存在，请重新输入账号");
        }
        adminMapper.insert(admin);
        return Result.success("新增成功");
    }

    public Admin findById(Integer id) {
    	Admin admin = adminMapper.selectById(id);
        return admin;
    }

    public void removeById(Integer id) {
        adminMapper.deleteById(id);
    }
}
