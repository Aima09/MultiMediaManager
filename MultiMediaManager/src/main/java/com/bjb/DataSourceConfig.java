package com.bjb;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bjb.common.Constants;

@Configuration
public class DataSourceConfig {
	@Bean
    public DataSource dataSource(){
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(Constants.DATASOURCE_DRIVER);
        dataSource.setUrl(Constants.DATASOURCE_URL);
        dataSource.setUsername(Constants.DATASOURCE_USERNAME);
        dataSource.setPassword(Constants.DATASOURCE_PASSWORD);
        return dataSource;
    }
}
