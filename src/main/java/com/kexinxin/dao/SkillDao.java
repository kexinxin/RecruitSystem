package com.kexinxin.dao;

import java.util.List;

import com.kexinxin.bean.Skill;

public interface SkillDao {
    int deleteByPrimaryKey(Integer skillId);

    int insert(Skill record);

    int insertSelective(Skill record);

    Skill selectByPrimaryKey(Integer skillId);
    
    List<Skill> selectAllSkill();
    
    int updateByPrimaryKeySelective(Skill record);

    int updateByPrimaryKey(Skill record);
}