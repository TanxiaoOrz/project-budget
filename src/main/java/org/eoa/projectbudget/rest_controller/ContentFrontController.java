package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Content;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.service.content.FileService;
import org.eoa.projectbudget.vo.in.FileIn;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 14:15
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: ContentFrontController
 * @Description: TODO
 * @Version: 1.0
 **/

@RestController
@Tag(name = "表单目录前端接口")
@RequestMapping("/api/v1/content/front")
public class ContentFrontController {

    @Autowired
    FileService fileService;

    @PostMapping(value = "/upload")
    @Operation(summary = "上传文件")
    // 此处的@RequestParam中的file名应与前端upload组件中的name的值保持一致
    public Vo<String> upload(@RequestPart("file") MultipartFile multipartFile
                            ,@RequestAttribute("HumanDto")HumanDto humanDto) throws EoaException, IOException {
        return new Vo<>(fileService.upload(multipartFile, humanDto.getDataId()));
    }

    @GetMapping(value = "/content")
    @Operation(summary = "获取目录清单")
    public Vo<List<Content>> getContentList(@RequestAttribute("HumanDto")HumanDto humanDto,
                                            HttpServletRequest request) {
        // TODO
        return null;
    }

    @GetMapping(value = "/file")
    @Operation(summary = "获取文件清单")
    public Vo<List<Content>> getFileList(@RequestAttribute("HumanDto")HumanDto humanDto,
                                            HttpServletRequest request) {
        // TODO
        return null;
    }

    @GetMapping(value = "/file/{id}")
    @Operation(summary = "获取文件清单")
    public Vo<Content> getFile(@RequestAttribute("HumanDto")HumanDto humanDto,
                                     HttpServletRequest request,
                                     @PathVariable String id) {
        // TODO
        return null;
    }

    @PostMapping(value = "/file")
    @Operation(summary = "新建文件")
    public Vo<Long> newFile(@RequestAttribute("HumanDto")HumanDto humanDto,
                            @RequestBody FileIn file) {
        // TODO
        return null;
    }


    @PutMapping(value = "/file/{id}")
    @Operation(summary = "修改文件")
    @Parameter(name = "id",description = "字段编号",required = true,in = ParameterIn.PATH)
    public Vo<Long> updateFile(@RequestAttribute("HumanDto")HumanDto humanDto,
                            @RequestBody FileIn file,
                            @PathVariable Long id) {
        // TODO
        return null;
    }

    @DeleteMapping(value = "/file/{id}")
    @Operation(summary = "废弃文件")
    @Parameter(name = "id",description = "字段编号",required = true,in = ParameterIn.PATH)
    public Vo<Integer> dropFile(@RequestAttribute("HumanDto")HumanDto humanDto,
                                @PathVariable Long id) {
        // TODO
        return null;
    }
}
