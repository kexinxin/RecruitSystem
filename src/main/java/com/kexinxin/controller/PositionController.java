package com.kexinxin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kexinxin.bean.Position;
import com.kexinxin.service.PositionService;
import com.kexinxin.service.SkillService;
import com.kexinxin.vo.PositionResum;

@Controller
public class PositionController {
	private static final Logger logger = Logger.getLogger(HomeController.class);

	@Resource
	PositionService positionService;

	@Resource
	SkillService skillService;

	@RequestMapping(value = "/position/position", method = RequestMethod.GET)
	public ModelAndView position() {
		ModelAndView model = new ModelAndView("position/position");
		List<Position> positionList = positionService.selectAllPosition();
		model.addObject("positionList", positionList);
		return model;
	}

	@RequestMapping(value = "/position/savePosition", method = RequestMethod.POST)
	public String savePosition(Position position) {
		positionService.insert(position);
		return "position/position";
	}

	@RequestMapping(value = "/position/anazyPosition", method = RequestMethod.GET)
	public void anazyPosition(Position position, HttpServletResponse response) throws IOException {
		System.out.println(position.getPositionId());
		response.getWriter().write("hahahah");
	}

	@RequestMapping(value = "/position/anazyPosition1", method = RequestMethod.GET)
	public ModelAndView anazyPosition1(Position position) {
		position = positionService.selectByPrimaryKey(position.getPositionId());
		List<String> skillNameList = skillService.getPositionSkillList(position.getPositionRequest());

		position.setPositionRequest("");
		for (String name : skillNameList) {
			position.setPositionRequest(position.getPositionRequest() + " " + name);
		}
		ModelAndView model = new ModelAndView("result/positionanazy");
		model.addObject(position);
		return model;
	}

	@RequestMapping(value = "/position/uploadResume", method = RequestMethod.POST)
	public void uploadResume(@RequestParam("file") MultipartFile file, HttpServletResponse response)
			throws IOException {
		InputStream stream = file.getInputStream();
		WordExtractor ex = new WordExtractor(stream);
		String content = ex.getText();
		// System.out.println(text2003);
		List<String> skillNameList = skillService.getContentSkillList(content);
		String anazyResult = "";
		for (String name : skillNameList) {
			anazyResult += " " + name;
		}

		response.getWriter().write(anazyResult);
	}

	@RequestMapping(value = "/position/anazyPositionAndResum", method = RequestMethod.GET)
	public void anazyPositionAndResum(PositionResum positionResum, HttpServletResponse response) throws IOException {

		response.getWriter().write(skillService.getResumeAndPositionAnazyResult(positionResum));
	}
}
