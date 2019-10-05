package com.kexinxin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kexinxin.bean.Question;
import com.kexinxin.service.QuestionService;
import com.kexinxin.util.hadoop.CrawlerMapReduce;
import com.kexinxin.util.hadoop.MyIndex;
import com.kexinxin.util.poi.ExcelReader;
import com.kexinxin.util.search.LuceneUtil;
import com.kexinxin.util.search.SearchCondition;
import com.kexinxin.vo.ExcelContent;

@Controller
public class QuestionController {
	private static final Logger logger = Logger.getLogger(QuestionController.class);

	@Resource
	QuestionService questionService;

	@RequestMapping(value = "/question/getQuestionList", method = RequestMethod.GET)
	public ModelAndView getQuestionList() {
		ModelAndView model = new ModelAndView("question/questionList");
		List<Question> questionList = questionService.selectAllQuestion();
		model.addObject("questionList", questionList);
		return model;
	}

	@RequestMapping(value = "/question/getQuestionManage", method = RequestMethod.GET)
	public ModelAndView getQuestionManage() {
		ModelAndView model = new ModelAndView("question/questionManage");
		List<Question> questionList = questionService.selectCollectQuestion();
		model.addObject("questionList", questionList);
		return model;
	}

	@RequestMapping(value = "/question/saveSelectQuestion", method = RequestMethod.POST)
	public ModelAndView saveSelectQuestion(Question question) {
		questionService.insert(question);
		ModelAndView model = new ModelAndView("question/questionManage");
		List<Question> questionList = questionService.selectCollectQuestion();
		model.addObject("questionList", questionList);
		return model;
	}

	@RequestMapping(value = "/question/saveBlankQuestion", method = RequestMethod.POST)
	public ModelAndView saveBlankQuestion(Question question) {
		questionService.insert(question);
		ModelAndView model = new ModelAndView("question/questionManage");
		List<Question> questionList = questionService.selectCollectQuestion();
		model.addObject("questionList", questionList);
		return model;
	}

	@RequestMapping(value = "/question/saveCodeQuestion", method = RequestMethod.POST)
	public ModelAndView saveCodeQuestion(Question question) {
		questionService.insert(question);
		ModelAndView model = new ModelAndView("question/questionManage");
		List<Question> questionList = questionService.selectCollectQuestion();
		model.addObject("questionList", questionList);
		return model;
	}

	/**
	 * 试题Excel导入
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "question/uploadQuestion", method = RequestMethod.POST)
	public ModelAndView upload(MultipartFile file, HttpServletRequest request) throws IOException {
		String fileName = file.getOriginalFilename();
		InputStream stream = file.getInputStream();

		List<ExcelContent> excelContentList = new LinkedList<ExcelContent>();
		ExcelReader excelReader = new ExcelReader();
		Map<Integer, String> map = excelReader.readExcelContent(stream);
		for (int i = 1; i <= map.size() - 2; i++) {
			String content = map.get(i);
			ExcelContent excelContent = new ExcelContent();
			String[] contents = content.split("#");
			excelContent.setCate_name(contents[0]);
			excelContent.setQuestion_content(contents[1]);
			if (contents[0].equals("单选题") || contents[0].equals("多选题")) {
				excelContent.setAnswer_num(contents[2]);
				excelContent.setAnswer_content(contents[3]);
				excelContent.setUser_name(contents[4]);
			} else {
				excelContent.setAnswer_content(contents[2]);
				excelContent.setUser_name(contents[3]);
			}
			excelContentList.add(excelContent);
		}
		for (int i = 0; i < excelContentList.size(); i++) {
			Question question = new Question();
			if (excelContentList.get(i).getCate_name().equals("单选题")
					|| excelContentList.get(i).getCate_name().equals("多选题")) {
				question.setQuestionTitle(excelContentList.get(i).getQuestion_content());
				question.setQuestionAnswer(excelContentList.get(i).getAnswer_content());
				question.setCateName(excelContentList.get(i).getCate_name());
				String[] choise = excelContentList.get(i).getAnswer_num().split("/");
				question.setA(choise[0]);
				question.setB(choise[1]);
				question.setC(choise[2]);
				question.setD(choise[3]);

			} else {
				question.setQuestionTitle(excelContentList.get(i).getQuestion_content());
				question.setQuestionAnswer(excelContentList.get(i).getAnswer_content());
				question.setCateName(excelContentList.get(i).getCate_name());
			}
			questionService.insert(question);
		}

		ModelAndView model = new ModelAndView("question/questionManage");
		List<Question> questionList = questionService.selectCollectQuestion();
		model.addObject("questionList", questionList);
		return model;
	}

	@RequestMapping(value = "/question/collectQuestion", method = RequestMethod.GET)
	public ModelAndView collectQuestion(Question question, HttpServletRequest request) {
		System.out.println(question.getQuestionId());
		Question result = questionService.selectByPrimaryKey(question.getQuestionId());
		result.setQuestionCollect(result.getQuestionCollect() + 1);
		questionService.updateByPrimaryKey(result);
		ModelAndView model = new ModelAndView("question/questionList");
		List<Question> questionList = questionService.selectAllQuestion();
		model.addObject("questionList", questionList);
		return model;
	}

	@RequestMapping(value = "/question/test", method = RequestMethod.GET)
	public ModelAndView test(Question question, HttpServletRequest request) throws Exception {
		System.out.println(question.getQuestionId());
		MyIndex.add();
		Question result = questionService.selectByPrimaryKey(question.getQuestionId());
		result.setQuestionCollect(result.getQuestionCollect() + 1);
		questionService.updateByPrimaryKey(result);
		ModelAndView model = new ModelAndView("question/questionList");
		List<Question> questionList = questionService.selectAllQuestion();
		model.addObject("questionList", questionList);
		return model;
	}

	@RequestMapping(value = "/question/testCrawler", method = RequestMethod.GET)
	public ModelAndView testCrawler(Question question, HttpServletRequest request) throws Exception {
		System.out.println(question.getQuestionId());

		CrawlerMapReduce.startMapReduceCrawler();

		ModelAndView model = new ModelAndView("question/questionList");
		List<Question> questionList = questionService.selectAllQuestion();
		model.addObject("questionList", questionList);
		return model;
	}

	@RequestMapping(value = "/question/searchQuestion", method = RequestMethod.POST)
	public ModelAndView searchQuestion(Question question, HttpServletRequest request) throws Exception {
		System.out.println(question.getQuestionTitle());
		// ClassifyUtil classifyUtil=new ClassifyUtil();
		// String cateGory= classifyUtil.getBestOneCategory("软件测试");
		// System.out.println(cateGory);
		// LuceneUtil.query(question.getQuestionTitle());

		// MyIndex.query(question.getQuestionTitle());

		SearchCondition searchCondition = LuceneUtil.getSearchCondition(question.getQuestionTitle());
		List<Question> questionList = LuceneUtil.queryFromLocal(searchCondition);

		List<Question> resultQuestionList = new ArrayList<Question>();

		if (questionList.size() > 10) {
			for (int i = 0; i < 10; i++) {
				resultQuestionList.add(questionList.get(i));
			}
		} else {
			for (int i = 0; i < questionList.size(); i++) {
				resultQuestionList.add(questionList.get(i));
			}
		}

		ModelAndView model = new ModelAndView("question/questionList");
		// List<Question> questionList = questionService.selectAllQuestion();

		model.addObject("questionList", resultQuestionList);
		model.addObject("searchCondition", searchCondition);
		return model;
	}
}
