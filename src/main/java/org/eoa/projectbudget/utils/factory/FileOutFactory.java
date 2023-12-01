package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Content;
import org.eoa.projectbudget.entity.File;
import org.eoa.projectbudget.entity.HumanResourceView;
import org.eoa.projectbudget.mapper.ContentMapper;
import org.eoa.projectbudget.mapper.HumanViewMapper;
import org.eoa.projectbudget.vo.out.FileOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 16:05
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: FileOutFactory
 * @Description: 文件输出类构造工厂
 * @Version: 1.0
 **/

@Component
public class FileOutFactory implements OutFactory<File, FileOut> {
    @Autowired
    HumanViewMapper humanViewMapper;
    @Autowired
    ContentMapper contentMapper;


    @Override
    public FileOut out(File file) {
        if (file == null) {
            return null;
        }
        FileOut fileOut = new FileOut(file);
        HumanResourceView creator = humanViewMapper.selectById(file.getCreator());
        Content content = contentMapper.selectById(file.getLeadContent());
        return fileOut.setLeadName(content==null?null:content.getContentName())
                .setCreatorName(creator.getName());
    }

    @Override
    public List<FileOut> outs(List<? extends File> files) {
        if (files == null) {
            return null;
        }
        return files.stream().map(this::out).toList();
    }
}
