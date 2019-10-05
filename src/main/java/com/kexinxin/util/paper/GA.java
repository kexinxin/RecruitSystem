package com.kexinxin.util.paper;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import com.kexinxin.dao.impl.QuestionDAOImpl;
import com.kexinxin.vo.QuestionBean;
import com.kexinxin.vo.RuleBean;


public class GA {

	/**
	 * 交叉概率
	 */
	private static final double uniformRate = 0.5;
	/**
	 * 变异概率
	 */
	private static final double mutationRate = 0.085;
	/**
	 * 精英主义
	 */
	private static final boolean elitism = true;
	/**
	 * 淘汰数组大小
	 */
	private static final int tournamentSize = 5;

	// 进化种群
	public static Population evolvePopulation(Population pop, RuleBean rule) throws IOException, ParseException {
		Population newPopulation = new Population(pop.getLength());
		int elitismOffset;
		// 精英主义
		if (elitism) {
			elitismOffset = 1;
			// 保留上一代最优秀个体
			Paper fitness = pop.getFitness();
			fitness.setId(0);
			newPopulation.setPaper(0, fitness);
		}
		// 种群交叉操作，从当前的种群pop来创建下一代种群newPopulation
		for (int i = elitismOffset; i < newPopulation.getLength(); i++) {
			// 较优选择parent
			Paper parent1 = select(pop);
			Paper parent2 = select(pop);
			while (parent2.getId() == parent1.getId()) {
				parent2 = select(pop);
			}
			// 交叉
			// Paper child = crossover(parent1, parent2, rule);
			
			Paper child = crossover(parent1, parent2, rule);

			child.setId(i);
			newPopulation.setPaper(i, child);
		}
		// 种群变异操作
		Paper tmpPaper;
		for (int i = elitismOffset; i < newPopulation.getLength(); i++) {
			tmpPaper = newPopulation.getPaper(i);
			mutate(tmpPaper,rule.getDescrible());
			// 计算知识点覆盖率与适应度
			tmpPaper.setKpCoverage(rule);
			tmpPaper.setAdaptationDegree(rule, Global.KP_WEIGHT, Global.DIFFCULTY_WEIGHt);
		}
		return newPopulation;
	}

	public static Paper crossover(Paper parent1, Paper parent2, RuleBean rule) {
		sort(parent1,rule);
		sort(parent2,rule);
		Paper child = new Paper();
		for (int i = 0; i < parent1.getQuestionSize(); i++) {
			if (Math.random() <= uniformRate) {
				// newSol.setGene(i, indiv1.getGene(i));
				child.getQuestionList().add(parent1.getQuestion(i));
			} else {
				// newSol.setGene(i, indiv2.getGene(i));
				child.getQuestionList().add(parent2.getQuestion(i));
			}
		}
		return child;
	}

