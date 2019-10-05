package com.kexinxin.util.search;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.kexinxin.bean.Question;
import com.kexinxin.bean.QuestionNet;
import com.kexinxin.bean.StopWord;
import com.kexinxin.dao.impl.StopWordDAOImpl;
import com.kexinxin.util.classify.ClassifyUtil;

public class LuceneUtil {
	/***
	 * 得到HDFS的writer
	 * 
	 **/
	public static IndexWriter getIndexWriter() throws Exception {
		String indexPath = "D:\\lucenc\\index04";
		Directory directory = FSDirectory.open(new File(indexPath));
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_48);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
		IndexWriter writer = new IndexWriter(directory, config);
		return writer;
	}

	public static IndexWriter getLocalQuestionIndexWriter() throws Exception {
		String indexPath = "D:\\lucenc\\index05";
		Directory directory = FSDirectory.open(new File(indexPath));
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_48);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
		IndexWriter writer = new IndexWriter(directory, config);
		return writer;
	}

	public static void add() throws Exception {

		IndexWriter writer = getIndexWriter();
		for (int i = 6; i < 100; i++) {
			Document doc = new Document();
			doc.add(new TextField("id", i + "", Store.YES));
			doc.add(new TextField("name", "lucene是一款非常优秀的全文检索框架" + i, Store.YES));
			doc.add(new TextField("content", "今天发工资了吗" + i, Store.YES));
			writer.addDocument(doc);
		}
		writer.commit();
		System.out.println("索引3条数据添加成功!");
		writer.close();
	}

	public static void add(List<QuestionNet> questionNetList) throws Exception {
		IndexWriter writer = getIndexWriter();
		for (int i = 0; i < questionNetList.size(); i++) {
			Document doc = new Document();
			doc.add(new TextField("QuestionNetId", questionNetList.get(i).getQuestionNetId() + "", Store.YES));
			doc.add(new TextField("A", questionNetList.get(i).getA() + "", Store.YES));
			doc.add(new TextField("B", questionNetList.get(i).getB() + "", Store.YES));
			doc.add(new TextField("C", questionNetList.get(i).getC() + "", Store.YES));
			doc.add(new TextField("CateName", questionNetList.get(i).getCateName() + "", Store.YES));
			doc.add(new TextField("D", questionNetList.get(i).getD() + "", Store.YES));
			doc.add(new TextField("QuestionContent", questionNetList.get(i).getQuestionContent() + "", Store.YES));
			doc.add(new TextField("QuestionDifficulty", questionNetList.get(i).getQuestionDifficulty() + "",
					Store.YES));
			doc.add(new TextField("QuestionTitle", questionNetList.get(i).getQuestionTitle() + "", Store.YES));
			doc.add(new TextField("SkillName", questionNetList.get(i).getSkillName() + "", Store.YES));
			writer.addDocument(doc);
		}
		writer.commit();
		writer.close();
	}

	public static void addLocalQuestion(List<Question> questionList) throws Exception {
		IndexWriter writer = getLocalQuestionIndexWriter();
		for (int i = 0; i < questionList.size(); i++) {
			Document doc = new Document();
			doc.add(new TextField("QuestionId", questionList.get(i).getQuestionId() + "", Store.YES));
			doc.add(new TextField("A", questionList.get(i).getA() + "", Store.YES));
			doc.add(new TextField("B", questionList.get(i).getB() + "", Store.YES));
			doc.add(new TextField("C", questionList.get(i).getC() + "", Store.YES));
			doc.add(new TextField("CateName", questionList.get(i).getCateName() + "", Store.YES));
			doc.add(new TextField("D", questionList.get(i).getD() + "", Store.YES));
			doc.add(new TextField("QuestionContent", questionList.get(i).getQuestionContent() + "", Store.YES));
			doc.add(new TextField("QuestionDifficulty", questionList.get(i).getQuestionDifficulty() + "", Store.YES));
			doc.add(new TextField("QuestionTitle", questionList.get(i).getQuestionTitle() + "", Store.YES));
			doc.add(new TextField("SkillName", questionList.get(i).getSkillName() + "", Store.YES));
			doc.add(new TextField("QuestionAnswer", questionList.get(i).getQuestionAnswer() + "", Store.YES));
			writer.addDocument(doc);
		}
		writer.commit();
		writer.close();
	}

	/**
	 * 根据指定ID 删除HDFS上的一些数据
	 * 
	 * 
	 **/
	public static void delete(String id) throws Exception {

		IndexWriter writer = getIndexWriter();
		writer.deleteDocuments(new Term("id", id));// 删除指定ID的数据
		writer.forceMerge(1);// 清除已经删除的索引空间
		writer.commit();// 提交变化

		System.out.println("id为" + id + "的数据已经删除成功.........");

	}

	public static void queryFromNet(String queryTerm) throws Exception {
		System.out.println("本次检索内容: " + queryTerm);

		String indexPath = "D:\\lucenc\\index04";
		Directory directory = FSDirectory.open(new File(indexPath));

		IndexReader reader = DirectoryReader.open(directory);
		System.out.println("总数据量: " + reader.numDocs());
		long a = System.currentTimeMillis();
		IndexSearcher searcher = new IndexSearcher(reader);

		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_48);
		QueryParser parse = new QueryParser(Version.LUCENE_48, "QuestionTitle", analyzer);
		Query query = parse.parse(queryTerm);
		TopDocs docs = searcher.search(query, 100);
		System.out.println("本次命中结果: " + docs.totalHits + " 条");
		for (ScoreDoc sc : docs.scoreDocs) {
			System.out.println("评分: " + sc.score + " id : " + searcher.doc(sc.doc).get("QuestionNetId") + " name: "
					+ searcher.doc(sc.doc).get("QuestionTitle"));
		}
		long b = System.currentTimeMillis();
		System.out.println("耗时:" + (b - a) + " 毫秒");
		reader.close();
		directory.close();
		System.out.println("检索完毕...............");

	}

	public static List<Question> queryFromLocal(SearchCondition searchCondition) throws ParseException, IOException {
		List<Question> questionList = new ArrayList<Question>();

		System.out.println("本次检索内容: " + searchCondition.getQuestion());

		String indexPath = "D:\\lucenc\\index05";
		Directory directory = FSDirectory.open(new File(indexPath));

		IndexReader reader = DirectoryReader.open(directory);
		System.out.println("总数据量: " + reader.numDocs());
		long a = System.currentTimeMillis();
		IndexSearcher searcher = new IndexSearcher(reader);

		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_48);

		int length = 1 + searchCondition.getQuestionTypeList().size() + searchCondition.getSkillNameList().size();
		String[] queries = new String[length];
		String[] fields = new String[length];
		queries[0] = searchCondition.getQuestion();
		for (int i = 1; i <= searchCondition.getSkillNameList().size(); i++) {
			queries[i] = searchCondition.getSkillNameList().get(i - 1);
		}
		for (int i = (searchCondition.getSkillNameList().size() + 1); i <= searchCondition.getQuestionTypeList().size()
				+ searchCondition.getSkillNameList().size(); i++) {
			queries[i] = searchCondition.getQuestionTypeList().get(i - (searchCondition.getSkillNameList().size() + 1));
		}

		fields[0] = "QuestionTitle";
		for (int i = 1; i <= searchCondition.getSkillNameList().size(); i++) {
			fields[i] = "SkillName";
		}
		for (int i = (searchCondition.getSkillNameList().size() + 1); i <= searchCondition.getQuestionTypeList().size()
				+ searchCondition.getSkillNameList().size(); i++) {
			fields[i] = "CateName";
		}

		BooleanClause.Occur[] clauses = new BooleanClause.Occur[length];
		clauses[0] = BooleanClause.Occur.MUST;
		for (int i = 1; i <= searchCondition.getQuestionTypeList().size()
				+ searchCondition.getSkillNameList().size(); i++) {
			clauses[i] = BooleanClause.Occur.SHOULD;
		}

		Query query = MultiFieldQueryParser.parse(Version.LUCENE_48, queries, fields, clauses, analyzer);

		TopDocs docs = searcher.search(query, 100);
		System.out.println("本次命中结果: " + docs.totalHits + " 条");
		for (ScoreDoc sc : docs.scoreDocs) {
			System.out.println("评分: " + sc.score + " id : " + searcher.doc(sc.doc).get("QuestionId") + " name: "
					+ searcher.doc(sc.doc).get("QuestionTitle"));
			Question question = new Question();
			question.setA(searcher.doc(sc.doc).get("A"));
			question.setB(searcher.doc(sc.doc).get("B"));
			question.setC(searcher.doc(sc.doc).get("C"));
			question.setD(searcher.doc(sc.doc).get("D"));
			question.setCateName(searcher.doc(sc.doc).get("CateName"));
			question.setQuestionContent(searcher.doc(sc.doc).get("QuestionContent"));
			if (!searcher.doc(sc.doc).get("QuestionDifficulty").equals("null")) {
				System.out.println(searcher.doc(sc.doc).get("QuestionDifficulty"));
				question.setQuestionDifficulty(Integer.valueOf(searcher.doc(sc.doc).get("QuestionDifficulty")));
			}
			question.setQuestionTitle(searcher.doc(sc.doc).get("QuestionTitle"));
			question.setSkillName(searcher.doc(sc.doc).get("SkillName"));
			question.setQuestionAnswer(searcher.doc(sc.doc).get("QuestionAnswer"));
			questionList.add(question);
		}
		long b = System.currentTimeMillis();
		System.out.println("耗时:" + (b - a) + " 毫秒");
		reader.close();
		directory.close();
		System.out.println("检索完毕...............");

		return questionList;
	}

	public static SearchCondition getSearchCondition(String question) throws IOException, ClassNotFoundException {
		List<StopWord> stopWord = StopWordDAOImpl.getStopWordList();

		List<String> stopWordStringList = new ArrayList<String>();
		for (int i = 0; i < stopWord.size(); i++) {
			stopWordStringList.add(stopWord.get(i).getValue());
		}

		String str = question;

		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_48);
		TokenStream stream = analyzer.tokenStream(null, new StringReader(str));
		stream.reset();
		CharTermAttribute term = stream.addAttribute(CharTermAttribute.class);
		List<String> result = new ArrayList<String>();

		String resultStr = "";
		while (stream.incrementToken()) {
			String str1 = term.toString();
			if (!stopWordStringList.contains(str1)) {
				resultStr += str1;
			}

			result.add(term.toString());
		}

		SearchConditionMap searchConditionMap = new SearchConditionMap();
		List<String> conditionQuesionTypeList = searchConditionMap.getQuestionTypeList();
		List<String> questionTypeList = new ArrayList<String>();

		for (String type : conditionQuesionTypeList) {
			if (resultStr.contains(type)) {
				questionTypeList.add(type);
				resultStr = resultStr.replaceAll(type, "");
			}
		}

		SearchCondition searchCondition = new SearchCondition();
		searchCondition.setQuestion(resultStr);
		searchCondition.setQuestionTypeList(questionTypeList);

		ClassifyUtil classifyUtil = new ClassifyUtil();
		String[] bestCatagory = classifyUtil.getBestCategory(resultStr);

		List<String> skillNameList = new ArrayList<String>();
		for (int i = 0; i < bestCatagory.length; i++) {
			skillNameList.add(bestCatagory[i]);
		}
		searchCondition.setSkillNameList(skillNameList);

		return searchCondition;

	}

	public static void main(String[] args) throws Exception {
		SearchCondition searchCondition = getSearchCondition("软件测试");

		List<Question> questionList = queryFromLocal(searchCondition);
		
		
		
		System.out.println("");
	}
}
