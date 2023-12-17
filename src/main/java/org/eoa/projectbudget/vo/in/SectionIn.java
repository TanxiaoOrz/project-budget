package org.eoa.projectbudget.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Section;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/17 19:54
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: SectionIn
 * @Description: 分部传入结构体
 * @Version: 1.0
 */

@Schema(name = "SectionIn", description = "分部传入结构体")
public class SectionIn implements CheckParameter<Section> {

    String sectionName;
    String sectionCode;
    String fullName;
    Long belongSection;
    Long sectionManager;
    String sectionIntroduction;
    Date createTime;
    Long photo;

    @Override
    public void checkSelf() throws ParameterException {
        if (DataProcessUtils.isEmpty(sectionName))
            throw new ParameterException("sectionName","","缺少分部名称");
        if (DataProcessUtils.isEmpty(sectionCode))
            throw new ParameterException("sectionCode","","缺少分部名称");
        if (belongSection == null)
            throw new ParameterException("belongSection","","缺少上级分部");

    }

    @Override
    public Section toEntity(Long dataId) throws ParameterException {
        checkSelf();

        Section section = new Section();
        section.setDataId(dataId)
                .setSectionName(sectionName)
                .setSectionCode(sectionCode)
                .setFullName((DataProcessUtils.isEmpty(fullName)?sectionName:fullName))
                .setBelongSection(belongSection)
                .setSectionManager(sectionManager)
                .setSectionIntroduction(sectionIntroduction)
                .setCreateTime(createTime)
                .setPhoto(photo);
        return section;
    }

    public String getSectionName() {
        return sectionName;
    }

    public SectionIn setSectionName(String sectionName) {
        this.sectionName = sectionName;
        return this;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public SectionIn setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public SectionIn setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Long getBelongSection() {
        return belongSection;
    }

    public SectionIn setBelongSection(Long belongSection) {
        this.belongSection = belongSection;
        return this;
    }

    public Long getSectionManager() {
        return sectionManager;
    }

    public SectionIn setSectionManager(Long sectionManager) {
        this.sectionManager = sectionManager;
        return this;
    }

    public String getSectionIntroduction() {
        return sectionIntroduction;
    }

    public SectionIn setSectionIntroduction(String sectionIntroduction) {
        this.sectionIntroduction = sectionIntroduction;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public SectionIn setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getPhoto() {
        return photo;
    }

    public SectionIn setPhoto(Long photo) {
        this.photo = photo;
        return this;
    }
}
