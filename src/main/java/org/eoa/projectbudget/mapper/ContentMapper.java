package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.eoa.projectbudget.entity.Content;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 11:20
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: ContentMapper
 * @Description: 目录持久层
 * @Version: 1.0
 **/

@Mapper
public interface ContentMapper extends BaseMapper<Content> {
    /**
     * 废弃目录
     * @param dataId 数据编号
     * @return 修改数量
     */
    @Update("update content_list set isDeprecated = 1 where dataId = #{dataId}")
    Integer drop(@Param("dataId") Long dataId);
}
