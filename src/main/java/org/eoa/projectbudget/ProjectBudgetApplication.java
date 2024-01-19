package org.eoa.projectbudget;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("org.eoa.projectbudget.mapper")
@EnableCaching
public class ProjectBudgetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBudgetApplication.class, args);
	}

}
