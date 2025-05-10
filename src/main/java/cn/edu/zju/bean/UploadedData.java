package cn.edu.zju.bean;

import java.util.Date;

public class UploadedData {
    private int id; // 数据ID
    private int sampleId; // 样本ID
    private String dataType; // 数据类型
    private String content; // 数据内容
    private Date createdAt; // 创建时间

    // 构造方法
    public UploadedData() {
    }

    public UploadedData(int id, int sampleId, String dataType, String content, Date createdAt) {
        this.id = id;
        this.sampleId = sampleId;
        this.dataType = dataType;
        this.content = content;
        this.createdAt = createdAt;
    }

    // Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}