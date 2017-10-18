package com.bjb;

import org.apache.log4j.Logger;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bjb.filter.AcrossFilter;
import com.bjb.filter.SessionFilter;

/**
 * 过滤器链配置
 * @author liuli
 *
 */
@Configuration
public class WebConfig {
	
	public static final Logger logger = Logger.getRootLogger();

	/**
	 * 配置跨域处理过滤器为第一执行，并配置拦截规则为.do和.show
	 * @param acrossFilter
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBeanAcrossFilter(AcrossFilter acrossFilter){//AcrossFilter acrossFilter
		
		logger.info("AcrossFilter过滤器装配.....");
		
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(acrossFilter);  
        filterRegistrationBean.setEnabled(true);  
        //配置拦截路径后缀
        filterRegistrationBean.addUrlPatterns("*.do", "*.show");
        filterRegistrationBean.setOrder(1);//说明过滤器的执行顺序
        
        return filterRegistrationBean;
	}
	
	/**
	 * 配置session过滤器为第二执行，并配置拦截规则为.do,.show
	 * @param sessionFilter
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBeanSessionFilter(SessionFilter sessionFilter){
		
		logger.info("SessionFilter过滤器装配.....");
		
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(sessionFilter);  
        filterRegistrationBean.setEnabled(true);  
        //配置拦截路径后缀
        filterRegistrationBean.addUrlPatterns("*.do", "*.show");
        filterRegistrationBean.setOrder(2);//说明过滤器的执行顺序
        
        return filterRegistrationBean;
	}
}
