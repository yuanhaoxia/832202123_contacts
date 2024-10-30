package com.demo.entity.dto;

/**
 * 用户登录dto
 */
public class LoginDTO {

    private String username;
    private String password;
    private String role; //ADMIN-管理员 USER-用户

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRoleId(String role) {
        this.role = role;
    }
}
