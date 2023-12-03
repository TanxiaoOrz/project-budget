package org.eoa.projectbudget.vo.in;

import org.eoa.projectbudget.entity.File;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 16:47
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: FileIn
 * @Description: 文件传入类
 * @Version: 1.0.1
 **/
public class FileIn implements CheckParameter<File> {
    String fileName;
    String fileRoute;
    Long leadContent;
    @Override
    public void checkSelf() throws ParameterException {
        if (leadContent == null) {
            throw new ParameterException("leadContent","","缺少值");
        }
        if (DataProcessUtils.isEmpty(fileName)) {
            throw new ParameterException("fileName","","缺少值");
        }
        if (DataProcessUtils.isEmpty(fileRoute)) {
            throw new ParameterException("fileRoute","","缺少值");
        }
    }

    @Override
    public File toEntity(Long dataId) throws ParameterException {
        checkSelf();
        return new File().setDataId(dataId)
                .setFileName(fileName)
                .setFileRoute(fileRoute)
                .setLeadContent(leadContent)
                .setIsDeprecated(0);
    }

    public String getFileName() {
        return fileName;
    }

    public FileIn setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileRoute() {
        return fileRoute;
    }

    public FileIn setFileRoute(String fileRoute) {
        this.fileRoute = fileRoute;
        return this;
    }

    public Long getLeadContent() {
        return leadContent;
    }

    public FileIn setLeadContent(Long leadContent) {
        this.leadContent = leadContent;
        return this;
    }
}
