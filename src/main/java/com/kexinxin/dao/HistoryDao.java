package com.kexinxin.dao;

import com.kexinxin.bean.History;

public interface HistoryDao {
    int deleteByPrimaryKey(Integer historyId);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(Integer historyId);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);
}