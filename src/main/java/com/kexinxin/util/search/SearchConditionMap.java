package com.kexinxin.util.search;

import java.util.ArrayList;
import java.util.List;

public class SearchConditionMap {
	private List<String> questionTypeList = new ArrayList<String>();

	public SearchConditionMap() {
		questionTypeList.add("选择题");
		questionTypeList.add("填空题");
		questionTypeList.add("编程题");
	}

	public List<String> getQuestionTypeList() {
		return questionTypeList;
	}

	public void setQuestionTypeList(List<String> questionTypeList) {
		this.questionTypeList = questionTypeList;
	}

}
