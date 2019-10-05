package com.kexinxin.service;

import java.util.List;

import com.kexinxin.bean.Question;

public interface QuestionService {
    int deleteByPrimaryKey(Integer questionId);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer questionId);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);
    
    List<Question> selectAllQuestion();
    
    List<Question> selectCollectQuestion();
    
    List<Question> getAllQuestion();
}