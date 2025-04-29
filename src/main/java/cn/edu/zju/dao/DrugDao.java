package cn.edu.zju.dao;

import cn.edu.zju.bean.Drug;
import cn.edu.zju.dbutils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugDao extends BaseDao {

    private static final Logger log = LoggerFactory.getLogger(DrugDao.class);

    public boolean existsById(String id) {
        return super.existsById(id, "drug");
    }

    public void saveDrug(Drug drug) {
        DBUtils.execSQL(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into drug (id, name, obj_cls, biomarker, drug_url) values    (?,?,?,?,?)");
                preparedStatement.setString(1, drug.getId());
                preparedStatement.setString(2, drug.getName());
                preparedStatement.setString(3, drug.getObjCls());
                preparedStatement.setBoolean(4, drug.isBiomarker());
                preparedStatement.setString(5, drug.getDrugUrl());
                preparedStatement.execute();
            } catch (SQLException e) {
                log.info("", e);
            }
        });

    }

    public List<Drug> findAll() {
        List<Drug> drugs = new ArrayList<>();
        DBUtils.execSQL(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT d.id, d.name, d.obj_cls, d.drug_url, d.biomarker, " +
                                "GROUP_CONCAT(DISTINCT dl.id) AS drug_label_ids, " +
                                "GROUP_CONCAT(DISTINCT dg.id) AS dosing_guideline_ids " +
                                "FROM drug d " +
                                "LEFT JOIN drug_label dl ON d.id = dl.drug_id " +
                                "LEFT JOIN dosing_guideline dg ON d.id = dg.drug_id " +
                                "GROUP BY d.id, d.name, d.obj_cls, d.drug_url, d.biomarker"
                );

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String objCls = resultSet.getString("obj_cls");
                    String drugUrl = resultSet.getString("drug_url");
                    boolean biomarker = resultSet.getBoolean("biomarker");

                    // New fields (comma-separated values from group_concat)
                    String drugLabelIds = resultSet.getString("drug_label_ids");  // e.g. "PA1001,PA1002"
                    String dosingGuidelineIds = resultSet.getString("dosing_guideline_ids"); // e.g. "DG2001"

                    // Construct Drug object
                    Drug drug = new Drug(id, name, biomarker, drugUrl, objCls);
                    drug.setDrugLabelId(drugLabelIds);             // Set new info
                    drug.setDosingGuidelineId(dosingGuidelineIds); // Set new info

                    drugs.add(drug);
                }

            } catch (SQLException e) {
                log.info("", e);
            }
        });
        return drugs;
    }

}