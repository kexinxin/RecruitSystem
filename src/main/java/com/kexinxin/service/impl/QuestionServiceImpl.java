package com.kexinxin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kexinxin.bean.Question;
import com.kexinxin.dao.QuestionDao;
import com.kexinxin.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	QuestionDao questionDao;

	public int deleteByPrimaryKey(Integer questionId) {
		return questionDao.deleteByPrimaryKey(questionId);
	}

	public int insert(Question record) {
		return questionDao.insert(record);
	}

	public int insertSelective(Question record) {
		return questionDao.insertSelective(record);
	}

	public Question selectByPrimaryKey(Integer questionId) {
		return questionDao.selectByPrimaryKey(questionId);
	}

	public int updateByPrimaryKeySelective(Question record) {
		return questionDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Question record) {
		return questionDao.updateByPrimaryKey(record);
	}

	public List<Question> selectAllQuestion() {
		return questionDao.selectAllQuestion();
	}

	public List<Question> selectCollectQuestion() {
		return questionDao.selectCollectQuestion();
	}

	public List<Question> getAllQuestion() {
		return questionDao.getAllQuestion();
	}
	
	
}