package cn.edu.zju.dao;

import cn.edu.zju.bean.UploadedData;
import cn.edu.zju.dbutils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UploadedDataDao extends BaseDao {

    public void save(int sampleId, String dataType, String content) {
        DBUtils.execSQL(connection -> {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(
                    "insert into uploaded_data (sample_id, data_type, content) values (?, ?, ?)"
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.setInt(1, sampleId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.setString(2, dataType);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.setString(3, content);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<UploadedData> findBySampleId(int sampleId) {
        List<UploadedData> uploadedDataList = new ArrayList<>();
        DBUtils.execSQL(connection -> {
            String sql = "SELECT * FROM uploaded_data WHERE sample_id = ?";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.setInt(1, sampleId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ResultSet resultSet = null;
            try {
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            while (true) {
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                UploadedData data = new UploadedData();
                try {
                    data.setId(resultSet.getInt("id"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    data.setSampleId(resultSet.getInt("sample_id"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    data.setDataType(resultSet.getString("data_type"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    data.setContent(resultSet.getString("content"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    data.setCreatedAt(resultSet.getTimestamp("created_at"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                //test error
                System.out.println("Fetched Data: " + data); // 打印每条记录
                //test error
                uploadedDataList.add(data);

            }
        });
        return uploadedDataList;
    }
}