package com.kexinxin.dao;

import com.kexinxin.bean.TotalCollect;

public interface TotalCollectDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TotalCollect record);

    int insertSelective(TotalCollect record);

    TotalCollect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TotalCollect record);

    int updateByPrimaryKey(TotalCollect record);
}