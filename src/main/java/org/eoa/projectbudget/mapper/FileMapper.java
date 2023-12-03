package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.eoa.projectbudget.entity.File;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 11:21
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: FileMapper
 * @Description: 文件持久层
 * @Version: 1.0
 **/
@Mapper
public interface FileMapper extends BaseMapper<File> {
    /**
     * 废弃文件索引
     * @param dataId 数据编号
     * @return 修改数量
     */
    @Update("update file_storage set isDeprecated = 1 where dataId = #{dataId}")
    Integer drop(@Param("dataId") Long dataId);
}
