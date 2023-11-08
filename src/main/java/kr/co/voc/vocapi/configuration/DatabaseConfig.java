package kr.co.voc.vocapi.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @ClassName  DatabaseConfigMariadb.java
 * @Description Mysql DB 설정을 위한 설정 class
 * @author Minju Kim
 * @since 2021. 5. 28.
 *
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfigMariadb {

	@Bean
	@Qualifier("dataSource")
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.type(HikariDataSource.class)
				.build();
	}
}
