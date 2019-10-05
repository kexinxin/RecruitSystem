package com.kexinxin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kexinxin.util.DBUtil;
import com.kexinxin.vo.QuestionBean;
import com.kexinxin.vo.RuleBean;


public class QuestionDAOImpl {

	public static QuestionBean[] getQuestionArray(int type, String substring) {
		// TODO Auto-generated method stub
		String contentType = "";
		if (type == 1) {
			contentType = "单选题";
		} else if (type == 2) {
			contentType = "编程题";
		} else if (type == 3) {
			contentType = "简答题";
		}
		String[] skillIds = substring.split(",");
		for(int i=0;i<skillIds.length;i++){
			skillIds[i]=skillIds[i].replace(" ", "");
		}
		
		List<QuestionBean> questionBeanList = new ArrayList<QuestionBean>();
		String sql = "SELECT CATE_NAME,SKILL_NAME,QUESTION_TITLE,DIFFICULTY,QUESTION_ID FROM QUESTION WHERE CATE_NAME=? AND ";

		if (skillIds.length > 1) {
			sql += "( ";
			for (int i = 0; i < skillIds.length - 1; i++) {
				sql += " SKILL_NAME=? OR ";
			}
			sql += " SKILL_NAME=? )";
		} else {
			sql += " SKILL_NAME=? ";
		}
		//System.out.println(sql);
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, contentType);

			for (int i = 0; i < skillIds.length; i++) {
				pStatement.setString(i + 2, skillIds[i]);
			}

			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				String cateName = resultSet.getString(1);
				String skillName = resultSet.getString(2);
				String question = resultSet.getString(3);
				Integer difficulty = resultSet.getInt(4);
				Integer id = resultSet.getInt(5);

				QuestionBean questionBean = new QuestionBean();
				questionBean.setDifficulty(difficulty);
				questionBean.setId(id);
				questionBean.setPointId(skillName);
				questionBean.setType(cateName);
				questionBean.setStem(question);
				questionBean.setScore(10);
				
				questionBeanList.add(questionBean);
				//System.out.println(question);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println(questionBeanList.toArray().length);
		//
		QuestionBean[] result = new QuestionBean[questionBeanList.size()];
		for (int i = 0; i < questionBeanList.size(); i++) {
			result[i] = questionBeanList.get(i);
		}
		return result;
	}

	public static List<QuestionBean> getQuestionListWithOutSId(QuestionBean tmpQuestion) {
		String sql = "SELECT CATE_NAME,SKILL_NAME,QUESTION_TITLE,DIFFICULTY,QUESTION_ID FROM QUESTION WHERE cate_name=? AND DIFFICULTY=? AND QUESTION_ID!=?";
		List<QuestionBean> questionBeanList = new ArrayList<QuestionBean>();
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, tmpQuestion.getType());
			pStatement.setInt(2, tmpQuestion.getDifficulty());
			pStatement.setInt(3, (int)tmpQuestion.getId());
			
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				String cateName = resultSet.getString(1);
				String skillName = resultSet.getString(2);
				String question = resultSet.getString(3);
				Integer difficulty = resultSet.getInt(4);
				Integer id = resultSet.getInt(5);

				QuestionBean questionBean = new QuestionBean();
				questionBean.setDifficulty(difficulty);
				questionBean.setId(id);
				questionBean.setPointId(skillName);
				questionBean.setType(cateName);
				questionBean.setStem(question);
				questionBean.setScore(10);
				
				
				questionBeanList.add(questionBean);
				//System.out.println(question);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<questionBeanList.size();i++){
			if(questionBeanList.get(i).getId()==tmpQuestion.getId()){
				questionBeanList.remove(i);
			}
		}
		
		return questionBeanList;
	}

	public static void main(String[] args) {
		QuestionDAOImpl questionService = new QuestionDAOImpl();
		
		 QuestionBean questionBean=new QuestionBean();
		
		 questionBean.setType("编程题");
		 questionBean.setDifficulty(8);
		
		 List<QuestionBean> questionBeanList=questionService.getQuestionListWithOutSId(questionBean);
		 System.out.println(questionBeanList.size());
		 
		 
		List<String> list = new ArrayList<String>();
		list.add("SQL");
		list.add("Jquery");
		RuleBean ruleBean = new RuleBean();
		ruleBean.setPointIds(list);
		System.out.println(ruleBean.getPointIds().toString());
		System.out.println(
				ruleBean.getPointIds().toString().substring(1, ruleBean.getPointIds().toString().indexOf("]")));
		QuestionBean[]  result=questionService.getQuestionArray(1,
				ruleBean.getPointIds().toString().substring(1, ruleBean.getPointIds().toString().indexOf("]")));
		
		System.out.println(result.length);
		for(QuestionBean questionbean:result){
			System.out.println(questionbean.getStem());
		}
		
	}

}
