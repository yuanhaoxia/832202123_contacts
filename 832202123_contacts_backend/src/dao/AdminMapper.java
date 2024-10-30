package com.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import com.demo.entity.Admin;

/**
 * 管理员数据库代理类
 */
@Repository
public interface AdminMapper extends BaseMapper<Admin>{

}