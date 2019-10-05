package com.kexinxin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kexinxin.service.ExamService;
import com.kexinxin.service.PositionService;
import com.kexinxin.util.paper.Paper;
import com.kexinxin.vo.QuestionBean;
import com.kexinxin.vo.RuleBean;

@Controller
public class ExamController {
	private static final Logger logger = Logger.getLogger(HomeController.class);

	@Resource
	ExamService examService;

	@RequestMapping(value = "/exam/exam", method = RequestMethod.GET)
	public String exam() {

		return "exam/exam";
	}

	@RequestMapping(value = "/exam/paper", method = RequestMethod.POST)
	public ModelAndView paper(RuleBean ruleBean) {
		Paper paper = examService.getGAPaper(ruleBean);
		List<QuestionBean> questionBeanList = paper.getQuestionList();
		ModelAndView model = new ModelAndView("exam/paper");
		model.addObject("questionBeanList", questionBeanList);
		model.addObject("adaptationDegree",paper.getAdaptationDegree());
		model.addObject("difficulty",paper.getDifficulty());
		return model;
	}
}
