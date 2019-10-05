package com.kexinxin.bean;

public class Exam {
    private Integer examId;

    private String examName;

    private String examDifficulty;

    private String examQuestionType;

    private String examSkill;

    private Integer positionId;

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamDifficulty() {
        return examDifficulty;
    }

    public void setExamDifficulty(String examDifficulty) {
        this.examDifficulty = examDifficulty;
    }

    public String getExamQuestionType() {
        return examQuestionType;
    }

    public void setExamQuestionType(String examQuestionType) {
        this.examQuestionType = examQuestionType;
    }

    public String getExamSkill() {
        return examSkill;
    }

    public void setExamSkill(String examSkill) {
        this.examSkill = examSkill;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}