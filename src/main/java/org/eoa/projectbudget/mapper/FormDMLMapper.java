package org.eoa.projectbudget.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Author 张骏山
 * @Date 2023/10/23 14:13
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: FormDMLMapper
 * @Description: 自定义表单对象的数据库dml操作接口
 * @Version 1.0
 */

@Mapper
public interface FormDMLMapper {

    /**
     * 获取指定自定义表单主表单行数据
     * @param dataId 主表数据编号
     * @param formTableName 主表名称
     * @return map键值对,列名=>值
     */
    @Select("select * from `${formTableName}` where `dataId` = #{dataId}")
    Map<String,Object> getMainFormById(@Param("dataId")Long dataId
            ,@Param("formTableName")String formTableName);

    /**
     * 获取指定自定义表单明细对应主体所有数据
     * @param detailMainId 主体数据id
     * @param formDetailTableName 明细表名
     * @return map键值对数组,列名=>值
     */
    @Select("select * from `${formDetailTableName}` where `detailMainId` = #{detailMainId}")
    List<Map<String,Object>> getDetailFormByDataId(@Param("detailMainId")Long detailMainId
            ,@Param("formDetailTableName")String formDetailTableName);

    @SuppressWarnings("all")
    @Update("""
            <script>
                update `${formTableName}` 
                    <set>
                        <foreach collection="columns.entrySet()" index="key" item="value">
                                ${key} = #{value},
                        </foreach>
                        latestEditTime = now()
                    </set>
                where `dataId` = #{dataId}
            </script>
            """)
    Integer updateMainForm(@Param("columns") Map<String,Object> columns
            ,@Param("dataId") Long dataId
            ,@Param("formTableName")String formTableName);

    @Update("""
            <script>
                update `${formDetailTableName}`
                    <set>
                        <foreach collection="columns.entrySet()" index="key" item="value">
                                ${key} = #{value},
                        </foreach>
                    </set>
                where `detailMainId` = #{detailMainId}
            </script>
            """)
    Integer updateDetailForm(@Param("columns") Map<String,Object> columns
            ,@Param("detailMainId") Long detailMainId
            ,@Param("formDetailTableName")String formDetailTableName);

    @Insert("""
            <script>
                insert into `${formTableName}`
                    <foreach collection="columns.keySet()" index="index" item="value" open="(" separator="," close=")">
                                `${value}`
                    </foreach>
                    value
                    <foreach collection="columns.values()" index="index" item="value" open="(" separator="," close=")">
                                #{value}
                    </foreach>
            </script>
            """)
    Integer insertMainForm(@Param("columns") Map<String,Object> columnsWithBaseData
            ,@Param("formTableName")String formTableName);

    @Insert("""
            <script>
                insert into `${formDetailTableName}`
                    <foreach collection="columns.keySet()" index="index" item="value" open="(" separator="," close=")">
                                `${value}`
                    </foreach>
                    value
                    <foreach collection="columns.values()" index="index" item="value" open="(" separator="," close=")">
                                #{value}
                    </foreach>
            </script>    
            """)
    Integer insertDetailForm(@Param("columns") Map<String,Object> columnsWithBaseData
            ,@Param("formDetailTableName")String formDetailTableName);
    @Delete("delete from `${formTableName}` where `dataId` = #{dataId}")
    Integer deleteMainForm(@Param("dataId") Long dataId
            ,@Param("formTableName")String formTableName);

    @Delete("delete from `${formDetailTableName}` where `detailMainId` = #{detailMainId}")
    Integer deleteDetailForm(@Param("detailMainId") Long detailMainId
            ,@Param("formDetailTableName")String formDetailTableName);

    Integer test();
}
