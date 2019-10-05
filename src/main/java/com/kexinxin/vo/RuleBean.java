package com.kexinxin.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 组卷规则Bean
 *
 */
public class RuleBean {
    /**
     * 规则id
     */
    private long id;
    /**
     * 规则对应的考试id
     */
    private long examId;
    /**
     * 试卷总分
     */
    private int totalMark;
    /**
     * 试卷期望难度系数
     */
    private double difficulty;
    /**
     * 单选题数量
     */
    private int singleNum;
    /**
     * 单选题单个分值
     */
    private double singleScore;
    /**
     * 填空题数量
     */
    private int completeNum;
    /**
     * 填空题单个分值
     */
    private double completeScore;
    /**
     * 主观题数量
     */
    private int subjectiveNum;
    /**
     * 主观题单个分值
     */
    private double subjectiveScore;
    
    /**
     * 知识点String的拼接串
     */
    private String totalPointName;
    
    /**
     * 描述用于Lucene试题搜索
     */
    private String describle;
    
    /**
     * 试卷包含的知识点id
     */
    private List<String> pointIds;
    /**
     * 规则创建时间
     */
    private Date createTime;
    
    
    public String getTotalPointName() {
		return totalPointName;
	}

	public void setTotalPointName(String totalPointName) {
		this.totalPointName = totalPointName;
	}

	public String getDescrible() {
		return describle;
	}

	public void setDescrible(String describle) {
		this.describle = describle;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public int getSingleNum() {
        return singleNum;
    }

    public void setSingleNum(int singleNum) {
        this.singleNum = singleNum;
    }

    public double getSingleScore() {
        return singleScore;
    }

    public void setSingleScore(double singleScore) {
        this.singleScore = singleScore;
    }

    public int getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(int completeNum) {
        this.completeNum = completeNum;
    }

    public double getCompleteScore() {
        return completeScore;
    }

    public void setCompleteScore(double completeScore) {
        this.completeScore = completeScore;
    }

    public int getSubjectiveNum() {
        return subjectiveNum;
    }

    public void setSubjectiveNum(int subjectiveNum) {
        this.subjectiveNum = subjectiveNum;
    }

    public double getSubjectiveScore() {
        return subjectiveScore;
    }

    public void setSubjectiveScore(double subjectiveScore) {
        this.subjectiveScore = subjectiveScore;
    }

    public List<String> getPointIds() {
        return pointIds;
    }

	// public void setPointIds(String pointIds) {
	// // 是否是表单传过来的数据
	// if (pointIds.endsWith("#")) {
	// pointIds = pointIds.substring(0, pointIds.lastIndexOf("#"));
	// }
	// // 使用HashSet去重
	// this.pointIds = new ArrayList<>(new
	// HashSet<>(Arrays.asList(pointIds.split("#"))));
	// }
    
    
    
    public Date getCreateTime() {
        return createTime;
    }

    public void setPointIds(List<String> pointIds) {
		this.pointIds = pointIds;
	}

	public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
