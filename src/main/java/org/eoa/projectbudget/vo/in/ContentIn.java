package org.eoa.projectbudget.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Content;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 16:25
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: ContentIn
 * @Description: 目录传入结构体
 * @Version: 1.0
 **/

@Schema(name = "ContentIn", description = "目录传入结构体")
public class ContentIn implements CheckParameter<Content>{
    String contentName;
    String contentRemark;
    String defaultEdit;
    String defaultCreate;
    String defaultDelete;
    String defaultShare;
    Long leadContent;

    @Override
    public void checkSelf() throws ParameterException {
        if (leadContent == null) {
            throw new ParameterException("leadContent","","缺少值");
        }
        if (DataProcessUtils.isEmpty(contentName)) {
            throw new ParameterException("contentName","","缺少值");
        }
    }

    @Override
    public Content toEntity(Long dataId) throws ParameterException {
        checkSelf();
        return new Content()
                .setDataId(dataId)
                .setContentName(contentName)
                .setContentRemark(contentRemark)
                .setDefaultCreate(defaultCreate)
                .setDefaultEdit(defaultEdit)
                .setDefaultDelete(defaultDelete)
                .setDefaultShare(defaultShare)
                .setLeadContent(leadContent)
                .setIsDeprecated(1);
    }
}
