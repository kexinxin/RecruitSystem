package com.kexinxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kexinxin.dao.CommentDao;
import com.kexinxin.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentDao commentDao;

	public int deleteByPrimaryKey(Integer commentId) {
		return commentDao.deleteByPrimaryKey(commentId);
	}

	public int insert(CommentService record) {
		return commentDao.insert((CommentDao) record);
	}

	public int insertSelective(CommentService record) {
		return commentDao.insertSelective((CommentDao) record);
	}

	public CommentService selectByPrimaryKey(Integer commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(CommentService record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(CommentService record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}