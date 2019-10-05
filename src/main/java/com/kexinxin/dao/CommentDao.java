package com.kexinxin.dao;


public interface CommentDao {
    int deleteByPrimaryKey(Integer commentId);

    int insert(CommentDao record);

    int insertSelective(CommentDao record);

    CommentDao selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(CommentDao record);

    int updateByPrimaryKey(CommentDao record);
}