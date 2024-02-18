package org.eoa.projectbudget;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("org.eoa.projectbudget.mapper")
@EnableCaching
@EnableTransactionManagement
public class ProjectBudgetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBudgetApplication.class, args);
	}

}
