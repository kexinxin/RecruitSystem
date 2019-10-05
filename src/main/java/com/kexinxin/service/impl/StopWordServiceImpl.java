package com.kexinxin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kexinxin.bean.StopWord;
import com.kexinxin.dao.StopWordDao;
import com.kexinxin.service.StopWordService;

@Service
public class StopWordServiceImpl implements StopWordService {

	@Autowired
	StopWordDao stopWordDao;

	public int deleteByPrimaryKey(Integer id) {
		return stopWordDao.deleteByPrimaryKey(id);
	}

	public int insert(StopWord record) {
		return stopWordDao.insert(record);
	}

	public int insertSelective(StopWord record) {
		return stopWordDao.insertSelective(record);
	}

	public StopWord selectByPrimaryKey(Integer id) {
		return stopWordDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(StopWord record) {
		return stopWordDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(StopWord record) {
		return stopWordDao.updateByPrimaryKey(record);
	}

	public List<StopWord> selectAllStopWord() {
		return stopWordDao.selectAllStopWord();
	}

}