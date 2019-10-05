package com.kexinxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kexinxin.bean.History;
import com.kexinxin.dao.HistoryDao;
import com.kexinxin.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService{
	
	@Autowired
	HistoryDao historyDao;
	
	public int deleteByPrimaryKey(Integer historyId) {
		return historyDao.deleteByPrimaryKey(historyId);
	}

	public int insert(History record) {
		return historyDao.insert(record);
	}

	public int insertSelective(History record) {
		return historyDao.insertSelective(record);
	}

	public History selectByPrimaryKey(Integer historyId) {
		return historyDao.selectByPrimaryKey(historyId);
	}

	public int updateByPrimaryKeySelective(History record) {
		return historyDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(History record) {
		return historyDao.updateByPrimaryKey(record);
	}
   
}