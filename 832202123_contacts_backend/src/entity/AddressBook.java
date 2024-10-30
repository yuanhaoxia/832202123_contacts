package com.demo.entity;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * 通讯录信息类
 */
@TableName("address_book")
public class AddressBook {

    @TableId("id")
    private Integer id;// ID

	@TableField("create_time")
    private Date createTime;// 创建时间

    @TableField("realname")
    private String realname;// 姓名

    @TableField("phone")
    private String phone;// 电话

    @TableField("email")
    private String email;// 邮箱

    @TableField("create_id")
    private Integer createId;//创建人



    public AddressBook() {
        super();
    }

    public  Integer getId() {
        return id;
    }

    public  Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }
}