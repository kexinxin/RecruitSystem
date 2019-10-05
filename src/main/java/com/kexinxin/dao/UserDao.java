package com.kexinxin.dao;

import java.util.List;

import com.kexinxin.bean.User;



public interface UserDao {
	int deleteByPrimaryKey(Integer userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer userId);
	
	User selectByNickName(String nickName);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User login(User user);

	List<User> getOnePageUsers();
}