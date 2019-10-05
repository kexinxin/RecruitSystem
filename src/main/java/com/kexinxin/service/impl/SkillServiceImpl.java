package com.kexinxin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kexinxin.bean.Skill;
import com.kexinxin.dao.SkillDao;
import com.kexinxin.service.SkillService;
import com.kexinxin.vo.PositionResum;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	SkillDao skillDao;

	public int deleteByPrimaryKey(Integer skillId) {
		return skillDao.deleteByPrimaryKey(skillId);
	}

	public int insert(Skill record) {
		return skillDao.insert(record);
	}

	public int insertSelective(Skill record) {
		return skillDao.insertSelective(record);
	}

	public Skill selectByPrimaryKey(Integer skillId) {
		return skillDao.selectByPrimaryKey(skillId);
	}

	public int updateByPrimaryKeySelective(Skill record) {
		return skillDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Skill record) {
		return skillDao.updateByPrimaryKey(record);
	}

	public List<Skill> selectAllSkill() {
		return skillDao.selectAllSkill();
	}

	public List<String> getPositionSkillList(String content) {
		List<Skill> skillList = skillDao.selectAllSkill();
		List<String> skillNameList = new ArrayList<String>();
		for (int i = 0; i < skillList.size(); i++) {
			if (content.contains(skillList.get(i).getSkillName()))
				skillNameList.add(skillList.get(i).getSkillName());
		}
		return skillNameList;
	}

	public List<String> getContentSkillList(String content) {
		List<Skill> skillList = skillDao.selectAllSkill();
		List<String> skillNameList = new ArrayList<String>();
		for (int i = 0; i < skillList.size(); i++) {
			if (content.contains(skillList.get(i).getSkillName())) {
				skillNameList.add(skillList.get(i).getSkillName());
			}
		}
		return skillNameList;
	}

	public String getResumeAndPositionAnazyResult(PositionResum positionResum) {
		String[] resumeList = positionResum.getResume().split(" ");
		String[] positionList = positionResum.getPosition().split(" ");

		String result = "";

		int count = 0;
		for (int i = 0; i < positionList.length; i++) {
			int flag = 0;
			for (int j = 0; j < resumeList.length; j++) {
				if (positionList[i].equals(resumeList[j])) {
					flag = 1;
					count++;
				}
			}
			if (flag == 1) {
				result += " " + positionList[i];
			}
		}
		result = "准确度：" + Double.valueOf(count) / positionList.length + "  匹配结果:" + result;
		return result;
	}
}