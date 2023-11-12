package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.service.table_module.ModuleService;
import org.eoa.projectbudget.utils.CreateOutUtils;
import org.eoa.projectbudget.vo.out.ModuleOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/module/back")
@Tag(name = "模块应用后端接口")
@CrossOrigin
public class TableBackController {
    @Autowired
    ModuleService moduleService;
    @Autowired
    CreateOutUtils createOutUtils;

    @Operation(summary = "获取所有模块信息")
    @GetMapping("")
    public Vo<List<ModuleOut>> getAllModule(@RequestAttribute("HumanDto") HumanDto humanDto) {
        return new Vo<>(createOutUtils.moduleOuts(moduleService.getAll(humanDto.getDataId())));
    }

}
