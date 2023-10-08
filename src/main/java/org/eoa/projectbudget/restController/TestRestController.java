package org.eoa.projectbudget.restController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @Author 张骏山
 * @Date 2023/9/26 16:20
 * @PackageName: org.eoa.projectbudget.config
 * @ClassName: TestRestController
 * @Description: 测试swagger接口描述和controller接口功能
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/test")
@Tag(name = "测试控制器")
public class TestRestController {

//    private final Logger log = LoggerFactory.getLogger("TCPServer");

    public Integer beforeProcess() {
        return 0;
    }

    @Operation(summary = "动作测试")
    @PostMapping("/noParamter")
    public int noParamter() {
        return 1;
    }

    @Operation(summary = "普通body请求+Param+Header+Path")
    @Parameters({
            @Parameter(name = "id", description = "文件id", in = ParameterIn.PATH),
            @Parameter(name = "token", description = "请求token", required = true, in = ParameterIn.HEADER),
            @Parameter(name = "name", description = "文件名称", required = true, in = ParameterIn.QUERY)
    })
    @PostMapping("/bodyParamHeaderPath/{id}")
    public Integer bodyParamHeaderPath(@PathVariable("id") String id, @RequestHeader("token") String token,
            @RequestParam("name") String name) {
        return 1;
    }

//    @Operation(summary = "日志测试服务")
//    @Parameters({
//            @Parameter(name = "type",description = "日志选择",in = ParameterIn.QUERY)
//    })
//    @PostMapping("/bodyLog")
//    public Integer bodyLog(@RequestParam("type") Integer type) {
//        log.info("{}",type);
//        return 1;
//    }

}
