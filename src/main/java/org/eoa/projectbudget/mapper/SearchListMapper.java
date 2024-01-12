package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.eoa.projectbudget.entity.SearchList;

/**
 * @Author: 张骏山
 * @Date: 2024/1/12 11:40
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: SearchListMapper
 * @Description: 展示表持久层
 * @Version: 1.0
 **/

@Mapper
public interface SearchListMapper extends BaseMapper<SearchList> {
}
