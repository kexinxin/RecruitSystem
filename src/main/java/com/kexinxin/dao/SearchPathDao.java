package com.kexinxin.dao;

import com.kexinxin.bean.SearchPath;

public interface SearchPathDao {
    int deleteByPrimaryKey(Integer bnId);

    int insert(SearchPath record);

    int insertSelective(SearchPath record);

    SearchPath selectByPrimaryKey(Integer bnId);

    int updateByPrimaryKeySelective(SearchPath record);

    int updateByPrimaryKey(SearchPath record);
}