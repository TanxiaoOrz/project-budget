package org.eoa.projectbudget.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.ColumnEntity;
import org.eoa.projectbudget.entity.ColumnView;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/11/23 19:47
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: ColumnIn
 * @Description: TODO
 * @Version: 1.0
 */
@Schema(name = "ColumnIn",description = "列字段格式传入类")
public class ColumnIn implements CheckParameter<Column> {
    @Schema(description = "字段显示名称")
    String columnViewName;
    @Schema(description = "数据库存储名称")
    String columnDataName;
    @Schema(description = "字段类型")
    String columnType;
    @Schema(description = "字段类型辅助描述")
    String columnTypeDescription;
    @Schema(description = "所属表单")
    Long tableNo;
    @Schema(description = "字段主表组编号")
    Integer columnGroupNo;
    @Schema(description = "字段显示顺序")
    Integer columnViewNo;
    @Schema(description = "字段创建者")
    Long creator;
    @Schema(description = "字段创建时间")
    Date createTime;
    @Schema(description = "字段明细编号")
    Integer columnDetailNo;
    @Schema(description = "字段是否展示")
    Boolean columnViewDisplay;
    @Schema(description = "字段是否展示")
    Boolean isVirtual;

    @Override
    public void checkSelf() throws ParameterException {
        //TODO
    }

    @Override
    public Column toEntity(Long dataId) throws ParameterException {
        Column ret;
        if (isVirtual)
            ret = new ColumnView();
        else
            ret = new ColumnEntity();
        ret.setColumnId(dataId)
                .setColumnType(columnType)
                .setColumnGroupNo(columnGroupNo)
                .setColumnDataName(columnDataName)
                .setColumnViewName(columnViewName)
                .setColumnViewNo(columnViewNo)
                .setCreator(creator)
                .setCreateTime(createTime)
                .setTableNo(tableNo)
                .setColumnTypeDescription(columnTypeDescription);
        if (isVirtual) {
            assert ret instanceof ColumnView;
            ((ColumnView) ret).setColumnViewDisplay(DataProcessUtils.translateBooleanToInteger(columnViewDisplay));
        }
        else {
            assert ret instanceof ColumnEntity;
            ((ColumnEntity) ret).setColumnDetailNo(columnDetailNo);
        }
        //TODO
        return ret;
    }

    public String getColumnViewName() {
        return columnViewName;
    }

    public ColumnIn setColumnViewName(String columnViewName) {
        this.columnViewName = columnViewName;
        return this;
    }

    public String getColumnDataName() {
        return columnDataName;
    }

    public ColumnIn setColumnDataName(String columnDataName) {
        this.columnDataName = columnDataName;
        return this;
    }

    public String getColumnType() {
        return columnType;
    }

    public ColumnIn setColumnType(String columnType) {
        this.columnType = columnType;
        return this;
    }

    public String getColumnTypeDescription() {
        return columnTypeDescription;
    }

    public ColumnIn setColumnTypeDescription(String columnTypeDescription) {
        this.columnTypeDescription = columnTypeDescription;
        return this;
    }

    public Long getTableNo() {
        return tableNo;
    }

    public ColumnIn setTableNo(Long tableNo) {
        this.tableNo = tableNo;
        return this;
    }

    public Integer getColumnGroupNo() {
        return columnGroupNo;
    }

    public ColumnIn setColumnGroupNo(Integer columnGroupNo) {
        this.columnGroupNo = columnGroupNo;
        return this;
    }

    public Integer getColumnViewNo() {
        return columnViewNo;
    }

    public ColumnIn setColumnViewNo(Integer columnViewNo) {
        this.columnViewNo = columnViewNo;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public ColumnIn setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ColumnIn setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getColumnDetailNo() {
        return columnDetailNo;
    }

    public ColumnIn setColumnDetailNo(Integer columnDetailNo) {
        this.columnDetailNo = columnDetailNo;
        return this;
    }

    public Boolean getColumnViewDisplay() {
        return columnViewDisplay;
    }

    public ColumnIn setColumnViewDisplay(Boolean columnViewDisplay) {
        this.columnViewDisplay = columnViewDisplay;
        return this;
    }

    public Boolean getVirtual() {
        return isVirtual;
    }

    public ColumnIn setVirtual(Boolean virtual) {
        isVirtual = virtual;
        return this;
    }
}
