package com.kexinxin.service;

import java.util.List;

import com.kexinxin.bean.User;


public interface UserService {

	User selectUserById(int userId);

	User selectByNickName(String nickName);
	
	int deleteUserById(int userId);

	int addUser(User user);

	int addUserSelective(User user);

	int updateUser(User user);

	int updateUserSelective(User user);

	User login(User user);
	
	List<User> getOnePageUsers();
}
