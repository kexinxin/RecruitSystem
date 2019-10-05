package com.kexinxin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.kexinxin.bean.Question;
import com.kexinxin.bean.Skill;
import com.kexinxin.util.DBUtil;


public class SearchDAOImpl {
	public static List<Skill> getSkillList() {
		List<Skill> skillList = new LinkedList<Skill>();
		String getSkillList = "SELECT * FROM skill";
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(getSkillList);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Skill skill = new Skill();
				skill.setSkillId(resultSet.getInt(1));
				skill.setFatherId(resultSet.getInt(2));
				skill.setSkillName(resultSet.getString(3));
				skillList.add(skill);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return skillList;
	}

	public static List<String> getSkillNameList() {
		List<String> skillNameList = new LinkedList<String>();
		String getSkillList = "SELECT SKILL_NAME FROM skill";
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(getSkillList);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				skillNameList.add(resultSet.getString(1));
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return skillNameList;
	}

	public static List<Question> getSubjectList() {
		List<Question> subjectList = new LinkedList<Question>();

		String getSubjectList = "SELECT * FROM question";

		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(getSubjectList);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Question subject = new Question();

				subject.setQuestionContent(resultSet.getString(6));
				subject.setQuestionId(resultSet.getInt(1));
				subject.setCateName(resultSet.getString(3));
				subject.setSkillId(resultSet.getInt(4));
				subject.setSkillName(resultSet.getString(5));
				
				subjectList.add(subject);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return subjectList;
	}
	
	
	
	public static List<Question> getNetSubjectList(String str) {
		long a = System.currentTimeMillis();
		List<Question> subjectList = new LinkedList<Question>();

		String getSubjectList = "SELECT * FROM question_net where question_title like '%"+str+"%' LIMIT 100";

		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(getSubjectList);
			//pStatement.setString(0, str);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Question subject = new Question();

				subject.setQuestionContent(resultSet.getString(2));
				subject.setQuestionId(resultSet.getInt(1));
				subject.setCateName(resultSet.getString(3));
				subject.setSkillId(resultSet.getInt(4));
				subject.setSkillName(resultSet.getString(5));
				
				subjectList.add(subject);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<100;i++){
			System.out.println(subjectList.get(i).getQuestionContent());
		}
		long b = System.currentTimeMillis();
		System.out.println("耗时:" + (b - a) + " 毫秒");
		return subjectList;
	}
	
	public static List<Question> getClassifySubjectList() {
		List<Question> subjectList = new LinkedList<Question>();

		String getSubjectList = "SELECT * FROM question_classify";

		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(getSubjectList);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Question subject = new Question();

				subject.setQuestionContent(resultSet.getString(6));
				subject.setQuestionId(resultSet.getInt(1));
				subject.setCateName(resultSet.getString(3));
				subject.setSkillId(resultSet.getInt(4));
				subject.setSkillName(resultSet.getString(5));
				
				subjectList.add(subject);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return subjectList;
	}
	
	
	public static void insertIntoStopWord(String values) {
		Connection conn = DBUtil.getConnection();
		String insert = "INSERT INTO stop_word(VALUE) VALUES (?)";
		try {
			PreparedStatement pStatement = conn.prepareStatement(insert);
			pStatement.setString(1, values);
			pStatement.executeUpdate();
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<String> getStopWordList() {
		List<String> stopWord = new LinkedList<String>();

		String getStopWordList = "SELECT VALUE FROM stop_word";
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(getStopWordList);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				String str = resultSet.getString(1);
				stopWord.add(str);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stopWord;
	}

	public static String getSkillNameById(int skillId) {
		String skillName = "";
		Connection conn = DBUtil.getConnection();
		String insert = "SELECT SKILL_NAME FROM skill WHERE SKILL_ID=?";
		try {
			PreparedStatement pStatement = conn.prepareStatement(insert);
			pStatement.setInt(1, skillId);
			ResultSet resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				skillName = resultSet.getString(1);
			}
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return skillName;
	}

	public static int getSkillIdByName(String skillName) {
		int skillId = -1;
		Connection conn = DBUtil.getConnection();
		String insert = "SELECT SKILL_ID FROM skill WHERE SKILL_NAME=?";
		try {
			PreparedStatement pStatement = conn.prepareStatement(insert);
			pStatement.setString(1, skillName);
			ResultSet resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				skillId = resultSet.getInt(1);
			}
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return skillId;
	}

	public static void main(String[] args) {
		// List<Subject> subjectList=getSubjectList();
		// for(Subject subject:subjectList){
		// System.out.println(subject.getContent());
		// System.out.println(subject.getDifficuty());
		// System.out.println(subject.getHits());
		// System.out.println(subject.getSkillID());
		// System.out.println(subject.getSkillName());
		// System.out.println(subject.getTestId());
		// System.out.println(subject.getTypeId());
		// System.out.println(subject.getTypeName());
		// List<String> stopWord=getStopWordList();
		// System.out.println(stopWord.size());
		
		
		// String skillName = getSkillNameById(13);
		// System.out.println(skillName);
		//
		// int skillId = getSkillIdByName("�Ƽ���");
		// System.out.println(skillId);
		//
		// List<String> skillList = getSkillNameList();
		// for (int i = 0; i < skillList.size(); i++) {
		// System.out.println(skillList.get(i));
		// }
		
		List<Question> questionList=getNetSubjectList("java");
		System.out.println(questionList.size());
	}
}
