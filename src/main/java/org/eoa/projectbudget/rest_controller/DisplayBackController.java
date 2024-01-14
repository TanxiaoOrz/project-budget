package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Charts;
import org.eoa.projectbudget.entity.SearchList;
import org.eoa.projectbudget.entity.SearchListColumn;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.display.ChartsService;
import org.eoa.projectbudget.service.display.SearchService;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.FilterUtils;
import org.eoa.projectbudget.utils.factory.ChartsOutFactory;
import org.eoa.projectbudget.utils.factory.SearchListColumnOutFactory;
import org.eoa.projectbudget.utils.factory.SearchListOutFactory;
import org.eoa.projectbudget.vo.in.ChartsIn;
import org.eoa.projectbudget.vo.in.SearchListColumnIn;
import org.eoa.projectbudget.vo.in.SearchListIn;
import org.eoa.projectbudget.vo.out.ChartsOut;
import org.eoa.projectbudget.vo.out.SearchListColumnOut;
import org.eoa.projectbudget.vo.out.SearchListOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 19:13
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: DisplayBackController
 * @Description: 展示模块后端控制器
 * @Version: 1.0
 */

@RestController
@Tag(name = "展示模块后端接口")
@RequestMapping("/api/v1/display/back")
@CrossOrigin
public class DisplayBackController {

    @Autowired
    ChartsService chartsService;
    @Autowired
    SearchService searchService;
    @Autowired
    CacheService cacheService;

    @Autowired
    ChangeFlagUtils changeFlagUtils;

    @Autowired
    SearchListOutFactory searchListOutFactory;
    @Autowired
    SearchListColumnOutFactory searchListColumnOutFactory;
    @Autowired
    ChartsOutFactory chartsOutFactory;

    private final long USER_ID_CACHE = 0L;

