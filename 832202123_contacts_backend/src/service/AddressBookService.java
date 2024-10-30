package com.demo.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.demo.dao.AddressBookMapper;
import com.demo.entity.AddressBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通讯录信息业务类
 */
@Service
@Transactional
public class AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;


    public Page<AddressBook> search(Page<AddressBook> page,AddressBook addressBook) {
        String role = StpUtil.getExtra("role").toString();
        List<AddressBook> list = addressBookMapper.selectPage(page,getEntityWrapper(addressBook));
        List<AddressBook> filterList = new ArrayList<>();
        if (role.equals("USER")){
            for (AddressBook entity : list) {
                if (entity.getCreateId().equals(Integer.valueOf(String.valueOf(StpUtil.getLoginId())))){
                    filterList.add(entity);
                }
            }
            return page.setRecords(filterList);
        }
        return page.setRecords(list);
    }


    //条件构造器
    private EntityWrapper getEntityWrapper(AddressBook addressBook){
        EntityWrapper entityWrapper = new EntityWrapper();
        if(null != addressBook){
            if(!StringUtils.isEmpty(addressBook.getRealname())){
                entityWrapper.like("realname",String.valueOf(addressBook.getRealname()));
            }
        }
        return entityWrapper;
    }

    public void modify(AddressBook addressBook) {
        addressBookMapper.updateById(addressBook);
    }

    public void add(AddressBook addressBook) {
        addressBook.setCreateTime(new Date());
        addressBookMapper.insert(addressBook);
    }

    public AddressBook findById(Integer id) {
    	AddressBook addressBook = addressBookMapper.selectById(id);
        return addressBook;
    }

    public void removeById(Integer id) {
        addressBookMapper.deleteById(id);
    }
}
