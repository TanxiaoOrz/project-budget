package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Content;
import org.eoa.projectbudget.entity.File;
import org.eoa.projectbudget.exception.AuthorityException;
import org.eoa.projectbudget.exception.AuthoritySolveException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.content.ContentService;
import org.eoa.projectbudget.service.content.FileService;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.utils.AuthorityUtils;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.FilterUtils;
import org.eoa.projectbudget.utils.factory.ContentOutFactory;
import org.eoa.projectbudget.utils.factory.FileOutFactory;
import org.eoa.projectbudget.vo.constraint.Constraint;
import org.eoa.projectbudget.vo.in.FileIn;
import org.eoa.projectbudget.vo.out.ContentOut;
import org.eoa.projectbudget.vo.out.FileOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 14:15
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: ContentFrontController
 * @Description: 目录模块前端接口
 * @Version: 1.0
 **/

@RestController
@Tag(name = "目录模块前端接口")
@RequestMapping("/api/v1/content/front")
@CrossOrigin
public class ContentFrontController {

    @Autowired
    FileService fileService;
    @Autowired
    ContentService contentService;
    @Autowired
    CacheService cacheService;
    @Autowired
    ContentOutFactory contentOutFactory;
    @Autowired
    FileOutFactory fileOutFactory;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    ChangeFlagUtils changeFlagUtils;

    private final long USER_ID_CACHE = 0L;


    @PostMapping(value = "/upload/content")
    @Operation(summary = "上传文件,目录模块上传")
    @Parameter(name = "leadContent",description = "上级目录",required = true,in = ParameterIn.QUERY)
    // 此处的@RequestParam中的file名应与前端upload组件中的name的值保持一致
    public Vo<String> uploadC(@RequestPart("file") MultipartFile multipartFile
                            ,@RequestAttribute("HumanDto")HumanDto humanDto
                            ,@RequestParam("leadContent") Long leadContent) throws EoaException, IOException {
        Content content = contentService.getContent(leadContent, humanDto.getDataId());
        if (AuthorityUtils.checkAuthority(humanDto,organizationService.getHumanDto(content.getCreator(),null),AuthorityUtils.getConstraint(content.getDefaultCreate()))) {
            String upload = fileService.upload(multipartFile, humanDto.getDataId());
            return new Vo<>(upload);
        } else
            throw new AuthorityException(humanDto.getDataId(), "Content", leadContent,"上传文件");
    }
    @PostMapping(value = "/upload/form")
    @Operation(summary = "上传文件,表单模块上传,返回新建的文件id")
    @Parameter(name = "leadContent",description = "上级目录",required = true,in = ParameterIn.QUERY)
    public Vo<Long> uploadF(@RequestPart("file") MultipartFile multipartFile
            ,@RequestAttribute("HumanDto")HumanDto humanDto
            ,@RequestParam("leadContent") Long leadContent) throws EoaException, IOException {

        String upload = fileService.upload(multipartFile, humanDto.getDataId());
        File file = new FileIn().setFileName(multipartFile.getName()).setLeadContent(leadContent).setFileRoute(upload).toEntity(null);
        Long id = contentService.newFile(file, humanDto.getDataId());
        return new Vo<>(id);

    }

