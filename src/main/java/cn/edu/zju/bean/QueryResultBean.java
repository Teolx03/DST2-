package cn.edu.zju.bean;

public class QueryResultBean {
    private String drugId; // 药物ID
    private String drugName; // 药物名称
    private boolean recommendation; // 是否推荐
    private String summaryMarkdown; // 摘要Markdown格式的摘要

    // 构造方法
    public QueryResultBean() {
    }

    public QueryResultBean(String drugId, String drugName, boolean recommendation, String summaryMarkdown) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.recommendation = recommendation;
        this.summaryMarkdown = summaryMarkdown;
    }

    // Getter 和 Setter 方法
    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public boolean isRecommendation() {
        return recommendation;
    }

    public void setRecommendation(boolean recommendation) {
        this.recommendation = recommendation;
    }

    public String getSummaryMarkdown() {
        return summaryMarkdown;
    }

    public void setSummaryMarkdown(String summaryMarkdown) {
        this.summaryMarkdown = summaryMarkdown;
    }
}