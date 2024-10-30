package com.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import com.demo.entity.AddressBook;

/**
 * 通讯录信息数据库代理类
 */
@Repository
public interface AddressBookMapper extends BaseMapper<AddressBook>{

}