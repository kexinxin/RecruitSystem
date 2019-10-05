package com.kexinxin.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kexinxin.bean.Exam;
import com.kexinxin.dao.ExamDao;
import com.kexinxin.service.ExamService;
import com.kexinxin.util.paper.GA;
import com.kexinxin.util.paper.Paper;
import com.kexinxin.util.paper.Population;
import com.kexinxin.vo.RuleBean;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamDao examDao;

	public int deleteByPrimaryKey(Integer examId) {
		return examDao.deleteByPrimaryKey(examId);
	}

	public int insert(Exam record) {
		return examDao.insert(record);
	}

	public int insertSelective(Exam record) {
		return examDao.insertSelective(record);
	}

	public Exam selectByPrimaryKey(Integer examId) {
		return examDao.selectByPrimaryKey(examId);
	}

	public int updateByPrimaryKeySelective(Exam record) {
		return examDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Exam record) {
		return examDao.updateByPrimaryKey(record);
	}

	public Paper getGAPaper(RuleBean ruleBean) {
		Paper resultPaper = null;
		// 迭代计数器
		int count = 0;
		int runCount = 6;
		// 适应度期望值
		double expand = 0.98;

		// 可自己初始化组卷规则rule
		RuleBean examRuleBean = new RuleBean();

		examRuleBean.setSingleNum(ruleBean.getSingleNum());
		examRuleBean.setCompleteNum(ruleBean.getCompleteNum());
		examRuleBean.setDifficulty(ruleBean.getDifficulty());
		examRuleBean.setSubjectiveNum(ruleBean.getSubjectiveNum());
		examRuleBean.setTotalMark(150);
		examRuleBean.setDescrible(ruleBean.getDescrible());
		
		
		List<String> list = new ArrayList<String>();
		String[] skillList = ruleBean.getTotalPointName().split(",");
		for (int i = 0; i < skillList.length; i++) {
			list.add(skillList[i]);
		}

		examRuleBean.setPointIds(list);

		if (examRuleBean != null) {
			// 初始化种群
			Population population = null;
			try {
				population = new Population(20, true, examRuleBean);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("初次适应度  " + population.getFitness().getAdaptationDegree());

			while (count < runCount && population.getFitness().getAdaptationDegree() < expand) {
				count++;
				try {
					population = GA.evolvePopulation(population, examRuleBean);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("第 " + count + " 次进化，适应度为： " + population.getFitness().getAdaptationDegree());
			}
			System.out.println("进化次数： " + count);
			System.out.println(population.getFitness().getAdaptationDegree());
			resultPaper = population.getFitness();
		}

		for (int i = 0; i < resultPaper.getQuestionSize(); i++) {
			System.out.println(resultPaper.getQuestion(i).getType() + ":" + resultPaper.getQuestion(i).getStem());
		}

		return resultPaper;
	}

}
