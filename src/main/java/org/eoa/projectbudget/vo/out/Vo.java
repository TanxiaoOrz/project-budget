package org.eoa.projectbudget.vo.out;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.exception.EoaException;

/**
 * @Author 张骏山
 * @Date 2023/9/28 9:50
 * @PackageName: org.eoa.projectbudget.vo
 * @ClassName: Vo
 * @Description: 数据回传的包装实体
 * @Version 1.4
 */
@Schema(name = "Vo",description = "数据传输实体类")
public class Vo<Entity> {
    public static Integer SUCCESS = 0;
    public static Integer TOKEN_ERROR = -1;
    public static Integer UPDATE_TOKEN = -2;
    public static Integer WRONG_PARAMETER = 1;
    public static Integer NO_AUTHORITY = -3;
    public static Integer SERVER_ERROR = 2;
    public static Integer STORE_DATA_ERROR = 3;

    @Schema(description = "成功状态,0:成功,-1:重新登录,-2:更新token,-3:无权限,1:入参错误,2:运行错误,3:存储数据错误")
    private Integer code;
    @Schema(description = "结果描述,批量获取数据时为数据的数量")
    private String description;
    @Schema(description = "结果类")
    private Entity entity;
    @Schema(description = "更新token,先是短token,后是长token",deprecated = true)
    private String[] newToken;

    /**
     * 异常返回
     * @param e 自定义异常类
     */
    public Vo(EoaException e) {
        this.code = e.code;
        this.description = e.description;
    }

    /**
     * 错误返回
     * @param code 错误码
     * @param description 错误描述
     */
    public Vo(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 正常返回
     * @param entity 返回结果
     */
    public Vo(Entity entity) {
        this.code = SUCCESS;
        this.description = "操作成功";
        this.entity = entity;
    }

    /**
     * 批量获取数据的创建方法
     * @param entity 数据几
     * @param counts 数据的数量
     */
    public Vo(Entity entity,Integer counts) {
        this.code = SUCCESS;
        this.description = counts.toString();
        this.entity = entity;
    }

    public Integer getCode() {
        return code;
    }

    public Vo<Entity> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Vo<Entity> setDescription(String description) {
        this.description = description;
        return this;
    }

    public Entity getEntity() {
        return entity;
    }

    public Vo<Entity> setEntity(Entity entity) {
        this.entity = entity;
        return this;
    }

    public String[] getNewToken() {
        return newToken;
    }

    public Vo<Entity> setNewToken(String[] newToken) {
        this.newToken = newToken;
        return this;
    }
}
