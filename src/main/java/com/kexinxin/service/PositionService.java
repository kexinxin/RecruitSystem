package com.kexinxin.service;

import java.util.List;

import com.kexinxin.bean.Position;

public interface PositionService {
    int deleteByPrimaryKey(Integer positionId);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer positionId);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
    
    List<Position> selectAllPosition();
}