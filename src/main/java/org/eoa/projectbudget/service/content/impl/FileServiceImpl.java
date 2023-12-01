package org.eoa.projectbudget.service.content.impl;

import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.service.content.FileService;
import org.eoa.projectbudget.utils.DataProcessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 14:00
 * @PackageName: org.eoa.projectbudget.service.content.impl
 * @ClassName: FileServiceImpl
 * @Description: 文件处理结构实现类
 * @Version: 1.0
 **/

@Service
public class FileServiceImpl implements FileService {

    private final Logger log = LoggerFactory.getLogger("ContentModule");

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public String upload(MultipartFile multipartFile, Long userId) throws EoaException, IOException {
        final String absolutePath = System.getProperty("user.dir")+"/static/files";
        String name = DataProcessUtils.nullToString(multipartFile.getOriginalFilename());
        int i = 0;
        File file = new File(absolutePath, name);
        while (file.exists()) {
            i++;
            file = new File(name+"("+i+")");
        }

        file.createNewFile();
        multipartFile.transferTo(file);
        log.info("用户=>{}上传文件=>{}",userId,file.getName());
        return "/files/"+file.getName();
    }
}
