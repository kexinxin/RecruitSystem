package com.kexinxin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kexinxin.bean.User;
import com.kexinxin.dao.UserDao;
import com.kexinxin.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public User selectUserById(int userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	public int deleteUserById(int userId) {
		return userDao.deleteByPrimaryKey(userId);
	}

	public int addUser(User user) {
		return userDao.insert(user);
	}

	public int addUserSelective(User user) {
		return userDao.insertSelective(user);
	}

	public int updateUser(User user) {
		return userDao.updateByPrimaryKey(user);
	}

	public int updateUserSelective(User user) {
		return userDao.updateByPrimaryKeySelective(user);
	}

	public User login(User user) {
		return userDao.login(user);
	}

	public List<User> getOnePageUsers() {
		return userDao.getOnePageUsers();
	}

	public User selectByNickName(String nickName) {
		return userDao.selectByNickName(nickName);
	}
}
