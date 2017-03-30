package cn.tedu.note.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.tedu.note.service.UserService;

public class ACLFilter implements Filter{
	
	private ServletContext sc;
	private ApplicationContext ctx;
	private UserService userService;
	
	public void init(FilterConfig cfg)
		throws ServletException {
 
		sc=	cfg.getServletContext();
		ctx=WebApplicationContextUtils
			.getWebApplicationContext(sc);
		userService=ctx.getBean(
				"userService",
				UserService.class);
		
	}
	public void doFilter(
			ServletRequest req, 
			ServletResponse res, 
			FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=
			(HttpServletRequest) req;
		HttpServletResponse response =
			(HttpServletResponse) res;

		String path=request.getRequestURI();
		
		path = path.substring(
			path.indexOf('/', 1));
		
		
		if(path.matches(".*/edit\\.html$")){
			checkLogin(request,response,chain);
			return;
		}
		
		if(path.matches(".(note).*\\.do$")){
			checkDotDo(request,response,chain);
			return;
		}
		
		chain.doFilter(request, response); 
	}
	private void checkDotDo(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		String token = getCookie(request, "token");
		String userId= getCookie(request,"userId");
		
		if(userService.checkToken(userId, token)){
			chain.doFilter(request, response);
			return;
		}
		
		String json="{\"state\":1,\"message\":\"保存成功\"}";
		response.setCharacterEncoding("utf-8");
		response.setContentType(
			"application/json;charset=UTF-8");
		response.getWriter().println(json);
	}
		
	private String getCookie(HttpServletRequest request, 
			String cookieName) {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if(cookieName.equals(
						cookie.getName())){
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	private void checkLogin(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		String token = getCookie(request, "token");
		String userId= getCookie(request,"userId");
		if(userService.checkToken(userId, token)){
			chain.doFilter(request, response);
			return;
		}
		String path=request.getContextPath()+
			"/log_in.html";
		response.sendRedirect(path);
	}
	
	public void destroy() {
	}
}