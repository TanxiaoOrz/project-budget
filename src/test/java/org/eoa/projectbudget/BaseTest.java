package org.eoa.projectbudget;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;

/**
 * @Author: 张骏山
 * @Date: 2024/1/1 16:54
 * @PackageName: org.eoa.projectbudget
 * @ClassName: BaseTest
 * @Description: 无需Springboot的基本测试
 * @Version: 1.0
 */
public class BaseTest {

    static String test = "1";
    static Object testO = "0";

    static void test(String s) {
        System.out.println("s");
    }

    static void test(Object o) {
        System.out.println("o");
        System.out.println("o.getClass() = " + o.getClass());
    }

    public static void main(String[] args) {
            //数据源
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
            hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/eoa_build");
            hikariConfig.setUsername("eoa");
            hikariConfig.setPassword("eoa");
            //设置可以获取tables remarks信息
            hikariConfig.addDataSourceProperty("useInformationSchema", "true");
            hikariConfig.setMinimumIdle(2);
            hikariConfig.setMaximumPoolSize(5);
            DataSource dataSource = new HikariDataSource(hikariConfig);
            //生成配置

            EngineConfig engineConfig = EngineConfig.builder()
                    //生成文件路径
                    .fileOutputDir("D:\\BaiduSyncdisk\\论文文档\\需求分析")
                    //打开目录
                    .openOutputDir(true)
                    //文件类型
                    .fileType(EngineFileType.WORD)
                    //生成模板实现
                    .produceType(EngineTemplateType.freemarker)
                    //自定义文件名称
                    .fileName("数据分析").build();


            //忽略表前缀
            ArrayList<String> ignorePrefix = new ArrayList<>();
            ignorePrefix.add("form_");
            ignorePrefix.add("main_");
            ProcessConfig processConfig = ProcessConfig.builder()
                    //指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
                    //根据名称指定表生成
                    .designatedTableName(new ArrayList<>())
                    //根据表前缀生成
                    .designatedTablePrefix(new ArrayList<>())
                    //根据表后缀生成
                    .designatedTableSuffix(new ArrayList<>())
                    //忽略表名
                    .ignoreTableName(new ArrayList<>())
                    //忽略表前缀
                    .ignoreTablePrefix(ignorePrefix)
                    //忽略表后缀
                    .ignoreTableSuffix(new ArrayList<>()).build();
            //配置
            Configuration config = Configuration.builder()
                    //版本
                    .version("1.0.0")
                    //描述
                    .description("数据库设计文档生成")
                    //数据源
                    .dataSource(dataSource)
                    //生成配置
                    .engineConfig(engineConfig)
                    //生成配置
                    .produceConfig(processConfig)
                    .build();
            //执行生成
            new DocumentationExecute(config).execute();

    }
}
