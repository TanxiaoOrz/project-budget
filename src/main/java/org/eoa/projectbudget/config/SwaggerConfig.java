package org.eoa.projectbudget.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * @Author 张骏山
 * @Date 2023/9/26 16:20
 * @PackageName: org.eoa.projectbudget.config
 * @ClassName: SwaggerConfig
 * @Description: TODO
 * @Version 1.0
 */

@Configuration
public class SwaggerConfig {
        @Bean
        public OpenAPI springOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("EOA ProjectBudget")
                            .contact(new Contact().name("张骏山").email("13671985248@163.com")
                                    .url("www.github.com/Tanxiao_Orz"))
                            .description("api接口文档")
                            .version("v0.0.1")
                            .license(new License().name("Apache 2.0")
                                    .url("https://www.apache.org/licenses/LICENSE-2.0")))
                    .externalDocs(new ExternalDocumentation()
                            .description("张骏山的毕业设计")
                            .url("https://www.cnblogs.com/jddreams/p/15922674.html"));
        }
}