    @GetMapping(value = "/content")
    @Operation(summary = "获取目录清单")
    @SuppressWarnings("Duplicates")
    public Vo<List<ContentOut>> getContentList(@RequestAttribute("HumanDto")HumanDto humanDto,
                                               HttpServletRequest request) throws EoaException {
        FilterUtils<Content> filter = new FilterUtils<>(request.getParameterMap(), Content.class);
        List<ContentOut> outs;
        String method = filter.getDescription();
        ContentOut[] cache = cacheService.getCache(ChangeFlagUtils.CONTENT,method, humanDto.getDataId(), ContentOut[].class);
        if (cache == null) {
            outs = contentOutFactory.outs(contentService.getContentList(filter.getWrapper(), humanDto.getDataId())).stream().filter(contentOut -> {
                try {
                    Constraint constraint = AuthorityUtils.getConstraint(contentOut.getDefaultShare());
                    return AuthorityUtils.checkAuthority(humanDto,organizationService.getHumanDto(contentOut.getCreator(),null),constraint);
                } catch (AuthoritySolveException | ParameterException e) {
                    return false;
                }
            }).toList();
            cacheService.setCache(ChangeFlagUtils.CONTENT,method, humanDto.getDataId(), outs);
        } else {
            outs = Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }

    @GetMapping(value = "/content/{id}")
    @Operation(summary = "获取具体目录")
    @Parameter(name = "id",description = "数据编号",required = true,in = ParameterIn.PATH)
    @SuppressWarnings("Duplicates")
    public Vo<ContentOut> getContent(@RequestAttribute("HumanDto")HumanDto humanDto,
                               @PathVariable Long id) throws EoaException {
        ContentOut out = cacheService.getCache(ChangeFlagUtils.CONTENT,id.toString(), USER_ID_CACHE, ContentOut.class);
        if (out == null) {
            out = contentOutFactory.out(contentService.getContent(id, humanDto.getDataId()));
        }
        if (AuthorityUtils.checkAuthority(humanDto,organizationService.getHumanDto(out.getCreator(),null),AuthorityUtils.getConstraint(out.getDefaultShare())))
            return new Vo<>(out);
        else
            throw new AuthorityException(humanDto.getDataId(), "Content", id, "查看");
    }

    @GetMapping(value = "/file")
    @Operation(summary = "获取文件清单")
    @Parameter(name = "leadContent",description = "上级目录",required = true,in = ParameterIn.QUERY)
    @Parameter(name = "isDeprecated",description = "是否废弃",required = false,in = ParameterIn.QUERY)
    public Vo<List<FileOut>> getFileList(@RequestAttribute("HumanDto")HumanDto humanDto,
                                            HttpServletRequest request) throws EoaException {
        FilterUtils<File> filter = new FilterUtils<>(request.getParameterMap(), File.class);
        List<FileOut> outs;
        String method = filter.getDescription();
        FileOut[] cache = cacheService.getCache(ChangeFlagUtils.FILE,method, humanDto.getDataId(), FileOut[].class);
        if (cache == null) {
            outs = fileOutFactory.outs(contentService.getFileList(filter.getWrapper(), humanDto.getDataId())).stream().filter(fileOut -> {
                try {
                    Constraint constraint = AuthorityUtils.getConstraint(fileOut.getViewAuthority());
                    return AuthorityUtils.checkAuthority(humanDto,organizationService.getHumanDto(fileOut.getCreator(),null),constraint);
                } catch (AuthoritySolveException | ParameterException e) {
                    return false;
                }
            }).toList();
            cacheService.setCache(ChangeFlagUtils.FILE,method, humanDto.getDataId(), outs);
        } else {
            outs = Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }

    @GetMapping(value = "/file/{id}")
    @Operation(summary = "获取文件清单")
    @Parameter(name = "id",description = "数据编号",required = true,in = ParameterIn.PATH)
    public Vo<FileOut> getFile(@RequestAttribute("HumanDto")HumanDto humanDto,
                               @PathVariable Long id) throws EoaException {
        FileOut out = cacheService.getCache(ChangeFlagUtils.FILE,id.toString(), USER_ID_CACHE, FileOut.class);
        if (out == null) {
            out = fileOutFactory.out(contentService.getFile(id, humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.FILE,id.toString(),USER_ID_CACHE,FileOut.class);
        }
        if (AuthorityUtils.checkAuthority(humanDto,organizationService.getHumanDto(out.getCreator(),null),AuthorityUtils.getConstraint(out.getViewAuthority())))
            return new Vo<>(out);
        else
            throw new AuthorityException(humanDto.getDataId(), "File", id, "查看");
    }

    @PostMapping(value = "/file")
    @Operation(summary = "新建文件")
    public Vo<Long> newFile(@RequestAttribute("HumanDto")HumanDto humanDto,
                            @RequestBody FileIn file) throws AuthoritySolveException, ParameterException, AuthorityException {
        file.checkSelf();
        Content content = contentService.getContent(file.getLeadContent(), humanDto.getDataId());
        if (AuthorityUtils.checkAuthority(humanDto,organizationService.getHumanDto(content.getCreator(),null),AuthorityUtils.getConstraint(content.getDefaultCreate()))) {
            Long id = contentService.newFile(file.toEntity(null), humanDto.getDataId());
            if (id!=null) {
                changeFlagUtils.freshDate(ChangeFlagUtils.FILE);
                return new Vo<>(id);
            } else
                return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
        } else
            throw new AuthorityException(humanDto.getDataId(), "Content", content.getDataId(), "创建");
    }


    @PutMapping(value = "/file/{id}")
    @Operation(summary = "修改文件")
    @Parameter(name = "id",description = "字段编号",required = true,in = ParameterIn.PATH)
    public Vo<String> updateFile(@RequestAttribute("HumanDto")HumanDto humanDto,
                            @RequestBody FileIn file,
                            @PathVariable Long id) throws AuthorityException, ParameterException, AuthoritySolveException {
        File old = contentService.getFile(id, humanDto.getDataId());
        if (AuthorityUtils.checkAuthority(humanDto,organizationService.getHumanDto(old.getCreator(),null),AuthorityUtils.getConstraint(old.getEditAuthority()))) {
            Integer update = contentService.updateFile(file.toEntity(id), humanDto.getDataId());
            if (update==1) {
                changeFlagUtils.freshDate(ChangeFlagUtils.FILE);
                return new Vo<>("修改成功");
            } else
                return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
        } else
            throw new AuthorityException(humanDto.getDataId(), "File", id, "编辑");
    }

    @DeleteMapping(value = "/file/{id}")
    @Operation(summary = "废弃文件")
    @Parameter(name = "id",description = "字段编号",required = true,in = ParameterIn.PATH)
    public Vo<String> dropFile(@RequestAttribute("HumanDto")HumanDto humanDto,
                                @PathVariable Long id) throws AuthoritySolveException, ParameterException, AuthorityException {
        File old = contentService.getFile(id, humanDto.getDataId());
        if (old == null) {
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
        }
        if (AuthorityUtils.checkAuthority(humanDto,organizationService.getHumanDto(old.getCreator(),null),AuthorityUtils.getConstraint(old.getDeleteAuthority()))) {
            Integer deletes = contentService.dropFile(id, humanDto.getDataId());
            if (deletes==1) {
                changeFlagUtils.freshDate(ChangeFlagUtils.FILE);
                return new Vo<>("删除成功");
            } else
                return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");

        }else
            throw new AuthorityException(humanDto.getDataId(), "File", id, "删除");
    }
}
