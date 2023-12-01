package org.eoa.projectbudget.service.content;

import org.eoa.projectbudget.exception.EoaException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 13:49
 * @PackageName: org.eoa.projectbudget.service.content
 * @ClassName: FileService
 * @Description: 文件存储处理
 * @Version: 1.0
 **/
public interface FileService {

    String upload(MultipartFile multipartFile, Long userId) throws EoaException, IOException;

}
