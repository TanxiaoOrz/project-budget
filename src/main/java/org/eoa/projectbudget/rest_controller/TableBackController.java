package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.service.table_module.ModuleService;
import org.eoa.projectbudget.utils.CreateOutUtils;
import org.eoa.projectbudget.vo.in.ModuleIn;
import org.eoa.projectbudget.vo.out.ModuleOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/table/back")
@Tag(name = "模块应用后端接口",description = "需要拥有表单权限的登录用户进行操作")
@CrossOrigin
public class TableBackController {
    @Autowired
    ModuleService moduleService;
    @Autowired
    CreateOutUtils createOutUtils;

    @Operation(summary = "获取所有模块信息")
    @GetMapping("/module")
    public Vo<List<ModuleOut>> getAllModule(@RequestAttribute("HumanDto") HumanDto humanDto) {
        return new Vo<>(createOutUtils.moduleOuts(moduleService.getAll(humanDto.getDataId())));
    }

    @Operation(summary = "创建模块应用")
    @PostMapping("/module")
    public Vo<String> newModule(@RequestAttribute("HumanDto") HumanDto humanDto, @RequestBody ModuleIn moduleIn) {
        // TODO
        return new Vo<>("");
    }

}
