package com.kexinxin.util.crawler;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kexinxin.util.DBUtil;


public class CrawlerService {

	public void saveQuestion(String question, String type, String difficulty) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pStatement = null;
		try {
			String insert = "INSERT INTO  questionfromnet(QUESTION,TYPE,DIFFICULTY) VALUES (?,?,?)";
			pStatement = conn.prepareStatement(insert);
			pStatement.setString(1, question);
			pStatement.setString(2, type);
			pStatement.setString(3, difficulty);
			pStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closePreparedStatement(pStatement);
		DBUtil.closeConnection(conn);
	}
	
	public void saveProgrammingQuestion(String question, String type, String difficulty,String questionType,String context){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pStatement = null;
		try {
			String insert = "INSERT INTO  questionfromnet(QUESTION,TYPE,DIFFICULTY,QUESTIONTYPE,CONTENT) VALUES (?,?,?,?,?)";
			pStatement = conn.prepareStatement(insert);
			pStatement.setString(1, question);
			pStatement.setString(2, type);
			pStatement.setString(3, difficulty);
			pStatement.setString(4, questionType);
			pStatement.setString(5, context);
			pStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closePreparedStatement(pStatement);
		DBUtil.closeConnection(conn);
	};
	
	public void saveOJProgrammingQuestion(String question, String questionType,String context){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pStatement = null;
		try {
			String insert = "INSERT INTO questionfromnet(QUESTION,QUESTIONTYPE,CONTENT) VALUES (?,?,?)";
			pStatement = conn.prepareStatement(insert);
			pStatement.setString(1, question);
			pStatement.setString(2, questionType);
			pStatement.setString(3, context);
			pStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closePreparedStatement(pStatement);
		DBUtil.closeConnection(conn);
	};
	
	
	public void saveSelectQuestion(String question, String type, String difficulty, String questionType, String A,
			String B, String C, String D) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pStatement = null;
		try {
			String insert = "INSERT INTO questionfromnet(QUESTION,TYPE,DIFFICULTY,QUESTIONTYPE,A,B,C,D) VALUES (?,?,?,?,?,?,?,?)";
			pStatement = conn.prepareStatement(insert);
			pStatement.setString(1, question);
			pStatement.setString(2, type);
			pStatement.setString(3, difficulty);
			pStatement.setString(4, questionType);
			pStatement.setString(5, A);
			pStatement.setString(6, B);
			pStatement.setString(7, C);
			pStatement.setString(8, D);
			pStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closePreparedStatement(pStatement);
		DBUtil.closeConnection(conn);
	}

	public static void main(String[] args) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pStatement = null;
		try {
			String insert = "INSERT INTO questionfromnet(QUESTION,TYPE) VALUES (?,?)";
			pStatement = conn.prepareStatement(insert);
			pStatement.setString(1, "1212");
			pStatement.setString(2, "");
			pStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closePreparedStatement(pStatement);
		DBUtil.closeConnection(conn);
	}
}
