package com.demo.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * 管理员类
 */
@TableName("admin")
public class Admin {

    @TableId("id")
    private Integer id;// ID
	@TableField("username")
    private String username;// 账号

	@TableField("password")
    private String password;// 密码

    public Admin() {
        super();
    }

    public  Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public  String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public  String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}