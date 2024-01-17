package org.eoa.projectbudget.service.display.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.SearchListDto;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.SearchList;
import org.eoa.projectbudget.entity.SearchListColumn;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.ColumnEntityMapper;
import org.eoa.projectbudget.mapper.ColumnViewMapper;
import org.eoa.projectbudget.mapper.SearchListColumnMapper;
import org.eoa.projectbudget.mapper.SearchListMapper;
import org.eoa.projectbudget.service.display.SearchService;
import org.eoa.projectbudget.utils.DataProcessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 15:48
 * @PackageName: org.eoa.projectbudget.service.display.impl
 * @ClassName: SearchServiceImpl
 * @Description: 查询列表业务实现类
 * @Version: 1.0
 */
@Service
public class SearchServiceImpl implements SearchService {

    private final Logger log = LoggerFactory.getLogger("DisplayModule");

    @Autowired
    SearchListMapper searchListMapper;

    @Autowired
    SearchListColumnMapper searchListColumnMapper;

    @Autowired
    ColumnEntityMapper entityMapper;
    @Autowired
    ColumnViewMapper viewMapper;



    @Override
    public List<SearchList> getSearchListList(QueryWrapper<SearchList> wrapper, Long userId) {
        List<SearchList> searchLists = searchListMapper.selectList(wrapper);
        log.info("用户+>{}批量获取列表数据=>{}",userId,searchLists.size());
        return searchLists;
    }

    @Override
    public SearchList getSearchList(Long dataId, Long userId) {
        SearchList searchList = searchListMapper.selectById(dataId);
        if (searchList == null) {
            log.warn("用户=>{}获取列表=>{},success=>{}",userId,dataId, true);
            throw new ParameterException("dataId", dataId.toString(), "不存在该编号");
        }
        log.info("用户=>{}获取列表=>{},success=>{}",userId,dataId,true);
        return searchList;
    }

    @Override
    public Long newSearchList(SearchList searchList, Long userId) {
        searchList.setCreator(userId).setCreateTime(new Date());

        searchListMapper.insert(searchList);
        log.info("用户=>{}创建列表=>{}",userId,searchList.getDataId());
        return searchList.getDataId();
    }

    @Override
    public Integer updateSearchList(SearchList searchList, Long userId) {
        SearchList old = searchListMapper.selectById(searchList.getDataId());
        searchList.setCreator(old.getCreator())
                .setCreateTime(old.getCreateTime());
        int i = searchListMapper.updateById(searchList);
        log.info("用户=>{}修改列表=>{}",userId,searchList.getDataId());
        return i;
    }

    @Override
    public Integer deleteSearchList(Long dataId, Long userId) {
        Integer drop = searchListMapper.deleteById(dataId);
        log.info("用户=>{}废弃列表=>{}",userId,dataId);
        return drop;
    }

    @Override
    public List<SearchListColumn> getSearchListColumnList(QueryWrapper<SearchListColumn> wrapper, Long userId) {
        List<SearchListColumn> searchListColumns = searchListColumnMapper.selectList(wrapper);
        log.info("用户+>{}批量获取列表字段数据=>{}",userId,searchListColumns.size());
        return searchListColumns;
    }

    @Override
    public SearchListColumn getSearchListColumn(Long dataId, Long userId) {
        SearchListColumn searchListColumn = searchListColumnMapper.selectById(dataId);
        if (searchListColumn == null) {
            log.warn("用户=>{}获取列表字段=>{},success=>{}",userId,dataId, true);
            throw new ParameterException("dataId", dataId.toString(), "不存在该编号");
        }
        log.info("用户=>{}获取列表字段=>{},success=>{}",userId,dataId,true);
        return searchListColumn;
    }

    @Override
    public Long newSearchListColumn(SearchListColumn searchListColumn, Long userId) {
        Long searchListId = searchListColumn.getSearchListId();
        if (searchListColumn.getViewNo() == null) {
            searchListColumn.setViewNo(searchListColumnMapper.getViewNoNew(searchListId));
        }
        searchListColumnMapper.insert(searchListColumn);
        log.info("用户=>{}创建列表字段=>{}",userId,searchListColumn.getDataId());
        return searchListColumn.getDataId();
    }

    @Override
    public Integer updateSearchListColumn(SearchListColumn searchListColumn, Long userId) {
        if (searchListColumn.getViewNo() == null) {
            searchListColumn.setViewNo(searchListColumnMapper.getViewNoNew(searchListColumn.getSearchListId()));
        }
        int i = searchListColumnMapper.updateById(searchListColumn);
        log.info("用户=>{}修改列表字段=>{}",userId,searchListColumn.getDataId());
        return i;
    }

    @Override
    public Integer deleteSearchListColumn(Long dataId, Long userId) {
        Integer drop = searchListColumnMapper.deleteById(dataId);
        log.info("用户=>{}废弃列表字段=>{}",userId,dataId);
        return drop;
    }

    @Override
    public SearchListDto getSearchListDto(Long dataId, Long userId) {
        log.info("用户=>{}获取列表计算类=>{}",userId,dataId);
        SearchList searchList = searchListMapper.selectById(dataId);
        List<SearchListColumn> searchListColumns = searchListColumnMapper.selectList(new QueryWrapper<SearchListColumn>().eq("searchListId", dataId).orderByAsc("viewNo"));
        List<? extends Column> columns = searchListColumns.stream().map(searchListColumn -> {
            if (DataProcessUtils.translateIntegerToBoolean(searchListColumn.getIsVirtual()))
                return viewMapper.selectById(searchListColumn.getColumnId());
            else
                return entityMapper.selectById(searchListColumn.getColumnId());
        }).toList();
        return new SearchListDto(searchList, searchListColumns, columns);
    }
}
