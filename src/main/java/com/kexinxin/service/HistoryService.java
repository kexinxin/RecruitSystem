package com.kexinxin.service;

import com.kexinxin.bean.History;

public interface HistoryService {
    int deleteByPrimaryKey(Integer historyId);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(Integer historyId);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);
}