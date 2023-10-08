package org.eoa.projectbudget;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.eoa.projectbudget.mapper")
public class ProjectBudgetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBudgetApplication.class, args);
	}

}
