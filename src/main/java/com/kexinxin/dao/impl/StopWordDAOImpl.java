package com.kexinxin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kexinxin.bean.Skill;
import com.kexinxin.bean.StopWord;
import com.kexinxin.util.DBUtil;

public class StopWordDAOImpl {
	public static List<StopWord> getStopWordList() {
		List<StopWord> stopWordList = new ArrayList<StopWord>();

		String sql = "SELECT * FROM stop_word";
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				StopWord stopWord = new StopWord();
				stopWord.setId(resultSet.getInt(1));
				stopWord.setValue(resultSet.getString(2));
				stopWordList.add(stopWord);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stopWordList;
	}

	public static void main(String[] args) {
		List<StopWord> stopWordList = getStopWordList();
		System.out.println(stopWordList.size());
	}

}
