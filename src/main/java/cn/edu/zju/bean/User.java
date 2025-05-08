package cn.edu.zju.bean;

import java.sql.Timestamp;

/**
 * 用户实体类，对应数据库中的users表
 */
public class User {
    private Integer id;                // 用户ID，自增主键
    private String username;          // 用户名，唯一
    private String password;          // 加密后的密码
    private Timestamp createdAt;      // 创建时间

    // 无参构造方法（JPA/MyBatis等框架需要）
    public User() {
    }

    // 注册用的构造方法（不含id和createdAt，由数据库自动生成）
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 全字段构造方法
    public User(Integer id, String username, String password, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    // Getter和Setter方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // 重写toString方法便于调试
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                ", createdAt=" + createdAt +
                '}';
    }
}