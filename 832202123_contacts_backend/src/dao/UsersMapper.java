package com.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import com.demo.entity.Users;

/**
 * 用户数据库代理类
 */
@Repository
public interface UsersMapper extends BaseMapper<Users>{

}