	public static void sort(Paper parent, RuleBean rule) {
		// 单选题
		List<QuestionBean> questionList=parent.getQuestionList();
		if (rule.getSingleNum() > 0) {
			for(int i=0;i<rule.getSingleNum()-1;i++){
				 if(questionList.get(i).getId()>questionList.get(i+1).getId()){
					 QuestionBean temp=questionList.get(i);
					 questionList.set(i, questionList.get(i+1));
					 questionList.set(i+1, temp);
				 }
			}
		}
		// 主观题
		if (rule.getSubjectiveNum() > 0) {
			for(int i=rule.getSingleNum();i<rule.getSingleNum()+rule.getSubjectiveNum()-1;i++){
				 if(questionList.get(i).getId()>questionList.get(i+1).getId()){
					 QuestionBean temp=questionList.get(i);
					 questionList.set(i, questionList.get(i+1));
					 questionList.set(i+1, temp);
				 }
			}
		}
		// 编程题
		if (rule.getCompleteNum() > 0) {
			for(int i=rule.getSingleNum()+rule.getSubjectiveNum();i<rule.getSingleNum()+rule.getSubjectiveNum()+rule.getCompleteNum()-1;i++){
				 if(questionList.get(i).getId()>questionList.get(i+1).getId()){
					 QuestionBean temp=questionList.get(i);
					 questionList.set(i, questionList.get(i+1));
					 questionList.set(i+1, temp);
				 }
			}
		}
		parent.setQuestionList(questionList);
		
	};

//	/**
//	 * 交叉算子
//	 *
//	 * @param parent1
//	 * @param parent2
//	 * @return
//	 */
//	public static Paper crossover(Paper parent1, Paper parent2, RuleBean rule) {
//		Paper child = new Paper(parent1.getQuestionSize());
//		int s1 = (int) (Math.random() * parent1.getQuestionSize());
//		int s2 = (int) (Math.random() * parent1.getQuestionSize());
//
//		// parent1的startPos endPos之间的序列，会被遗传到下一代
//		int startPos = s1 < s2 ? s1 : s2;
//		int endPos = s1 > s2 ? s1 : s2;
//		for (int i = startPos; i < endPos; i++) {
//			child.saveQuestion(i, parent1.getQuestion(i));
//		}
//
//		// 继承parent2中未被child继承的question
//		// 防止出现重复的元素
//		String idString = rule.getPointIds().toString();
//		for (int i = 0; i < startPos; i++) {
//			if (!child.containsQuestion(parent2.getQuestion(i))) {
//				child.saveQuestion(i, parent2.getQuestion(i));
//			} else {
//				int type = getTypeByIndex(i, rule);
//				// getQuestionArray()用来选择指定类型和知识点的试题数组
//				QuestionBean[] singleArray = QuestionService.getQuestionArray(type,
//						idString.substring(1, idString.indexOf("]")));
//				child.saveQuestion(i, singleArray[(int) (Math.random() * singleArray.length)]);
//			}
//		}
//		for (int i = endPos; i < parent2.getQuestionSize(); i++) {
//			if (!child.containsQuestion(parent2.getQuestion(i))) {
//				child.saveQuestion(i, parent2.getQuestion(i));
//			} else {
//				int type = getTypeByIndex(i, rule);
//				QuestionBean[] singleArray = QuestionService.getQuestionArray(type,
//						idString.substring(1, idString.indexOf("]")));
//				child.saveQuestion(i, singleArray[(int) (Math.random() * singleArray.length)]);
//			}
//		}
//
//		return child;
//	}

	private static int getTypeByIndex(int index, RuleBean rule) {
		int type = 0;
		// 单选
		if (index < rule.getSingleNum()) {
			type = 1;
		} else if (index < rule.getSingleNum() + rule.getCompleteNum()) {
			// 填空
			type = 2;
		} else {
			// 主观
			type = 3;
		}
		return type;
	}

	/**
	 * 突变算子 每个个体的每个基因都有可能突变
	 *
	 * @param paper
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static void mutate(Paper paper,String describe) throws IOException, ParseException {
		QuestionBean tmpQuestion;
		List<QuestionBean> list;
		int index;
		for (int i = 0; i < paper.getQuestionSize(); i++) {
			if (Math.random() < mutationRate) {
				// 进行突变，第i道
				tmpQuestion = paper.getQuestion(i);
				// 从题库中获取和变异的题目类型一样分数相同的题目（不包含变异题目）
				
				//list = QuestionDAOImpl.getQuestionListWithOutSId(tmpQuestion);
				list=QuestionBeanLuceneSearch.getQuestionListWithOutSId(tmpQuestion, describe);
				
				if (list.size() > 0) {
					// 随机获取一道
					index = (int) (Math.random() * list.size());
					// 设置分数
					list.get(index).setScore(tmpQuestion.getScore());
					paper.saveQuestion(i, list.get(index));
				}
			}
		}
	}

	/**
	 * 选择算子
	 *
	 * @param population
	 */
	private static Paper select(Population population) {
		Population pop = new Population(tournamentSize);
		for (int i = 0; i < tournamentSize; i++) {
			pop.setPaper(i, population.getPaper((int) (Math.random() * population.getLength())));
		}
		return pop.getFitness();
	}
}
