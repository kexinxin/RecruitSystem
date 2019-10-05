package com.kexinxin.bean;

public class SearchPath {
    private Integer bnId;

    private Integer startQuestionId;

    private Integer endQuestionId;

    private Integer time;

    public Integer getBnId() {
        return bnId;
    }

    public void setBnId(Integer bnId) {
        this.bnId = bnId;
    }

    public Integer getStartQuestionId() {
        return startQuestionId;
    }

    public void setStartQuestionId(Integer startQuestionId) {
        this.startQuestionId = startQuestionId;
    }

    public Integer getEndQuestionId() {
        return endQuestionId;
    }

    public void setEndQuestionId(Integer endQuestionId) {
        this.endQuestionId = endQuestionId;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}