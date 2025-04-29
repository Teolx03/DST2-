package cn.edu.zju.dao;
import cn.edu.zju.bean.User;
import cn.edu.zju.dbutils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BaseDao {
    private static final Logger log = LoggerFactory.getLogger(BaseDao.class);

    // 通用方法：按ID检查记录是否存在
    public boolean existsById(String id, String tableName) {
        AtomicBoolean exists = new AtomicBoolean(false);
        DBUtils.execSQL(connection -> {
            try (PreparedStatement ps = connection.prepareStatement(
                    String.format("SELECT 1 FROM %s WHERE id = ?", tableName))) {
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                exists.set(rs.next());
            } catch (SQLException e) {
                log.error("检查ID存在性失败 [table={}, id={}]", tableName, id, e);
                throw new RuntimeException("数据库查询异常", e);
            }
        });
        return exists.get();
    }

    // 新增：检查用户名是否存在
    public boolean existsByUsername(String username) {
        AtomicBoolean exists = new AtomicBoolean(false);
        DBUtils.execSQL(connection -> {
            try (PreparedStatement ps = connection.prepareStatement(
                    "SELECT 1 FROM users WHERE username = ? LIMIT 1")) {
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

    // 新增：通用插入用户方法
    public void insertUser(User user) {
        DBUtils.execSQL(connection -> {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword()); // 注意：应在调用前加密密码
                ps.executeUpdate();
                log.info("用户数据插入成功: {}", user.getUsername());
            } catch (SQLException e) {
                log.error("用户插入失败: {}", user.getUsername(), e);
                throw new RuntimeException("数据库插入失败", e);
            }
        });
    }
}