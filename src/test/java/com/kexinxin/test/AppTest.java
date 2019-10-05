package com.kexinxin.test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kexinxin.bean.Exam;
import com.kexinxin.bean.Question;
import com.kexinxin.bean.QuestionNet;
import com.kexinxin.bean.Skill;
import com.kexinxin.bean.StopWord;
import com.kexinxin.bean.User;
import com.kexinxin.dao.ExamDao;
import com.kexinxin.dao.QuestionDao;
import com.kexinxin.dao.UserDao;
import com.kexinxin.dao.impl.StopWordDAOImpl;
import com.kexinxin.service.QuestionNetService;
import com.kexinxin.service.QuestionService;
import com.kexinxin.service.SkillService;
import com.kexinxin.service.StopWordService;
import com.kexinxin.service.impl.QuestionServiceImpl;
import com.kexinxin.service.impl.SkillServiceImpl;
import com.kexinxin.util.DBUtil;
import com.kexinxin.util.classify.ClassifyUtil;
import com.kexinxin.util.paper.GA;
import com.kexinxin.util.paper.Paper;
import com.kexinxin.util.paper.Population;
import com.kexinxin.util.search.LuceneUtil;
import com.kexinxin.util.search.SearchCondition;
import com.kexinxin.util.search.SearchConditionMap;
import com.kexinxin.vo.PositionResum;
import com.kexinxin.vo.QuestionBean;
import com.kexinxin.vo.RuleBean;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}

	public void test1() {
		System.out.println("hahahahs");
	}

	@SuppressWarnings({ "resource" })
	public void test2() {
		ApplicationContext ct = new ClassPathXmlApplicationContext("application.xml");
		UserDao userDaoImpl = ct.getBean(UserDao.class);
		List<User> userList = userDaoImpl.getOnePageUsers();
		for (User user : userList) {
			System.out.println(user.getNickName());
			System.out.println(user.getPassword());
		}
	}

	@SuppressWarnings({ "resource" })
	public void test3() {
		ApplicationContext ct = new ClassPathXmlApplicationContext("application.xml");
		ExamDao examDaoImpl = ct.getBean(ExamDao.class);
		Exam exam = examDaoImpl.selectByPrimaryKey(1);
		System.out.println(exam.getExamName());
	}

	@SuppressWarnings({ "resource" })
	public void test4() {
		ApplicationContext ct = new ClassPathXmlApplicationContext("application.xml");
		QuestionDao questionDaoImpl = ct.getBean(QuestionDao.class);
		Question question = questionDaoImpl.selectByPrimaryKey(1);
		System.out.println(question.getQuestionTitle());
	}

	@SuppressWarnings({ "resource" })
	public void test5() {
		ApplicationContext ct = new ClassPathXmlApplicationContext("application.xml");
		QuestionService questionService = ct.getBean(QuestionServiceImpl.class);
		Question question = questionService.selectByPrimaryKey(1);
		System.out.println(question.getQuestionTitle());
	}

	@SuppressWarnings({ "resource" })
	public void test6() {
		ApplicationContext ct = new ClassPathXmlApplicationContext("application.xml");
		QuestionService questionService = ct.getBean(QuestionServiceImpl.class);

		// ClassifyUtil classifyUtil=ct.getBean(ClassifyUtil.class);

		// PositionController
		// positionController=ct.getBean(PositionController.class);

		List<Question> questionList = questionService.selectAllQuestion();
		List<Question> collectQuestionList = questionService.selectCollectQuestion();
		System.out.println(collectQuestionList.size());
	}

	public void test7() throws IOException {
		String text = "我的名字叫做柯鑫鑫";
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_48);
		TokenStream stream = analyzer.tokenStream(null, new StringReader(text));
		stream.reset();
		CharTermAttribute term = stream.addAttribute(CharTermAttribute.class);
		List<String> result = new ArrayList<String>();
		while (stream.incrementToken()) {
			result.add(term.toString());
			System.out.println(term.toString());
		}
	}

	public void test8() throws IOException, ParseException {
		Paper resultPaper = null;
		// 迭代计数器
		int count = 0;
		int runCount = 6;
		// 适应度期望值
		double expand = 0.98;
		// 可自己初始化组卷规则rule
		RuleBean ruleBean = new RuleBean();
		ruleBean.setSingleNum(2);
		ruleBean.setCompleteNum(2);
		ruleBean.setDifficulty(10);
		ruleBean.setSubjectiveNum(2);
		ruleBean.setTotalMark(150);
		
		ruleBean.setDescrible("多线程，前端框架");
		
		List<String> list = new ArrayList<String>();
		list.add("SQL");
		list.add("Jquery");
		ruleBean.setPointIds(list);

		if (ruleBean != null) {
			// 初始化种群
			Population population = new Population(20, true, ruleBean);
			System.out.println("初次适应度  " + population.getFitness().getAdaptationDegree());

			while (count < runCount && population.getFitness().getAdaptationDegree() < expand) {
				count++;
				population = GA.evolvePopulation(population, ruleBean);
				System.out.println("第 " + count + " 次进化，适应度为： " + population.getFitness().getAdaptationDegree());
			}
			System.out.println("进化次数： " + count);
			System.out.println(population.getFitness().getAdaptationDegree());
			resultPaper = population.getFitness();
		}

		for (int i = 0; i < resultPaper.getQuestionSize(); i++) {
			System.out.println(resultPaper.getQuestion(i).getType() + ":" + resultPaper.getQuestion(i).getStem());
		}
	}

	public void test9() {
		long a = System.currentTimeMillis();
		String sql = "SELECT question_title FROM question_net ";
		List<QuestionBean> questionBeanList = new ArrayList<QuestionBean>();
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);

			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				String question = resultSet.getString(1);

				QuestionBean questionBean = new QuestionBean();
				questionBean.setStem(question);

				questionBeanList.add(questionBean);
				// System.out.println(question);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(questionBeanList.size());
		for (int i = 0; i < 100; i++) {
			System.out.println(questionBeanList.get(i));
		}
		long b = System.currentTimeMillis();
		System.out.println("第一次耗时:" + (b - a) + " 毫秒");
	}

	public void test10() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationProvider.xml" });
		context.start();
		System.out.println("按任意键退出");
		System.in.read();
	}

	public void test11() {
		ApplicationContext ct = new ClassPathXmlApplicationContext("application.xml");
		SkillService skillService = ct.getBean(SkillServiceImpl.class);
		List<Skill> skillList = skillService.selectAllSkill();
		System.out.println(skillList.size());
		List<String> skillNameList = skillService.getPositionSkillList(
				"熟练掌握Java语言，具有独立开发项目的经验。掌握常用算法、数据结构、网络协议等。掌握数据库基础，有至少一种主流数据库产品使用、维护、性能优化的经验。具有较强的问题定位和分析能力。热爱开发工作，团队合作意识良好、责任感强、有较强的沟通能力和抗压能力。满足以下一项或多项的同学优先：掌握常见Web框架技术，如Spring、Struts、Hibernate、MyBatis等。掌握XML、JSON等。掌握Memcache/Redis/Mongodb等技术");
		for (String name : skillNameList) {
			System.out.println(name);
		}
	}

	public void test12() {
		ApplicationContext ct = new ClassPathXmlApplicationContext("application.xml");
		SkillService skillService = ct.getBean(SkillServiceImpl.class);
		PositionResum positionResum = new PositionResum();
		positionResum.setPosition("java C# 设计模式 算法");
		positionResum.setResume("java 设计模式 PHP");
		String result = skillService.getResumeAndPositionAnazyResult(positionResum);
		System.out.println(result);
	}

	public void test13() throws ClassNotFoundException, IOException {
		ClassifyUtil classifyUtil = new ClassifyUtil();
		String bestCatagory = classifyUtil.getBestOneCategory("描述");
		System.out.println(bestCatagory);
	}

	public void test14() throws Exception {
		ApplicationContext ct = new ClassPathXmlApplicationContext("application.xml");
		QuestionNetService questionNetService = ct.getBean(QuestionNetService.class);
		List<QuestionNet> questionNetList = questionNetService.selectAllQuestionNet();
		LuceneUtil.add(questionNetList);
	}

	public void test15() throws IOException, ClassNotFoundException {
		// ApplicationContext ct = new
		// ClassPathXmlApplicationContext("application.xml");
		// StopWordService stopWordService = ct.getBean(StopWordService.class);
		// List<StopWord> stopWord = stopWordService.selectAllStopWord();

		List<StopWord> stopWord = StopWordDAOImpl.getStopWordList();

		List<String> stopWordStringList = new ArrayList<String>();
		for (int i = 0; i < stopWord.size(); i++) {
			stopWordStringList.add(stopWord.get(i).getValue());
		}

		String str = "有关测试的选择题";

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

		System.out.println(searchCondition);
	}
	
	public void test16() throws ClassNotFoundException, IOException{
		SearchCondition searchCondition=LuceneUtil.getSearchCondition("有关测试的选择题");
	}
	
	public void test17() throws IOException, ParseException{
		String queryTerm="测试";
		
		String indexPath = "D:\\lucenc\\index04";
		Directory directory = FSDirectory.open(new File(indexPath));
		IndexReader reader = DirectoryReader.open(directory);
		System.out.println("总数据量: " + reader.numDocs());
		
		long a = System.currentTimeMillis();
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_48);
		
		
		String[] queries={"测试","java"};
		String[] fields={"QuestionTitle","SkillName"};
		BooleanClause.Occur[] clauses = { BooleanClause.Occur.SHOULD, BooleanClause.Occur.MUST};
		Query query = MultiFieldQueryParser.parse(Version.LUCENE_48, queries, fields, clauses,analyzer);  
		
		
		//QueryParser parse = new QueryParser(Version.LUCENE_48, "QuestionTitle", analyzer);
		//Query query = parse.parse(queryTerm);
		TopDocs docs = searcher.search(query, 100);
		System.out.println("本次命中结果: " + docs.totalHits + " 条");
		for (ScoreDoc sc : docs.scoreDocs) {
			System.out.println("评分: " + sc.score + " id : " + searcher.doc(sc.doc).get("QuestionNetId") + " name: "
					+ searcher.doc(sc.doc).get("QuestionTitle")); 
		}
		long b = System.currentTimeMillis();
		System.out.println("耗时:" + (b - a) + " 毫秒");
		
		directory.close();
	}
	
	public void test18() throws Exception{
		ApplicationContext ct = new ClassPathXmlApplicationContext("application.xml");
		QuestionService questionService = ct.getBean(QuestionService.class);
		List<Question> questionList=questionService.getAllQuestion();
		System.out.println(questionList.size());
		LuceneUtil.addLocalQuestion(questionList);
	}
}
