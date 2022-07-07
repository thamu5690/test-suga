package org.vtop.CourseRegistration;

import java.sql.SQLException;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import oracle.ucp.jdbc.PoolDataSource;

/*import oracle.jdbc.pool.OracleDataSource;
*/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "academicsEntityManagerFactory",
		transactionManagerRef = "academicsTransactionManager"
		)
public class AcademicsDataConfig {
	
	@Bean
	PlatformTransactionManager academicsTransactionManager() throws SQLException {
       JpaTransactionManager txManager = new JpaTransactionManager();
        JpaDialect jpaDialect = new HibernateJpaDialect();
        txManager.setEntityManagerFactory(academicsEntityManagerFactory().getObject());
        txManager.setJpaDialect(jpaDialect);
		return txManager;
	}

	@PersistenceContext(unitName = "academics")
	@Bean
	LocalContainerEntityManagerFactoryBean academicsEntityManagerFactory() throws SQLException {

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.Oracle12cDialect");
				
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		factoryBean.setDataSource(academicsDataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setPersistenceUnitName("academics");
		factoryBean.setPackagesToScan("org.vtop");

		return factoryBean;
	}

	

	@Primary
	@Bean
	PoolDataSource academicsDataSource() throws SQLException {
	//OracleDataSource academicsDataSource() throws SQLException {
		
		
		PoolDataSource  pds = PoolDataSourceFactory.getPoolDataSource();
			
		pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
        pds.setURL("jdbc:oracle:thin:@//172.16.0.59:1521/orcldb.localdomain");		
		pds.setUser("reguser");
		pds.setPassword("reg#space*1");
        //Override any pool properties.
        
        pds.setInitialPoolSize(3);
        pds.setMinPoolSize(3);
		pds.setInactiveConnectionTimeout(600);
		
		return pds;
	}


}
