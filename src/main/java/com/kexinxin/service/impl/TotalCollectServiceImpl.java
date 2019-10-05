package com.kexinxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kexinxin.bean.TotalCollect;
import com.kexinxin.dao.TotalCollectDao;
import com.kexinxin.service.TotalCollectService;

@Service
public class TotalCollectServiceImpl implements TotalCollectService {

	@Autowired
	TotalCollectDao totalCollectDao;

	public int deleteByPrimaryKey(Integer id) {
		return totalCollectDao.deleteByPrimaryKey(id);
	}

	public int insert(TotalCollect record) {
		return totalCollectDao.insert(record);
	}

	public int insertSelective(TotalCollect record) {
		return totalCollectDao.insertSelective(record);
	}

	public TotalCollect selectByPrimaryKey(Integer id) {
		return totalCollectDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(TotalCollect record) {
		return totalCollectDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TotalCollect record) {
		return totalCollectDao.updateByPrimaryKey(record);
	}

}