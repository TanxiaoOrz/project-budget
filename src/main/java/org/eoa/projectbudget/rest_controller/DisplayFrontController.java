package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.dto.SearchListDto;
import org.eoa.projectbudget.dto.constraint.Constraint;
import org.eoa.projectbudget.entity.SearchList;
import org.eoa.projectbudget.exception.AuthorityException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.display.SearchService;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.utils.AuthorityUtils;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.factory.SearchListDtoOutFactory;
import org.eoa.projectbudget.vo.out.SearchListDtoOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 20:18
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: DisplayFrontController
 * @Description: 展示模块前端接口
 * @Version: 1.0
 */

@RestController
@Tag(name = "展示模块前端接口")
@RequestMapping("/api/v1/display/front")
@CrossOrigin
public class DisplayFrontController {

    @Autowired
    SearchService searchService;
    @Autowired
    CacheService cacheService;

    @Autowired
    SearchListDtoOutFactory searchListDtoOutFactory;
    @Autowired
    OrganizationService organizationService;

    private final long USER_ID_CACHE = 0L;

    @GetMapping(value = "/search/{id}")
    @Operation(summary = "获取列表构造体")
    public Vo<SearchListDtoOut> getSearchList(@RequestAttribute("HumanDto") HumanDto humanDto,
                                           @PathVariable Long id) throws EoaException {
        Boolean viewAble = cacheService.getCache(ChangeFlagUtils.SEARCH_DTO, id.toString(), humanDto.getDataId(), Boolean.class);
        SearchListDto searchListDto = null;
        if (viewAble == null) {
            searchListDto =  searchService.getSearchListDto(id, humanDto.getDataId());
            SearchList searchList = searchListDto.getSearchList();
            Constraint constraint = AuthorityUtils.getConstraint(searchList.getShareAuthority());
            viewAble = AuthorityUtils.checkAuthority(humanDto,organizationService.getHumanDto(searchList.getCreator(),null),constraint);
        }
        if (viewAble) {
            SearchListDtoOut out = cacheService.getCache(ChangeFlagUtils.SEARCH_DTO, id.toString(), USER_ID_CACHE, SearchListDtoOut.class);
            if (out == null) {
                searchListDto =searchListDto !=null?searchListDto: searchService.getSearchListDto(id, humanDto.getDataId());
                out = searchListDtoOutFactory.out(searchListDto);
                cacheService.setCache(ChangeFlagUtils.SEARCH_DTO, id.toString(), USER_ID_CACHE, out);
            }
            return new Vo<>(out);
        } else {
            throw new AuthorityException(humanDto.getDataId(), "展示列表", id, "查看");
        }

    }

}
