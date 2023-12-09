package org.eoa.projectbudget.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author 张骏山
 * @Date 2023/10/23 14:13
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: FormDMLMapper
 * @Description: 自定义表单对象的数据库dml操作接口
 * @Version 1.1
 */

@Mapper
public interface FormDMLMapper {

    /**
     * 获取指定自定义表单主表单行数据
     * @param dataId 主表数据编号
     * @param formTableName 主表名称
     * @return map键值对,列名=>值
     */
    @Select("select * from ${formTableName} where `dataId` = #{dataId}")
    Map<String,Object> getMainFormById(@Param("dataId")Long dataId
            ,@Param("formTableName")String formTableName);

    /**
     * 获取指定自定义表单明细对应主体所有数据
     * @param detailMainId 主体数据id
     * @param formDetailTableName 明细表名
     * @return map键值对数组,列名=>值
     */
    @Select("select * from ${formDetailTableName} where `detailMainId` = #{detailMainId}")
    List<Map<String,Object>> getDetailFormByDataId(@Param("detailMainId")Long detailMainId
                                                    ,@Param("formDetailTableName")String formDetailTableName);

    /**
     * 更新主表数据
     * @param columns 字段键值对
     * @param dataId 数据id
     * @param formTableName 表单名称
     * @return 结果数字
     */
    Integer updateMainForm(@Param("columns") Map<String,Object> columns
                            ,@Param("dataId") Long dataId
                            ,@Param("formTableName")String formTableName);

    /**
     * 更新明细表数据
     * @param columns 字段键值对
     * @param formDetailTableName 明细表名
     * @return 结果数字
     */
    Integer updateDetailForm(@Param("columns") Map<String,Object> columns
                            ,@Param("detailDataId") Long detailDataId
                            ,@Param("formDetailTableName")String formDetailTableName);

    /**
     * 插入主表数据
     * @param columnsWithBaseData 字段键值对
     * @param formTableName 主表名称
     * @return 结果数字
     */
    Integer insertMainForm(@Param("columns") Map<String,Object> columnsWithBaseData
                            ,@Param("formTableName")String formTableName);

    /**
     * 插入明细数据
     * @param columnsWithBaseData  字段键值对,需要基础信息
     * @param formDetailTableName 明细表明
     * @return 结果数字
     */
    Integer insertDetailForm(@Param("columns") Map<String,Object> columnsWithBaseData
                            ,@Param("formDetailTableName")String formDetailTableName);

    /**
     * 删除主表数据
     * @param dataId 数据id
     * @param formTableName 主表名
     * @return
     */
    @Delete("delete from ${formTableName} where `dataId` = #{dataId}")
    Integer deleteMainForm(@Param("dataId") Long dataId
                            ,@Param("formTableName")String formTableName);

    /**
     * 删除明细表数据
     * @param detailDataId 明细id
     * @param formDetailTableName 明细表名
     * @return 结果数字
     */
    @Delete("delete from ${formDetailTableName} where `detailDataId` = #{detailDataId}")
    Integer deleteDetailForm(@Param("detailDataId") Long detailDataId
                            ,@Param("formDetailTableName")String formDetailTableName);

    /**
     * 删除明细表数据中主表相关数据
     * @param detailMainId 主表id
     * @param formDetailTableName 明细表名
     * @return 结果数字
     */
    @Delete("delete from ${formDetailTableName} where `detailMainId` = #{detailMainId}")
    Integer deleteDetailFormByDataId(@Param("detailMainId") Long detailMainId
            ,@Param("formDetailTableName")String formDetailTableName);

    /**
     * 规则下批量获取主键id
     * @param formTableName 表名
     * @param eqMap 字段筛选规则
     * @param likeMap 字符串筛选规则
     * @param betweenMap 日期筛选规则
     * @return 主键数组
     */
    List<Long> getIdsByMap(@Param("formTableName")String formTableName,
                           @Param("eqMap")Map<String,String> eqMap,
                           @Param("likeMap")Map<String, String> likeMap,
                           @Param("betweenMap")Map<String, String[]> betweenMap);

    /**
     * 获取对应主表id下的所有明细id
     * @param detailMainId 主表id
     * @param detailTableName 明细表名
     * @return 明细id数组
     */
    @Select("select detailDataId from ${formDetailTableName} where `detailMainId` = #{detailMainId}")
    List<Long> getExistDetail(@Param("detailMainId")Long detailMainId,
                              @Param("detailTableName")String detailTableName);

}
