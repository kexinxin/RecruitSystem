package com.kexinxin.util.paper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.lucene.queryparser.classic.ParseException;

import com.kexinxin.dao.impl.QuestionDAOImpl;
import com.kexinxin.vo.QuestionBean;
import com.kexinxin.vo.RuleBean;

/**
 * 种群，即多套试卷
 *
 */
public class Population {
	/**
	 * 试卷集合
	 */
	private Paper[] papers;

	/**
	 * 初始种群
	 *
	 * @param populationSize
	 *            种群规模
	 * @param initFlag
	 *            初始化标志 true-初始化
	 * @param rule
	 *            规则bean
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public Population(int populationSize, boolean initFlag, RuleBean rule) throws IOException, ParseException {
		papers = new Paper[populationSize];
		if (initFlag) {
			Paper paper;
			Random random = new Random();
			for (int i = 0; i < populationSize; i++) {
				paper = new Paper();
				paper.setId(i + 1);
				// while (paper.getTotalScore() != rule.getTotalMark()) {

				paper.getQuestionList().clear();
				String idString = rule.getPointIds().toString();
				// 单选题
				if (rule.getSingleNum() > 0) {
					generateQuestion(1, random, rule.getSingleNum(), rule.getSingleScore(), idString,
							rule.getDescrible(), "单选题数量不够，组卷失败", paper);
				}

				// 主观题
				if (rule.getSubjectiveNum() > 0) {
					generateQuestion(3, random, rule.getSubjectiveNum(), rule.getSubjectiveScore(), idString,
							rule.getDescrible(), "主观题数量不够，组卷失败", paper);
				}

				// 编程题
				if (rule.getCompleteNum() > 0) {
					generateQuestion(2, random, rule.getCompleteNum(), rule.getCompleteScore(), idString,
							rule.getDescrible(), "填空题数量不够，组卷失败", paper);
				}

				// }
				// 计算试卷知识点覆盖率
				paper.setKpCoverage(rule);
				// 计算试卷适应度
				paper.setAdaptationDegree(rule, Global.KP_WEIGHT, Global.DIFFCULTY_WEIGHt);
				papers[i] = paper;
			}
		}
	}

	private void generateQuestion(int type, Random random, int qustionNum, double score, String idString,
			String describe, String errorMsg, Paper paper) throws IOException, ParseException {
		//QuestionBean[] singleArray = QuestionDAOImpl.getQuestionArray(type,
		//		idString.substring(1, idString.indexOf("]")));
		
		QuestionBean[] singleArray = QuestionBeanLuceneSearch.getQuestionArray(type,
				idString.substring(1, idString.indexOf("]")),describe);
		
		
		
		if (singleArray.length < qustionNum) {
			return;
		}
		QuestionBean tmpQuestion;

		List<String> ids = new ArrayList<String>();
		for (int j = 0; j < qustionNum; j++) {
			int index = random.nextInt(singleArray.length - j);
			// 初始化分数
			// singleArray[index].setScore(score);
			while (ids.contains(singleArray[index].getId() + "")) {
				index = random.nextInt(singleArray.length - j);
			}
			ids.add(singleArray[index].getId() + "");
			paper.addQuestion(singleArray[index]);

			// 保证不会重复添加试题
			// tmpQuestion = singleArray[singleArray.length - j - 1];
			// singleArray[singleArray.length - j - 1] = singleArray[index];
			// singleArray[index] = tmpQuestion;
		}
	}

	/**
	 * 获取种群中最优秀个体
	 *
	 * @return
	 */
	public Paper getFitness() {
		Paper paper = papers[0];
		for (int i = 1; i < papers.length; i++) {
			if (paper.getAdaptationDegree() < papers[i].getAdaptationDegree()) {
				paper = papers[i];
			}
		}
		return paper;
	}

	public Population(int populationSize) {
		papers = new Paper[populationSize];
	}

	/**
	 * 获取种群中某个个体
	 *
	 * @param index
	 * @return
	 */
	public Paper getPaper(int index) {
		return papers[index];
	}

	/**
	 * 设置种群中某个个体
	 *
	 * @param index
	 * @param paper
	 */
	public void setPaper(int index, Paper paper) {
		papers[index] = paper;
	}

	/**
	 * 返回种群规模
	 *
	 * @return
	 */
	public int getLength() {
		return papers.length;
	}

}