    @GetMapping(value = "/search")
    @Operation(summary = "获取列表")
    @SuppressWarnings("Duplicates")
    public Vo<List<SearchListOut>> getSearchListList(@RequestAttribute("HumanDto") HumanDto humanDto,
                                                     HttpServletRequest request) throws EoaException {
        FilterUtils<SearchList> filter = new FilterUtils<>(request.getParameterMap(), SearchList.class);
        List<SearchListOut> outs;
        String method = filter.getDescription();
        SearchListOut[] cache = cacheService.getCache(ChangeFlagUtils.SEARCH,method, USER_ID_CACHE, SearchListOut[].class);
        if (cache == null) {
            outs = searchListOutFactory.outs(searchService.getSearchListList(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.SEARCH,method, USER_ID_CACHE, outs);
        } else {
            outs = Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }

    @GetMapping(value = "/search/{id}")
    @Operation(summary = "获取列表")
    @SuppressWarnings("Duplicates")
    public Vo<SearchListOut> getSearchList(@RequestAttribute("HumanDto")HumanDto humanDto,
                                           @PathVariable Long id) throws EoaException {
        SearchListOut out = cacheService.getCache(ChangeFlagUtils.SEARCH,id.toString(), USER_ID_CACHE, SearchListOut.class);
        if (out == null) {
            out = searchListOutFactory.out(searchService.getSearchList(id, humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.SEARCH,id.toString(),USER_ID_CACHE,out);
        }
        return new Vo<>(out);

    }

    @PostMapping(value = "/search")
    @Operation(summary = "新建列表")
    public Vo<Long> newSearchList(@RequestAttribute("HumanDto")HumanDto humanDto,
                                  @RequestBody SearchListIn searchList) throws ParameterException {
        Long id = searchService.newSearchList(searchList.toEntity(null), humanDto.getDataId());
        if (id!=null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.SEARCH);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
    }


    @PutMapping(value = "/search/{id}")
    @Operation(summary = "修改列表")
    @Parameter(name = "id",description = "字段编号",required = true,in = ParameterIn.PATH)
    public Vo<String> updateSearchList(@RequestAttribute("HumanDto")HumanDto humanDto,
                                       @RequestBody SearchListIn searchList,
                                       @PathVariable Long id) throws ParameterException {

        Integer update = searchService.updateSearchList(searchList.toEntity(id), humanDto.getDataId());
        if (update==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.SEARCH);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @DeleteMapping(value = "/search/{id}")
    @Operation(summary = "废弃列表")
    @Parameter(name = "id",description = "列表编号",required = true,in = ParameterIn.PATH)
    public Vo<String> dropSearchList(@RequestAttribute("HumanDto")HumanDto humanDto,
                                     @PathVariable Long id) {

        Integer deletes = searchService.deleteSearchList(id, humanDto.getDataId());
        if (deletes==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.SEARCH);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
    }

    @GetMapping(value = "/column")
    @Operation(summary = "获取列表字段")
    @SuppressWarnings("Duplicates")
    public Vo<List<SearchListColumnOut>> getSearchListColumnList(@RequestAttribute("HumanDto") HumanDto humanDto,
                                                                 HttpServletRequest request) throws EoaException {
        FilterUtils<SearchListColumn> filter = new FilterUtils<>(request.getParameterMap(), SearchListColumn.class);
        List<SearchListColumnOut> outs;
        String method = filter.getDescription();
        SearchListColumnOut[] cache = cacheService.getCache(ChangeFlagUtils.SEARCH_COLUMN,method, USER_ID_CACHE, SearchListColumnOut[].class);
        if (cache == null) {
            outs = searchListColumnOutFactory.outs(searchService.getSearchListColumnList(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.SEARCH_COLUMN,method, USER_ID_CACHE, outs);
        } else {
            outs = Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }

    @GetMapping(value = "/column/{id}")
    @Operation(summary = "获取列表字段")
    @SuppressWarnings("Duplicates")
    public Vo<SearchListColumnOut> getSearchListColumn(@RequestAttribute("HumanDto")HumanDto humanDto,
                                                       @PathVariable Long id) throws EoaException {
        SearchListColumnOut out = cacheService.getCache(ChangeFlagUtils.SEARCH_COLUMN,id.toString(), USER_ID_CACHE, SearchListColumnOut.class);
        if (out == null) {
            out = searchListColumnOutFactory.out(searchService.getSearchListColumn(id, humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.SEARCH_COLUMN,id.toString(),USER_ID_CACHE,out);
        }
        return new Vo<>(out);

    }

    @PostMapping(value = "/column")
    @Operation(summary = "新建列表字段")
    public Vo<Long> newSearchListColumn(@RequestAttribute("HumanDto")HumanDto humanDto,
                                        @RequestBody SearchListColumnIn searchListColumn) throws ParameterException {
        Long id = searchService.newSearchListColumn(searchListColumn.toEntity(null), humanDto.getDataId());
        if (id!=null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.SEARCH_COLUMN);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
    }


    @PutMapping(value = "/column/{id}")
    @Operation(summary = "修改列表字段")
    @Parameter(name = "id",description = "字段编号",required = true,in = ParameterIn.PATH)
    public Vo<String> updateSearchListColumn(@RequestAttribute("HumanDto")HumanDto humanDto,
                                             @RequestBody SearchListColumnIn searchListColumn,
                                             @PathVariable Long id) throws ParameterException {

        Integer update = searchService.updateSearchListColumn(searchListColumn.toEntity(id), humanDto.getDataId());
        if (update==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.SEARCH_COLUMN);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @DeleteMapping(value = "/column/{id}")
    @Operation(summary = "废弃列表字段")
    @Parameter(name = "id",description = "列表字段编号",required = true,in = ParameterIn.PATH)
    public Vo<String> dropSearchListColumn(@RequestAttribute("HumanDto")HumanDto humanDto,
                                           @PathVariable Long id) {

        Integer deletes = searchService.deleteSearchListColumn(id, humanDto.getDataId());
        if (deletes==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.SEARCH_COLUMN);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
    }

    @GetMapping(value = "/charts")
    @Operation(summary = "获取图表")
    @SuppressWarnings("Duplicates")
    public Vo<List<ChartsOut>> getChartsList(@RequestAttribute("HumanDto") HumanDto humanDto,
                                             HttpServletRequest request) throws EoaException {
        FilterUtils<Charts> filter = new FilterUtils<>(request.getParameterMap(), Charts.class);
        List<ChartsOut> outs;
        String method = filter.getDescription();
        ChartsOut[] cache = cacheService.getCache(ChangeFlagUtils.CHARTS,method, USER_ID_CACHE, ChartsOut[].class);
        if (cache == null) {
            outs = chartsOutFactory.outs(chartsService.getChartsList(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.CHARTS,method, USER_ID_CACHE, outs);
        } else {
            outs = Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }

    @GetMapping(value = "/charts/{id}")
    @Operation(summary = "获取图表")
    @SuppressWarnings("Duplicates")
    public Vo<ChartsOut> getCharts(@RequestAttribute("HumanDto")HumanDto humanDto,
                                   @PathVariable Long id) throws EoaException {
        ChartsOut out = cacheService.getCache(ChangeFlagUtils.CHARTS,id.toString(), USER_ID_CACHE, ChartsOut.class);
        if (out == null) {
            out = chartsOutFactory.out(chartsService.getCharts(id, humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.CHARTS,id.toString(),USER_ID_CACHE,out);
        }
        return new Vo<>(out);

    }

    @PostMapping(value = "/charts")
    @Operation(summary = "新建图表")
    public Vo<Long> newCharts(@RequestAttribute("HumanDto")HumanDto humanDto,
                              @RequestBody ChartsIn charts) throws ParameterException {
        Long id = chartsService.newCharts(charts.toEntity(null), humanDto.getDataId());
        if (id!=null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.CHARTS);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
    }


    @PutMapping(value = "/charts/{id}")
    @Operation(summary = "修改图表")
    @Parameter(name = "id",description = "字段编号",required = true,in = ParameterIn.PATH)
    public Vo<String> updateCharts(@RequestAttribute("HumanDto")HumanDto humanDto,
                                   @RequestBody ChartsIn charts,
                                   @PathVariable Long id) throws ParameterException {

        Integer update = chartsService.updateCharts(charts.toEntity(id), humanDto.getDataId());
        if (update==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.CHARTS);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @DeleteMapping(value = "/charts/{id}")
    @Operation(summary = "废弃图表")
    @Parameter(name = "id",description = "图表编号",required = true,in = ParameterIn.PATH)
    public Vo<String> dropCharts(@RequestAttribute("HumanDto")HumanDto humanDto,
                                 @PathVariable Long id) {

        Integer deletes = chartsService.deleteCharts(id, humanDto.getDataId());
        if (deletes==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.CHARTS);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
    }
}
