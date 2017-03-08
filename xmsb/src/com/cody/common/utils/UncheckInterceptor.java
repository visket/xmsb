package com.cody.common.utils;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UncheckInterceptor extends HandlerInterceptorAdapter {

	//private List<String> uncheckUrls;
	private String uncheckUrl;
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler){
		
		String requestUrl = request.getRequestURI();
		
		/*try {
			response.sendRedirect(request.getContextPath()+"/system/unit/unitRegister");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;*/
		//if(uncheckUrls.contains(requestUrl))
		/*if("/system/unit".equals(requestUrl))	
		    return true;
		else
			return true;*/
		return true;
	}
	
}
