package com.jbm.game.manager.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jbm.game.manager.constant.SessionKey;

/**
 * 登录状态检查，临时处理，以后调整
 * @author Administrator
 *
 */
public class UserInterceptor extends HandlerInterceptorAdapter{
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {
		String userName = (String) (request.getSession()).getAttribute(SessionKey.USER_INFO);
		if (userName == null) {
			mav.setViewName("redirect:/login");
		}
	}
}
