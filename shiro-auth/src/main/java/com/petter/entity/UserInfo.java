package com.petter.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * RBAC 是基于角色的访问控制（Role-Based Access Control ）
 * 在 RBAC 中，权限与角色相关联，用户通过成为适当角色的成员而得到这些角色的权限。
 * 这就极大地简化了权限的管理。这样管理都是层级相互依赖的，权限赋予给角色，而把角色又赋予用户，这样的权限设计很清楚，管理起来很方便。
 * @author hongxf
 * @since 2017-02-17 11:19
 */
@Entity
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue
    private Long id; //用户id

    private String username; //账号

    private String name; //名称（昵称或者真实姓名，不同系统不同定义）

    private String password; //密码;

    private String salt; //加密密码的盐

    private byte state; //用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole",
            joinColumns = {@JoinColumn(name = "uid")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roleList; // 一个用户具有多个角色

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", state=" + state +
                '}';
    }

    /**
     * 密码盐
     * @return
     */
    public String getCredentialsSalt(){
        return this.username + this.salt;
    }
}
