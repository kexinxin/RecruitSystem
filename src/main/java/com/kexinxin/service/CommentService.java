package com.kexinxin.service;


public interface CommentService {
    int deleteByPrimaryKey(Integer commentId);

    int insert(CommentService record);

    int insertSelective(CommentService record);

    CommentService selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(CommentService record);

    int updateByPrimaryKey(CommentService record);
}