package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
}
