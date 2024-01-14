package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Content;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.content.ContentService;
import org.eoa.projectbudget.service.content.FileService;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.FilterUtils;
import org.eoa.projectbudget.utils.factory.ContentOutFactory;
import org.eoa.projectbudget.utils.factory.FileOutFactory;
import org.eoa.projectbudget.vo.in.ContentIn;
import org.eoa.projectbudget.vo.out.ContentOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * @Author: 张骏山
 * @Date: 2023/12/2 20:33
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: ContentBackController
 * @Description: 目录模块后端控制器
 * @Version: 1.0
 */

@RestController
@Tag(name = "目录模块后端接口")
@RequestMapping("/api/v1/content/back")
@CrossOrigin
public class ContentBackController {
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

    public static final long USER_ID_CACHE = 0L;


    @GetMapping(value = "/content")
    @Operation(summary = "获取目录清单")
    @SuppressWarnings("Duplicates")
    public Vo<List<ContentOut>> getContentList(@RequestAttribute("HumanDto") HumanDto humanDto,
                                               HttpServletRequest request) throws EoaException {
        FilterUtils<Content> filter = new FilterUtils<>(request.getParameterMap(), Content.class);
        List<ContentOut> outs;
        String method = filter.getDescription();
        ContentOut[] cache = cacheService.getCache(ChangeFlagUtils.CONTENT,method, USER_ID_CACHE, ContentOut[].class);
        if (cache == null) {
            outs = contentOutFactory.outs(contentService.getContentList(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.CONTENT,method, USER_ID_CACHE, outs);
        } else {
            outs = Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }

    @GetMapping(value = "/content/{id}")
    @Operation(summary = "获取目录")
    @SuppressWarnings("Duplicates")
    public Vo<ContentOut> getContent(@RequestAttribute("HumanDto")HumanDto humanDto,
                                     @PathVariable Long id) throws EoaException {
        ContentOut out = cacheService.getCache(ChangeFlagUtils.CONTENT,id.toString(), USER_ID_CACHE, ContentOut.class);
        if (out == null) {
            out = contentOutFactory.out(contentService.getContent(id, humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.CONTENT,id.toString(),USER_ID_CACHE,out);
        }
        return new Vo<>(out);

    }

    @PostMapping(value = "/content")
    @Operation(summary = "新建目录")
    public Vo<Long> newContent(@RequestAttribute("HumanDto")HumanDto humanDto,
                               @RequestBody ContentIn content) throws ParameterException {
        Long id = contentService.newContent(content.toEntity(null), humanDto.getDataId());
        if (id!=null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.CONTENT);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
    }


    @PutMapping(value = "/content/{id}")
    @Operation(summary = "修改目录")
    @Parameter(name = "id",description = "字段编号",required = true,in = ParameterIn.PATH)
    public Vo<String> updateContent(@RequestAttribute("HumanDto")HumanDto humanDto,
                                    @RequestBody ContentIn content,
                                    @PathVariable Long id) throws ParameterException {

        Integer update = contentService.updateContent(content.toEntity(id), humanDto.getDataId());
        if (update==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.CONTENT);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @DeleteMapping(value = "/content/{id}")
    @Operation(summary = "废弃目录")
    @Parameter(name = "id",description = "目录编号",required = true,in = ParameterIn.PATH)
    public Vo<String> dropContent(@RequestAttribute("HumanDto")HumanDto humanDto,
                                  @PathVariable Long id) {

        Integer deletes = contentService.dropContent(id, humanDto.getDataId());
        if (deletes==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.CONTENT);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
    }
}
