package com.kexinxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kexinxin.bean.ExamQuestion;
import com.kexinxin.dao.ExamQuestionDao;
import com.kexinxin.service.ExamQuestionService;

@Service
public class ExamQuestionServiceImpl implements ExamQuestionService{
	@Autowired
	ExamQuestionDao examQuestionDao;
	
	public int deleteByPrimaryKey(Integer examId) {
		return examQuestionDao.deleteByPrimaryKey(examId);
	}

	public int insert(ExamQuestion record) {
		return examQuestionDao.insert(record);
	}

	public int insertSelective(ExamQuestion record) {
		return examQuestionDao.insertSelective(record);
	}

	public ExamQuestion selectByPrimaryKey(Integer examId) {
		return examQuestionDao.selectByPrimaryKey(examId);
	}

	public int updateByPrimaryKeySelective(ExamQuestion record) {
		return examQuestionDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(ExamQuestion record) {
		return examQuestionDao.updateByPrimaryKey(record);
	}
    
}