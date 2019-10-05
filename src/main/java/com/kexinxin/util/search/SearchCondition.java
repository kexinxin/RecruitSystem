package com.kexinxin.util.search;

import java.util.List;

public class SearchCondition {
	private List<String> questionTypeList;
	private List<String> skillNameList;
	private String question;

	public List<String> getQuestionTypeList() {
		return questionTypeList;
	}

	public void setQuestionTypeList(List<String> questionTypeList) {
		this.questionTypeList = questionTypeList;
	}

	public List<String> getSkillNameList() {
		return skillNameList;
	}

	public void setSkillNameList(List<String> skillNameList) {
		this.skillNameList = skillNameList;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
