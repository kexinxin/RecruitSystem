package com.kexinxin.service;

import com.kexinxin.bean.SearchPath;

public interface SearchPathService {
    int deleteByPrimaryKey(Integer bnId);

    int insert(SearchPath record);

    int insertSelective(SearchPath record);

    SearchPath selectByPrimaryKey(Integer bnId);

    int updateByPrimaryKeySelective(SearchPath record);

    int updateByPrimaryKey(SearchPath record);
}