package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.eoa.projectbudget.entity.SearchListColumn;

/**
 * @Author: 张骏山
 * @Date: 2024/1/12 11:41
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: SearchListColumnMapper
 * @Description: 展示表字段持久层
 * @Version: 1.0
 **/

@Mapper
public interface SearchListColumnMapper extends BaseMapper<SearchListColumn> {

    /**
     * 获取搜索表单下新viewNo
     * @param searchListId 列表编号
     * @return viewNo
     */
    @Select("select ifnull(max(viewNo),0)+1 from search_list_column where searchListId = #{searchListId}")
    Integer getViewNoNew(@Param("searchListId")Long searchListId);
}
