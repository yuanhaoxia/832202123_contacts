package com.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.demo.dao.AdminMapper;
import com.demo.entity.Admin;
import com.demo.dao.UsersMapper;
import com.demo.entity.Users;
import com.demo.entity.dto.LoginDTO;
import com.demo.entity.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;

@Service
public class SystemService  {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UsersMapper usersMapper;


    /**
     * 登录
     * @param query
     * @param request
     * @return
     */
    public Result login(LoginDTO query, HttpServletRequest request) {
        if("ADMIN".equals(query.getRole())){
            List<Admin> list = adminMapper.selectList(new EntityWrapper().eq("username", query.getUsername()).eq("password", query.getPassword()));
            if(list.size() == 0) return Result.error("账号或密码错误");
            StpUtil.login(list.get(0).getId(),SaLoginConfig.setExtra("role", "ADMIN"));
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();//获取 Token  相关参数 
            return Result.success("登录成功",tokenInfo.getTokenValue());//返回 Token值 给前端
        }
        if("USER".equals(query.getRole())){
            List<Users> list = usersMapper.selectList(new EntityWrapper().eq("username", query.getUsername()).eq("password", query.getPassword()));
            if(list.size() == 0) return Result.error("账号或密码错误");
            StpUtil.login(list.get(0).getId(),SaLoginConfig.setExtra("role", "USER"));
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();//获取 Token  相关参数 
            return Result.success("登录成功",tokenInfo.getTokenValue()); //返回 Token值 给前端
        }
        return Result.error("账号或密码错误");
    }

    /**
     * 注册
     * @param loginDTO
     * @param request
     * @return
     */
    public Result register(LoginDTO loginDTO, HttpServletRequest request) {
        Users user = new Users();
        user.setUsername(loginDTO.getUsername());
        Users oldUser = usersMapper.selectOne(user);
        if(oldUser != null){
            return Result.error("该用户已存在，请重新输入账号");
        }
        user.setPassword(loginDTO.getPassword());
        usersMapper.insert(user);
        return Result.success("注册成功");
    }
}