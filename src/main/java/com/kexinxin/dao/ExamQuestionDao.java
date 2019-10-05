package com.kexinxin.dao;

import com.kexinxin.bean.ExamQuestion;

public interface ExamQuestionDao {
    int deleteByPrimaryKey(Integer examId);

    int insert(ExamQuestion record);

    int insertSelective(ExamQuestion record);

    ExamQuestion selectByPrimaryKey(Integer examId);

    int updateByPrimaryKeySelective(ExamQuestion record);

    int updateByPrimaryKey(ExamQuestion record);
}