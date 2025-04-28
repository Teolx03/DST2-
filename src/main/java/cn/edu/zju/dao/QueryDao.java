package cn.edu.zju.dao;

import cn.edu.zju.bean.QueryResultBean;
import cn.edu.zju.dbutils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDao {

    public List<QueryResultBean> findDosingGuidelineByDrugId(String drugId) {
        List<QueryResultBean> results = new ArrayList<>();
        DBUtils.execSQL(connection -> {
            try {
                // 修改 SQL 查询语句，添加 WHERE 子句
                String sql = "SELECT dg.drug_id, dg.recommendation, dg.summary_markdown, d.name " +
                        "FROM dosing_guideline dg " +
                        "JOIN drug d ON dg.drug_id = d.id " +
                        "WHERE dg.drug_id = ? OR d.name LIKE ?"; // 添加 WHERE 子句
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, drugId); // 设置参数
                preparedStatement.setString(2, "%" + drugId + "%");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    // 修复 QueryResultBean 的构造函数调用
                    String id = resultSet.getString("drug_id");
                    String name = resultSet.getString("name");
                    boolean recommendation = resultSet.getBoolean("recommendation"); // 修复为 boolean 类型
                    String summaryMarkdown = resultSet.getString("summary_markdown");
                    results.add(new QueryResultBean(id, name, recommendation, summaryMarkdown));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return results;
    }
}