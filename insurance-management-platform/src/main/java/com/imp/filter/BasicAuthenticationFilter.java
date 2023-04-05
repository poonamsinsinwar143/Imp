package com.imp.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class BasicAuthenticationFilter implements Filter {
	@Value("${imp.security.username}")
	private String username;
	
	@Value("${imp.security.password}")
	private String password;
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		
		if(!httpReq.getRequestURI().contains("swagger") && !httpReq.getRequestURI().contains("api-docs")) {
			String authorization = httpReq.getHeader("Authorization");
			if(authorization== null) {
				sendUnauthorizedResponse(response);
				return;
			}
			String encodedString = authorization.substring(6);
			byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
			String decodedString = new String(decodedBytes);
			if(!decodedString.equals(username+":"+password)) {
				sendUnauthorizedResponse(response);
				return;
			}
		}
		chain.doFilter(request, response);
	}
	
	private void sendUnauthorizedResponse(ServletResponse response) throws IOException {
		HttpServletResponse httpRes = (HttpServletResponse) response;
		httpRes.setContentType("application/json");
		httpRes.setCharacterEncoding("UTF-8");
		httpRes.setStatus(401);
		PrintWriter out = httpRes.getWriter();
		out.print("Username or password is incorrect!");
		out.flush();
	}
	
}
