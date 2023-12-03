package org.eoa.projectbudget.service.content.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.Content;
import org.eoa.projectbudget.entity.File;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.ContentMapper;
import org.eoa.projectbudget.mapper.FileMapper;
import org.eoa.projectbudget.service.content.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 13:56
 * @PackageName: org.eoa.projectbudget.service.content.impl
 * @ClassName: ContentServiceImpl
 * @Description: 目录模块实现类
 * @Version: 1.0
 **/

@Service
public class ContentServiceImpl implements ContentService {

    private final Logger log = LoggerFactory.getLogger("ContentModule");

    @Autowired
    ContentMapper contentMapper;

    @Autowired
    FileMapper fileMapper;

    @Override
    public List<Content> getContentList(QueryWrapper<Content> wrapper, Long userId) {
        List<Content> contents = contentMapper.selectList(wrapper);
        log.info("用户+>{}批量获取目录数据=>{}",userId,contents.size());
        return contents;
    }

    @Override
    public Content getContent(Long dataId, Long userId) {
        Content content = contentMapper.selectById(dataId);
        log.info("用户=>{}获取目录=>{},success=>{}",userId,dataId,content==null);
        return content;
    }

    @Override
    public Long newContent(Content content, Long userId) throws ParameterException {
        content.setCreator(userId).setCreateTime(new Date());
        if (content.getLeadContent() != null){
            Content lead = contentMapper.selectById(content.getLeadContent());
            if (lead == null) {
                throw new ParameterException("leadContent", content.getLeadContent().toString(), "不存在该目录");
            }
        }
        contentMapper.insert(content);
        log.info("用户=>{}创建目录=>{}",userId,content.getDataId());
        return content.getDataId();
    }

    @Override
    public Integer updateContent(Content content, Long userId) {
        Content old = contentMapper.selectById(content.getDataId());
        content.setCreator(old.getCreator())
                .setCreateTime(old.getCreateTime());
        int i = contentMapper.updateById(content);
        log.info("用户=>{}修改目录=>{}",userId,content.getDataId());
        return i;
    }

    @Override
    public Integer dropContent(Long dataId, Long userId) {
        Integer drop = contentMapper.drop(dataId);
        log.info("用户=>{}废弃目录=>{}",userId,dataId);
        return drop;
    }

    @Override
    public List<File> getFileList(QueryWrapper<File> wrapper, Long userId) {
        List<File> files = fileMapper.selectList(wrapper);
        log.info("用户+>{}批量获取文件数据=>{}",userId,files.size());
        return files;
    }

    @Override
    public File getFile(Long dataId, Long userId) {
        File file = fileMapper.selectById(dataId);
        log.info("用户=>{}获取文件=>{},success=>{}",userId,dataId,file==null);
        return file;
    }

    @Override
    public Long newFile(File file, Long userId) throws ParameterException {
        file.setCreator(userId).setCreateTime(new Date());
        Content content = contentMapper.selectById(file.getLeadContent());
        if (content == null) {
            throw new ParameterException("leadContent",file.getLeadContent().toString(),"不存在该目录");
        }
        file.setDeleteAuthority(content.getDefaultDelete()).
                setViewAuthority(content.getDefaultShare()).
                setEditAuthority(content.getDefaultEdit());
        fileMapper.insert(file);
        log.info("用户=>{}新建文件=>{}",userId,file.getDataId());
        return file.getDataId();
    }

    @Override
    public Integer updateFile(File file, Long userId) throws ParameterException {
        File old = fileMapper.selectById(file.getDataId());
        if (!old.getFileRoute().equals(file.getFileRoute()))
            throw new ParameterException("fileRoute",file.getFileRoute(),"不允许修改文件路径");
        file.setCreator(old.getCreator())
                .setCreateTime(old.getCreateTime());
        int i = fileMapper.updateById(file);
        log.info("用户=>{}修改目录=>{}",userId,file.getDataId());
        return i;
    }

    @Override
    public Integer dropFile(Long dataId, Long userId) {
        Integer drop = fileMapper.drop(dataId);
        log.info("用户=>{}废弃目录=>{}",userId,dataId);
        return drop;
    }
}
