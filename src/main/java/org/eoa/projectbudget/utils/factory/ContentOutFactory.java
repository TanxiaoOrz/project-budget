package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.Content;
import org.eoa.projectbudget.entity.HumanResourceView;
import org.eoa.projectbudget.mapper.ContentMapper;
import org.eoa.projectbudget.mapper.HumanViewMapper;
import org.eoa.projectbudget.vo.out.ContentOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 16:16
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: ContentOutFactory
 * @Description: 目录类构造工厂
 * @Version: 1.0
 **/

@Component
public class ContentOutFactory implements OutFactory<Content, ContentOut> {
    @Autowired
    HumanViewMapper humanViewMapper;
    @Autowired
    ContentMapper contentMapper;


    @Override
    public ContentOut out(Content content) {
        if (content == null) {
            return null;
        }
        Content lead = contentMapper.selectById(content.getLeadContent());
        HumanResourceView humanResourceView = humanViewMapper.selectById(content.getCreator());
        return new ContentOut(content)
                .setLeadName(lead==null?null: lead.getContentName())
                .setCreatorName(humanResourceView.getName());
    }

    @Override
    public List<ContentOut> outs(List<? extends Content> contents) {
        if (contents == null) {
            return null;
        }
        return contents.stream().map(this::out).toList();
    }
}
