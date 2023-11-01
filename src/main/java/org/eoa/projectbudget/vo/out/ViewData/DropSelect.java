package org.eoa.projectbudget.vo.out.ViewData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/8 17:17
 * @PackageName: org.eoa.projectbudget.vo.ViewData
 * @ClassName: DropSelect
 * @Description: TODO
 * @Version 1.0
 */

@Schema(name = "DropSelect",title = "供下拉框使用的临时数据结构")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DropSelect {
    private Integer key;
    private String label;
    private Boolean disable;
    private List<DropSelect> children;
}
