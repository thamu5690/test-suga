package org.vtop.CourseRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;


@SpringBootApplication(scanBasePackages="org.vtop",
						exclude={DataSourceAutoConfiguration.class,
						HibernateJpaAutoConfiguration.class,
						DataSourceTransactionManagerAutoConfiguration.class})

public class CourseRegistrationApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(CourseRegistrationApplication.class, args);
	}
}
