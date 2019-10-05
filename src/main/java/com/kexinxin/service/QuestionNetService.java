package com.kexinxin.service;

import java.util.List;

import com.kexinxin.bean.QuestionNet;

public interface QuestionNetService {
    int deleteByPrimaryKey(Integer questionNetId);

    int insert(QuestionNet record);

    int insertSelective(QuestionNet record);

    QuestionNet selectByPrimaryKey(Integer questionNetId);

    int updateByPrimaryKeySelective(QuestionNet record);

    int updateByPrimaryKey(QuestionNet record);
    
    List<QuestionNet> selectAllQuestionNet();
}