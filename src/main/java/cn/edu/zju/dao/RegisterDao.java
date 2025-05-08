package cn.edu.zju.dao;

import cn.edu.zju.bean.User;
import cn.edu.zju.dbutils.DBUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegisterDao extends BaseDao {
    private static final Logger log = LoggerFactory.getLogger(RegisterDao.class);

    public boolean existsByUsername(String username) {
        AtomicBoolean exists = new AtomicBoolean(false);
        DBUtils.execSQL(connection -> {
            String sql = "SELECT 1 FROM users WHERE username = ? LIMIT 1";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                exists.set(rs.next());
            } catch (SQLException e) {
                log.error("用户名查询失败: {}", username, e);
                throw new RuntimeException("数据库查询异常", e);
            }
        });
        return exists.get();
    }

    public void registerUser(User user) {
        if (existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("用户名已存在");
        }

        DBUtils.execSQL(connection -> {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                String hashedPwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                ps.setString(1, user.getUsername());
                ps.setString(2, hashedPwd);
                ps.executeUpdate();
                log.info("用户注册成功: {}", user.getUsername());
            } catch (SQLException e) {
                log.error("用户注册失败: {}", user.getUsername(), e);
                throw new RuntimeException("数据库插入失败", e);
            }
        });
    }

    public boolean validateUser(String username, String password) {
        AtomicBoolean isValid = new AtomicBoolean(false);
        DBUtils.execSQL(connection -> {
            String sql = "SELECT password FROM users WHERE username = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    isValid.set(BCrypt.checkpw(password, rs.getString("password")));
                }
            } catch (SQLException e) {
                log.error("登录验证失败: {}", username, e);
                throw new RuntimeException("数据库查询失败", e);
            }
        });
        return isValid.get();
    }
}