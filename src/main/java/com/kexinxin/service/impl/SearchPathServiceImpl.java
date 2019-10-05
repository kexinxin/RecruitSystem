package com.kexinxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kexinxin.bean.SearchPath;
import com.kexinxin.dao.SearchPathDao;
import com.kexinxin.service.SearchPathService;

@Service
public class SearchPathServiceImpl implements SearchPathService{
	
	@Autowired
	SearchPathDao searchPathDao;

	public int deleteByPrimaryKey(Integer bnId) {
		return searchPathDao.deleteByPrimaryKey(bnId);
	}

	public int insert(SearchPath record) {
		return searchPathDao.insert(record);
	}

	public int insertSelective(SearchPath record) {
		return searchPathDao.insertSelective(record);
	}

	public SearchPath selectByPrimaryKey(Integer bnId) {
		return searchPathDao.selectByPrimaryKey(bnId);
	}

	public int updateByPrimaryKeySelective(SearchPath record) {
		return searchPathDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(SearchPath record) {
		return searchPathDao.updateByPrimaryKey(record);
	}
}