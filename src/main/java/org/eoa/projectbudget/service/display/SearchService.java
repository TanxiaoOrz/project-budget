package org.eoa.projectbudget.service.display;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.SearchListDto;
import org.eoa.projectbudget.entity.SearchList;
import org.eoa.projectbudget.entity.SearchListColumn;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/12 14:33
 * @PackageName: org.eoa.projectbudget.service.display
 * @ClassName: SearchService
 * @Description: 数据列表业务类
 * @Version: 1.0
 **/
public interface SearchService {

    /**
     * 获取列表
     * @param chartId 列表编号
     * @param userId 用户编号
     * @return 列表实体类
     */
    SearchList getSearchList(Long chartId, Long userId);

    /**
     * 获取列表数组
     * @param wrapper 查询条件
     * @param userId 用户id
     * @return 列表实体类数组
     */
    List<SearchList> getSearchListList(QueryWrapper<SearchList> wrapper, Long userId);

    /**
     * 新建列表
     * @param searchList 列表数据
     * @param userId 用户id
     * @return 图标编号
     */
    Long newSearchList(SearchList searchList, Long userId);

    /**
     * 更新列表
     * @param searchList 列表数据
     * @param userId 用户编号
     * @return 更新数量
     */
    Integer updateSearchList(SearchList searchList, Long userId);
    /**
     * 删除列表
     * @param dataId 列表编号
     * @param userId 用户编号
     * @return 更新数量
     */
    Integer deleteSearchList(Long dataId, Long userId);

    /**
     * 获取列表字段
     * @param chartId 列表字段编号
     * @param userId 用户编号
     * @return 列表字段实体类
     */
    SearchListColumn getSearchListColumn(Long chartId, Long userId);

    /**
     * 获取列表字段数组
     * @param wrapper 查询条件
     * @param userId 用户id
     * @return 列表字段实体类数组
     */
    List<SearchListColumn> getSearchListColumnList(QueryWrapper<SearchListColumn> wrapper, Long userId);

    /**
     * 新建列表字段
     * @param searchListColumn 列表字段数据
     * @param userId 用户id
     * @return 图标编号
     */
    Long newSearchListColumn(SearchListColumn searchListColumn, Long userId);

    /**
     * 更新列表字段
     * @param searchListColumn 列表字段数据
     * @param userId 用户编号
     * @return 更新数量
     */
    Integer updateSearchListColumn(SearchListColumn searchListColumn, Long userId);
    /**
     * 删除列表字段
     * @param dataId 列表字段编号
     * @param userId 用户编号
     * @return 更新数量
     */
    Integer deleteSearchListColumn(Long dataId, Long userId);

    /**
     * 获取列表展示类
     * @param dataId 列表编号
     * @param userId 用户标号
     * @return 列表数据
     */
    SearchListDto getSearchListDto(Long dataId, Long userId);
}
