package org.manas.eclipselink.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Priority;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.logging.SessionLog;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Priority(1)
public class DataSourceConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(final EntityManagerFactoryBuilder builder) {
		final LocalContainerEntityManagerFactoryBean ret = builder.dataSource(dataSource())
				.packages("org.manas.eclipselink.entity").persistenceUnit("YourPersistenceUnitName")
				.properties(initJpaProperties()).build();

		return ret;
	}

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		/*dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://example.com:5432/DatabaseName");
		dataSource.setUsername("user");
		dataSource.setPassword("password");*/
		
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:file:~/eclipselink-test");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
	
	private final Map<String, ?> initJpaProperties() {
		final Map<String, Object> ret = new HashMap<>();
		// Add any JpaProperty you are interested in and is supported by your
		// Database and JPA implementation
		ret.put("eclipselink.target-database", "org.eclipse.persistence.platform.database.H2Platform");
		ret.put("eclipselink.jdbc.batch-writing", "JDBC");
		ret.put(PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.FINE_LABEL);
		ret.put("eclipselink.weaving", "false");
		return ret;
	}
}
