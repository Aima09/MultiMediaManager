package com.bjb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * 说明：在本demo中DEBUG级别启动会报错，但不影响使用，原因是springboot的安全机制
 *     本demo适应于war包、jar包部署
 * @author liuli
 *
 */
@EnableTransactionManagement//开启事物注解
@SpringBootApplication
public class Application {
	
	/**
	 * 程序主入口
	 * @param args
	 */
	public static void main(String[] args) {
		
		SpringApplication app=new SpringApplication(Application.class);
		app.run(args);
	}

}
