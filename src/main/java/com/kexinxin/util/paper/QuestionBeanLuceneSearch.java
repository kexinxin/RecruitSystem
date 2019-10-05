package com.kexinxin.util.paper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.kexinxin.vo.QuestionBean;

public class QuestionBeanLuceneSearch {

	public static QuestionBean[] getQuestionArray(int type, String substring, String describe)
			throws IOException, ParseException {
		String contentType = "";
		if (type == 1) {
			contentType = "单选题";
		} else if (type == 2) {
			contentType = "编程题";
		} else if (type == 3) {
			contentType = "简答题";
		}
		String[] skillIds = substring.split(",");
		for (int i = 0; i < skillIds.length; i++) {
			skillIds[i] = skillIds[i].replace(" ", "");
		}

		List<QuestionBean> questionBeanList = new ArrayList<QuestionBean>();

		int length = 2 + skillIds.length;
		String[] queries = new String[length];
		String[] fields = new String[length];
		String indexPath = "D:\\lucenc\\index05";
		Directory directory = FSDirectory.open(new File(indexPath));
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_48);
		queries[0] = contentType;
		queries[1] = describe;
		for (int i = 2; i < 2 + skillIds.length; i++) {
			queries[i] = skillIds[i - 2];
		}
		fields[0] = "CateName";
		fields[1] = "QuestionTitle";
		for (int i = 2; i < 2 + skillIds.length; i++) {
			fields[i] = "SkillName";
		}
		BooleanClause.Occur[] clauses = new BooleanClause.Occur[length];
		clauses[0] = BooleanClause.Occur.MUST;
		for (int i = 1; i < 2 + skillIds.length; i++) {
			clauses[i] = BooleanClause.Occur.SHOULD;
		}

		Query query = MultiFieldQueryParser.parse(Version.LUCENE_48, queries, fields, clauses, analyzer);

		TopDocs docs = searcher.search(query, 500);
		//System.out.println("本次命中结果: " + docs.totalHits + " 条");
		for (ScoreDoc sc : docs.scoreDocs) {
			// System.out.println("评分: " + sc.score + " id : " +
			// searcher.doc(sc.doc).get("QuestionId") + " name: "
			// + searcher.doc(sc.doc).get("QuestionTitle"));
			QuestionBean questionBean = new QuestionBean();
			questionBean.setLuceneScore(sc.score);
			questionBean.setAnswer(searcher.doc(sc.doc).get("QuestionAnswer"));
			questionBean.setChoice1(searcher.doc(sc.doc).get("A"));
			questionBean.setChoice2(searcher.doc(sc.doc).get("B"));
			questionBean.setChoice3(searcher.doc(sc.doc).get("C"));
			questionBean.setChoice4(searcher.doc(sc.doc).get("D"));
			if (!searcher.doc(sc.doc).get("QuestionDifficulty").equals("null")) {
				questionBean.setDifficulty(Integer.valueOf(searcher.doc(sc.doc).get("QuestionDifficulty")));
			}
			questionBean.setId(Integer.valueOf(searcher.doc(sc.doc).get("QuestionId")));

			questionBean.setPointId(searcher.doc(sc.doc).get("SkillName"));
			questionBean.setScore(10);
			questionBean.setType(searcher.doc(sc.doc).get("CateName"));
			questionBean.setStem(searcher.doc(sc.doc).get("QuestionTitle"));
			
			int flag = 0;

			for (int i = 0; i < skillIds.length; i++) {
				if (questionBean.getPointId().equals(skillIds[i])) {
					flag = 1;
				}
			}

			if (flag == 1) {
				questionBeanList.add(questionBean);
			}

		}
		reader.close();
		directory.close();
		QuestionBean[] result = new QuestionBean[questionBeanList.size()];
		for (int i = 0; i < questionBeanList.size(); i++) {
			result[i] = questionBeanList.get(i);
		}
		return result;
	}

	public static List<QuestionBean> getQuestionListWithOutSId(QuestionBean tmpQuestion, String describle)
			throws IOException, ParseException {
		List<QuestionBean> questionBeanList = new ArrayList<QuestionBean>();

		int length = 4;
		String[] queries = new String[length];
		String[] fields = new String[length];
		String indexPath = "D:\\lucenc\\index05";
		Directory directory = FSDirectory.open(new File(indexPath));
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_48);
		queries[0] = tmpQuestion.getType();
		queries[1] = tmpQuestion.getDifficulty() + "";
		queries[2] = tmpQuestion.getId() + "";
		queries[3]=describle;
		
		fields[0] = "CateName";
		fields[1] = "QuestionDifficulty";
		fields[2] = "QuestionId";
		fields[3]="QuestionTitle";
		
		
		BooleanClause.Occur[] clauses = new BooleanClause.Occur[length];
		clauses[0] = BooleanClause.Occur.MUST;
		clauses[1] = BooleanClause.Occur.MUST;
		clauses[2] = BooleanClause.Occur.MUST_NOT;
		clauses[3]=BooleanClause.Occur.SHOULD;
		
		
		Query query = MultiFieldQueryParser.parse(Version.LUCENE_48, queries, fields, clauses, analyzer);

		TopDocs docs = searcher.search(query, 500);
		//System.out.println("本次命中结果: " + docs.totalHits + " 条");
		for (ScoreDoc sc : docs.scoreDocs) {
			// System.out.println("评分: " + sc.score + " id : " +
			// searcher.doc(sc.doc).get("QuestionId") + " name: "
			// + searcher.doc(sc.doc).get("QuestionTitle"));
			QuestionBean questionBean = new QuestionBean();
			questionBean.setLuceneScore(sc.score);
			questionBean.setAnswer(searcher.doc(sc.doc).get("QuestionAnswer"));
			questionBean.setChoice1(searcher.doc(sc.doc).get("A"));
			questionBean.setChoice2(searcher.doc(sc.doc).get("B"));
			questionBean.setChoice3(searcher.doc(sc.doc).get("C"));
			questionBean.setChoice4(searcher.doc(sc.doc).get("D"));
			if (!searcher.doc(sc.doc).get("QuestionDifficulty").equals("null")) {
				questionBean.setDifficulty(Integer.valueOf(searcher.doc(sc.doc).get("QuestionDifficulty")));
			}
			questionBean.setId(Integer.valueOf(searcher.doc(sc.doc).get("QuestionId")));

			questionBean.setPointId(searcher.doc(sc.doc).get("SkillName"));
			questionBean.setScore(10);
			questionBean.setType(searcher.doc(sc.doc).get("CateName"));
			questionBean.setStem(searcher.doc(sc.doc).get("QuestionTitle"));
			questionBeanList.add(questionBean);

		}
		reader.close();
		directory.close();

		return questionBeanList;
	}

	public static void main(String[] args) throws IOException, ParseException {
		//QuestionBean[] questionBeanList = getQuestionArray(1, "SQL,Jquery", "多线程");
		//System.out.println(questionBeanList.length);
		
		QuestionBean questionBean=new QuestionBean();
		questionBean.setId(10);
		questionBean.setType("简答题");
		questionBean.setDifficulty(5);
		List<QuestionBean> questionList=getQuestionListWithOutSId(questionBean, "多线程");
		
		System.out.println(questionList.size());
	}
}