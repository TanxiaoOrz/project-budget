package org.eoa.projectbudget.restController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eoa.projectbudget.service.ModuleService;
import org.eoa.projectbudget.vo.ModuleOut;
import org.eoa.projectbudget.vo.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/module")
@Tag(name = "模块应用")
public class ModuleController {
    @Autowired
    ModuleService moduleService;

    @Operation(summary = "获取所有模块信息")
    @GetMapping("")
    public Vo<List<ModuleOut>> getAllModule() {
        return new Vo<>(moduleService.getAll(null));
    }

}
