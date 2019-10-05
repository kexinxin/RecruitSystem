package com.kexinxin.service;

import com.kexinxin.bean.Exam;
import com.kexinxin.util.paper.Paper;
import com.kexinxin.vo.RuleBean;

public interface ExamService {
	int deleteByPrimaryKey(Integer examId);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Integer examId);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);
    
    Paper getGAPaper(RuleBean ruleBean);
}
