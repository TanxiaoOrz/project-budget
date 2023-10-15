package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.eoa.projectbudget.entity.ColumnEntity;

@Mapper
public interface ColumnEntityMapper extends BaseMapper<ColumnEntity> {

    /**
     * 通过字段所属表单查询字段显示编号应赋值
     * @param tableNo 字段所属表单编号
     * @return 字段显示最新值
     */
    @Select("select ifnull(max(columnViewNo),0)+1 from table_column_index where tableNo = #{tableNo}")
    Integer getColumnViewNoNew(@Param("tableNo")Long tableNo);
}
