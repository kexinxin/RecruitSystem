package com.kexinxin.dao;

import java.util.List;

import com.kexinxin.bean.StopWord;

public interface StopWordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(StopWord record);

    int insertSelective(StopWord record);

    StopWord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StopWord record);

    int updateByPrimaryKey(StopWord record);
    
    List<StopWord> selectAllStopWord();
}