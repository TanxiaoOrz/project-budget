package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 张骏山
 * @Date: 2023/12/29 20:29
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: WorkflowFrontController
 * @Description: 工作流前端接口控制器
 * @Version: 1.0
 */
@RestController
@Tag(name = "工作流前端控制器")
@CrossOrigin
@RequestMapping("/api/v1/workflow/front")
public class WorkflowFrontController {
}
