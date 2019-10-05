package com.kexinxin.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kexinxin.bean.Question;
import com.kexinxin.service.QuestionService;

@Controller
public class FileController {

	private static final Logger logger = Logger.getLogger(QuestionController.class);

	@Resource
	QuestionService questionService;

	/**
	 * 文件上传功能
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload")
	public ModelAndView upload(MultipartFile file, HttpServletRequest request) throws IOException {
		String fileName = file.getOriginalFilename();
		InputStream stream = file.getInputStream();
		WordExtractor ex = new WordExtractor(stream);
		System.out.println(ex.getText());

		ModelAndView model = new ModelAndView("question/questionManage");
		List<Question> questionList = questionService.selectCollectQuestion();
		model.addObject("questionList", questionList);
		return model;
	}

	/**
	 * 文件下载功能
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/down")
	public void down(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 模拟文件，myfile.txt为需要下载的文件
		// String fileName =
		// request.getSession().getServletContext().getRealPath("upload") +
		// "/myfile.txt";
		// 获取输入流

		String contextPath = request.getSession().getServletContext().getContextPath();

		InputStream bis = new BufferedInputStream(new FileInputStream(new File(contextPath)));
		// 假如以中文名下载的话
		String filename = "新建文本文档.txt";
		// 转码，免得文件名中文乱码
		filename = URLEncoder.encode(filename, "UTF-8");
		// 设置文件下载头
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		int len = 0;
		while ((len = bis.read()) != -1) {
			out.write(len);
			out.flush();
		}
		out.close();
	}
}