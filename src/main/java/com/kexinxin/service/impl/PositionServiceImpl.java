package com.kexinxin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kexinxin.bean.Position;
import com.kexinxin.dao.PositionDao;
import com.kexinxin.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	PositionDao positionDao;

	public int deleteByPrimaryKey(Integer positionId) {
		return positionDao.deleteByPrimaryKey(positionId);
	}

	public int insert(Position record) {
		return positionDao.insert(record);
	}

	public int insertSelective(Position record) {
		return positionDao.insertSelective(record);
	}

	public Position selectByPrimaryKey(Integer positionId) {
		return positionDao.selectByPrimaryKey(positionId);
	}

	public int updateByPrimaryKeySelective(Position record) {
		return positionDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Position record) {
		return positionDao.updateByPrimaryKey(record);
	}

	public List<Position> selectAllPosition() {
		return positionDao.selectAllPosition();
	}

}