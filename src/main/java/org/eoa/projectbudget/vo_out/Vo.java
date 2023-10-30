package org.eoa.projectbudget.vo_out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.eoa.projectbudget.exception.EoaException;

/**
 * @Author 张骏山
 * @Date 2023/9/28 9:50
 * @PackageName: org.eoa.projectbudget.vo
 * @ClassName: Vo
 * @Description: 数据回传的包装实体
 * @Version 1.0
 */
@Data
@Schema(name = "Vo",description = "数据传输实体类")
public class Vo<Entity> {
    public static Integer SUCCESS = 0;
    public static Integer LOGIN = -2;
    public static Integer UPDATE_TOKEN = -2;
    public static Integer WRONG_PARAMETER = 1;
    public static Integer NO_AUTHORITY = -3;
    public static Integer SERVER_ERROR = 2;
    public static Integer STORE_DATA_ERROR = 3;

    @Schema(name = "成功状态",description = "0:成功,-1:重新登录,-2:更新token,-3:无权限,1:入参错误,2:运行错误,3:存储数据错误")
    private Integer code;
    @Schema(name = "结果描述")
    private String description;
    @Schema(name = "结果类")
    private Entity entity;
    @Schema(name = "更新token",description = "先是短token,后是长token")
    private String[] newToken;

    public Vo(EoaException e) {
        this.code = e.code;
        this.description = e.description;
    }

    public Vo(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Vo(Entity entity) {
        this.code = SUCCESS;
        this.description = "操作成功";
        this.entity = entity;
    }
}
