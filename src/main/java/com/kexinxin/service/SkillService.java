package com.kexinxin.service;

import java.util.List;

import com.kexinxin.bean.Skill;
import com.kexinxin.vo.PositionResum;

public interface SkillService {
    int deleteByPrimaryKey(Integer skillId);

    int insert(Skill record);

    int insertSelective(Skill record);

    Skill selectByPrimaryKey(Integer skillId);

    int updateByPrimaryKeySelective(Skill record);

    int updateByPrimaryKey(Skill record);
    
    List<Skill> selectAllSkill();
    
    List<String> getPositionSkillList(String content);
    
    public List<String> getContentSkillList(String content);
    
    String getResumeAndPositionAnazyResult(PositionResum positionResum);
}