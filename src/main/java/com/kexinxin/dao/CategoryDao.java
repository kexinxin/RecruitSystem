package com.kexinxin.dao;

import com.kexinxin.bean.Category;

public interface CategoryDao {
    int deleteByPrimaryKey(Integer cateId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer cateId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}