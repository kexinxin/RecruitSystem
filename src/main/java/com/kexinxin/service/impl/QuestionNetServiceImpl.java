package com.kexinxin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kexinxin.bean.QuestionNet;
import com.kexinxin.dao.QuestionNetDao;
import com.kexinxin.service.QuestionNetService;

@Service
public class QuestionNetServiceImpl implements QuestionNetService {

	@Autowired
	QuestionNetDao questionNetDao;

	public int deleteByPrimaryKey(Integer questionNetId) {
		return questionNetDao.deleteByPrimaryKey(questionNetId);
	}

	public int insert(QuestionNet record) {
		return questionNetDao.insert(record);
	}

	public int insertSelective(QuestionNet record) {
		return questionNetDao.insertSelective(record);
	}

	public QuestionNet selectByPrimaryKey(Integer questionNetId) {
		return questionNetDao.selectByPrimaryKey(questionNetId);
	}

	public int updateByPrimaryKeySelective(QuestionNet record) {
		return questionNetDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(QuestionNet record) {
		return questionNetDao.updateByPrimaryKey(record);
	}

	public List<QuestionNet> selectAllQuestionNet() {
		return questionNetDao.selectAllQuestionNet();
	}
}