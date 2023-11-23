package org.eoa.projectbudget.vo.out;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.ColumnEntity;
import org.eoa.projectbudget.entity.ColumnView;
import org.eoa.projectbudget.utils.DataProcessUtils;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/11/23 19:34
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: ColumnOut
 * @Description: TODO
 * @Version: 1.1
 */

@Schema(name = "ColumnOut",description = "列字段格式输出类")
public class ColumnOut{

    @Schema(description = "主键id")
    Long columnId;
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
    @Schema(description = "字段创建者编号")
    Long creator;
    @Schema(description = "字段创建者名称")
    String creatorName;
    @Schema(description = "字段创建时间")
    Date createTime;
    @Schema(description = "字段明细编号")
    Integer columnDetailNo;
    @Schema(description = "字段是否展示")
    Boolean columnViewDisplay;
    @Schema(description = "字段是否展示")
    Boolean virtual;

    public ColumnOut(Column column) {
        this.columnId = column.getColumnId();
        this.columnViewName = column.getColumnViewName();
        this.columnDataName = column.getColumnDataName();
        this.columnType = column.getColumnType();
        this.columnTypeDescription = column.getColumnTypeDescription();
        this.tableNo = column.getTableNo();
        this.columnGroupNo = column.getColumnGroupNo();
        this.columnViewNo = column.getColumnViewNo();
        this.creator = column.getCreator();
        this.createTime = column.getCreateTime();
        if (column.getClass() == ColumnView.class)
            createView((ColumnView) column);
        else if (column.getClass() == ColumnEntity.class)
            createEntity((ColumnEntity) column);
        else {
            this.columnDetailNo = -1;
            columnViewDisplay = true;
            virtual = true;
        }
    }

    private void createView(ColumnView columnView) {
        this.columnDetailNo = -1;
        columnViewDisplay = DataProcessUtils.translateIntegerToBoolean(columnView.getColumnViewDisplay());
        virtual = true;
    }

    private void createEntity(ColumnEntity columnEntity) {
        this.columnDetailNo= columnEntity.getColumnDetailNo();
        columnViewDisplay = true;
        virtual = false;
    }

    public Long getColumnId() {
        return columnId;
    }

    public ColumnOut setColumnId(Long columnId) {
        this.columnId = columnId;
        return this;
    }

    public String getColumnViewName() {
        return columnViewName;
    }

    public ColumnOut setColumnViewName(String columnViewName) {
        this.columnViewName = columnViewName;
        return this;
    }

    public String getColumnDataName() {
        return columnDataName;
    }

    public ColumnOut setColumnDataName(String columnDataName) {
        this.columnDataName = columnDataName;
        return this;
    }

    public String getColumnType() {
        return columnType;
    }

    public ColumnOut setColumnType(String columnType) {
        this.columnType = columnType;
        return this;
    }

    public String getColumnTypeDescription() {
        return columnTypeDescription;
    }

    public ColumnOut setColumnTypeDescription(String columnTypeDescription) {
        this.columnTypeDescription = columnTypeDescription;
        return this;
    }

    public Long getTableNo() {
        return tableNo;
    }

    public ColumnOut setTableNo(Long tableNo) {
        this.tableNo = tableNo;
        return this;
    }

    public Integer getColumnGroupNo() {
        return columnGroupNo;
    }

    public ColumnOut setColumnGroupNo(Integer columnGroupNo) {
        this.columnGroupNo = columnGroupNo;
        return this;
    }

    public Integer getColumnViewNo() {
        return columnViewNo;
    }

    public ColumnOut setColumnViewNo(Integer columnViewNo) {
        this.columnViewNo = columnViewNo;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public ColumnOut setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public ColumnOut setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ColumnOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getColumnDetailNo() {
        return columnDetailNo;
    }

    public ColumnOut setColumnDetailNo(Integer columnDetailNo) {
        this.columnDetailNo = columnDetailNo;
        return this;
    }

    public Boolean getColumnViewDisplay() {
        return columnViewDisplay;
    }

    public ColumnOut setColumnViewDisplay(Boolean columnViewDisplay) {
        this.columnViewDisplay = columnViewDisplay;
        return this;
    }

    public Boolean getVirtual() {
        return virtual;
    }

    public ColumnOut setVirtual(Boolean virtual) {
        this.virtual = virtual;
        return this;
    }
}
