package com.kexinxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kexinxin.bean.Answer;
import com.kexinxin.dao.AnswerDao;
import com.kexinxin.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService{
	
	@Autowired
	AnswerDao answerDao;
	
	public int deleteByPrimaryKey(Integer answerId) {
		return answerDao.deleteByPrimaryKey(answerId);
	}

	public int insert(Answer record) {
		return answerDao.insert(record);
	}

	public int insertSelective(Answer record) {
		return answerDao.insertSelective(record);
	}

	public Answer selectByPrimaryKey(Integer answerId) {
		return answerDao.selectByPrimaryKey(answerId);
	}

	public int updateByPrimaryKeySelective(Answer record) {
		return answerDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Answer record) {
		return answerDao.updateByPrimaryKey(record);
	}
    
}