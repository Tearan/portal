package com.example.demo.config;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by marta on 09.07.17.
 */

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class DBConfig {
    @Autowired
    private Environment env;

    @Bean //Create entity manager factory bean.
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setJtaDataSource(getDataSource());
        lef.setPersistenceUnitName("myJpaPersistenceUnit");
        lef.setJpaVendorAdapter(getJpaVendorAdapter());
//        lef.setJpaProperties(jpaProperties());
        lef.setPackagesToScan("com.example.demo");
        return lef;
    }

    @Bean
    public HibernateJpaVendorAdapter getJpaVendorAdapter() { //TODO Co to?
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform(env.getProperty("hibernate.dialect"));
        adapter.setGenerateDdl(env.getProperty("hibernate.generateDdl", Boolean.class));
        adapter.setShowSql(env.getProperty("hibernate.show_sql", Boolean.class));
        return adapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory((EntityManagerFactory) entityManagerFactory());
        return txManager;
    }

    @Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));

		return dataSource;
	}

//    private Properties jpaProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
//        return properties;
//    }

}
