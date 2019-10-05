package com.kexinxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kexinxin.bean.Category;
import com.kexinxin.dao.CategoryDao;
import com.kexinxin.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDao categoryDao;
	
	public int deleteByPrimaryKey(Integer cateId) {
		return categoryDao.deleteByPrimaryKey(cateId);
	}

	public int insert(Category record) {
		return categoryDao.insert(record);
	}

	public int insertSelective(Category record) {
		return categoryDao.insertSelective(record);
	}

	public Category selectByPrimaryKey(Integer cateId) {
		return categoryDao.selectByPrimaryKey(cateId);
	}

	public int updateByPrimaryKeySelective(Category record) {
		return categoryDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Category record) {
		return categoryDao.updateByPrimaryKey(record);
	}
}