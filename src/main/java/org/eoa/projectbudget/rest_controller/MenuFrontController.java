package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 22:59
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: MenuFrontController
 * @Description: 菜单模块前端接口
 * @Version: 1.0
 */

@RestController
@Tag(name = "菜单模块前端接口")
@RequestMapping("/api/v1/menu/front")
@CrossOrigin
public class MenuFrontController {
}
