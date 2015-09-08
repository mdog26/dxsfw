package com.dxsfw.pub.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dxsfw.common.constants.Constant;
import com.dxsfw.common.constants.GlobalValue;

public class AllInterceptor extends HandlerInterceptorAdapter {
	private static Logger log = LoggerFactory.getLogger(AllInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String action = request.getServletPath();
		if ("/pub/login".equals(action) || "/pub/openApp".equals(action) || "/pub/regMobile".equals(action)
				|| "/pub/getMobileCheckMsg".equals(action)) {
			//注册登录模块不需要token验证
			return super.preHandle(request, response, handler);
		} else if (action.equals("")){
			//测试代码 会在此逻辑
			GlobalValue.PATH_PICTURE = "D:/workspace/xzl/dxsfw_file/picture/";
			GlobalValue.PATH_USER_PICTURE = "D:/workspace/xzl/dxsfw_file/user/picture/";
			GlobalValue.PATH_JIANLI_PICTURE = "D:/workspace/xzl/dxsfw_file/jianli/picture/";
			GlobalValue.PATH_JIANLI_FUJIAN = "D:/workspace/xzl/dxsfw_file/jianli/fujian/";
			return super.preHandle(request, response, handler);
		}
		String token = request.getParameter("token");
		if (token != null) {
			Integer userid = GlobalValue.USER_TOKEN_MAP.get(token);
			if (userid != null) {
				return super.preHandle(request, response, handler);
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write("{\"status\": "+Constant.STATUS_ERROR_500+", \"msg\":\""+Constant.MSG_ERROR_TOKEN+"\",\"token\":\"\"}");
		log.debug("拦截请求 token失效 token : " + token);
		return false;
	}

	public boolean beforePreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//方便调试，输出请求json
		if ("application/json".equalsIgnoreCase(request.getContentType()) || "application/json; charset=UTF-8".equalsIgnoreCase(request.getContentType())) {
			BodyReaderHttpServletRequestWrapper requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
			String requestJson = requestWrapper.getRequestBodyAsString();
			log.debug("URL : " + request.getServletPath());
			log.debug("Request Json : " + requestJson);
			return super.preHandle(requestWrapper, response, handler);
		} else {
			return super.preHandle(request, response, handler);
		}
	}
}