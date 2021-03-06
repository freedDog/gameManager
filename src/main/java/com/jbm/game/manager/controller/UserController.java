package com.jbm.game.manager.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.jbm.game.engine.util.StringUtils;
import com.jbm.game.manager.constant.SessionKey;
import com.jbm.game.manager.service.UserService;

/**
 * 用户
 * @author Administrator
 *
 */
@RequestMapping("/user")
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@Value("${userName}")
	private String userName;

	@Value("${password}")
	private String password;

	/**
	 * 登录
	 * 
	 * @param session
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpSession session, HttpServletResponse response, String userName, String password) {
		logger.info("开始登陆"+userName+"---"+password+"  系统:"+this.userName+"----"+this.password);
		if (StringUtils.stringIsNullEmpty(userName) || StringUtils.stringIsNullEmpty(password)) {
			return new ModelAndView("redirect:/login");
		}

		if (userName.equalsIgnoreCase(this.userName) && password.equals(this.password)) {
			session.setAttribute(SessionKey.USER_INFO,userName);
			userService.saveCookie(session, userName, response);
			return new ModelAndView("redirect:/home");
		}

		return new ModelAndView("redirect:/login");
	}

}
