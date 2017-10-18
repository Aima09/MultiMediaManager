package com.bjb.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


/**
 * 处理跨域过滤器
 * @author wujie
 *
 */
@Component
public class AcrossFilter extends AbstractFilter {


	@Override
	protected void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

	    response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Allow-Credentials", "true");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers","withCredentials,SESSIONID,Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");
	   
	    logger.info("across跨域过滤器执行过滤业务...");
	    chain.doFilter(request, response);
	}
	
	@Override
	protected void doInit(FilterConfig config) {
		
		logger.info("across跨域过滤器被创建！");
	}
	
}
