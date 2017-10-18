package com.bjb.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 抽象过滤器
 * 用于过滤掉不需拦截的请求及供其他过滤器继承
 * @author liuli
 * 
 */
public abstract class AbstractFilter implements Filter  {

	private FilterConfig config;
	
	protected static final Logger logger = Logger.getRootLogger();
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		logger.info("abstract过滤器执行过滤业务...");
		try {
			doFilter(request, response, chain);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 抽象方法
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
	protected abstract void doFilter(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws IOException, ServletException, SQLException;

	/**
	 * 初始化
	 * @param config
	 */
	protected abstract void doInit(FilterConfig config);
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
		this.config = config;
		doInit(config);
	}
	
	public FilterConfig getConfig() {
		return config;
	}
	
	@Override
	public void destroy() {}
	
}
