package com.kexinxin.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.kexinxin.bean.User;
import com.kexinxin.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Resource
	private UserService userService;

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public String login(User user, String autoLogin, HttpServletRequest request, HttpSession session) {
		user = userService.login(user);
		if (user == null) {
			request.setAttribute("result", "用户名或密码错误，登陆失败！请仔细检查后重试！若依然无法登陆，请联系系统管理员！");
		} else if (user.getStatus() == 0) {
			request.setAttribute("result", "您的账号已被禁用！请联系系统管理员！");
		} else {
			logger.info(user.getNickName() + "查询成功");
			session.setAttribute("currentUser", user);
			// 不做处理，交给拦截器
		}
		return "home/home";
	}

	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public ModelAndView logOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView(new RedirectView(request.getContextPath()));
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String addUser() {
		return "user/add";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String addUser(User user, boolean switchStatus, HttpServletRequest request, HttpSession session) {
		if (user.getNickName() == "" || user.getPassword() == "") {
			request.setAttribute("result", "登陆昵称和初始密码为必填字段，不能为空！");
			return "user/add";
		}
		if (userService.selectByNickName(user.getNickName()) != null) {
			request.setAttribute("result", "昵称已被占用，请选择其他昵称！");
			return "user/add";
		}

		user.setCreateUserId(((User) session.getAttribute("currentUser")).getUserId());
		// 数据库timestamp自动记录，不写也可
		// user.setCreateTime(new Date());
		if (switchStatus) {
			user.setStatus(1);
		} else {
			user.setStatus(0);
		}
		// username为空时默认与nickname相同
		if (user.getUserName() == "") {
			user.setUserName(user.getNickName());
		}

		int row = userService.addUserSelective(user);
		if (row == 1) {
			logger.info(user.getNickName() + "创建成功");
			request.setAttribute("result", "用户添加成功！");
		} else {
			request.setAttribute("result", "用户添加失败，请重试！");
		}
		return "user/add";
	}

	@RequestMapping(value = "/user/nickNameIsExist")
	public void nickNameIsExist(String name, PrintWriter printWriter) {
		if (userService.selectByNickName(name) != null) {
			printWriter.write("fail");
		} else {
			printWriter.write("success");
		}
		printWriter.flush();
		printWriter.close();
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	public String userRegister() {
		return "user/register";
	}

	@RequestMapping("/user/register/do")
	public void doRegister(User user, PrintWriter printWriter) {
		int row = userService.addUserSelective(user);
		if (row == 1) {
			printWriter.write("success");
		} else {
			printWriter.write("fail");
		}
		printWriter.flush();
		printWriter.close();
	}

	@RequestMapping(value = "/user/modify", method = RequestMethod.GET)
	public String modifyUser() {
		return "user/modify";
	}

	@RequestMapping(value = "/user/modify", method = RequestMethod.POST)
	public String modifyUser(String userName, String password, String newPassword, String reNewPassword,
			HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute("currentUser");
		// 录入用户id和用户名信息
		User newUser = new User();
		newUser.setUserId(user.getUserId());
		newUser.setUserName(userName);
		// 录入密码信息，如果有的话，验证原密码
		if (password.equals(user.getPassword())) {
			// 新密码为空或者两次密码不同
			if ("".equals(newPassword) || !(reNewPassword.equals(newPassword))) {
				request.setAttribute("result", "请确保密码与重复密码相同，且不能为空！");
				return "user/modify";
			} else {
				newUser.setPassword(newPassword);
			}
		} else if (!("".equals(password))) {
			request.setAttribute("result", "原密码错误！修改失败！");
			return "user/modify";
		}
		// 提交修改
		int row = userService.updateUserSelective(newUser);
		if (row == 1) {
			request.setAttribute("result", "修改成功");
			session.setAttribute("currentUser", userService.selectUserById(newUser.getUserId()));
		} else {
			request.setAttribute("result", "修改失败，请稍后重试或联系系统管理员！");
		}
		return "user/modify";
	}

	@RequestMapping(value = "/user/manage/reset")
	public void reset(HttpServletRequest request, PrintWriter printWriter) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		User user = new User();
		user.setUserId(userId);

		if (request.getParameter("password") != null) {
			user.setPassword(request.getParameter("password"));
		}
		if (request.getParameter("status") != null) {
			user.setStatus(("1".equals(request.getParameter("status"))) ? 1 : 0);
		}

		int row = userService.updateUserSelective(user);

		if (row == 1) {
			printWriter.write("修改成功");
		} else {
			printWriter.write("修改失败");
		}
		printWriter.flush();
		printWriter.close();
	}
}
