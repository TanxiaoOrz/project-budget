package org.eoa.projectbudget.service.content;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.Content;
import org.eoa.projectbudget.entity.File;
import org.eoa.projectbudget.exception.ParameterException;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 11:29
 * @PackageName: org.eoa.projectbudget.service.content
 * @ClassName: ContentService
 * @Description: 目录模块业务层接口
 * @Version: 1.0
 **/
public interface ContentService {

    /**
     * 批量获取目录
     * @param wrapper 目录条件
     * @param userId 操作人
     * @return 筛选结果
     */
    List<Content> getContentList(QueryWrapper<Content> wrapper,Long userId);

    /**
     * 获取目录信息
     * @param dataId 数据id
     * @param userId 操作人
     * @return 结果
     */
    Content getContent(Long dataId, Long userId);

    /**
     * 新建目录
     * @param content 目录信息
     * @param userId 操作人
     * @return 主键id
     */
    Long newContent(Content content, Long userId) throws ParameterException;

    /**
     * 修改目录
     * @param content 目录结构
     * @param userId 操作人
     * @return 修改数量
     */
    Integer updateContent(Content content, Long userId);

    /**
     * 废弃目录
     * @param dataId 数据编号
     * @param userId 操作人
     * @return 删除数量
     */
    Integer dropContent(Long dataId, Long userId);

    /**
     * 批量获取文件索引
     * @param wrapper 筛选器
     * @param userId 擦欧总人
     * @return 数据集
     */
    List<File> getFileList(QueryWrapper<File> wrapper, Long userId);

    /**
     * 获取文件索引
     * @param dataId 数据编号
     * @param userId 操作人
     * @return 文件索引
     */
    File getFile(Long dataId, Long userId);

    /**
     * 新建文件索引
     * @param file 文件名
     * @param userId 操作人
     * @return 主键id
     */
    Long newFile(File file, Long userId) throws ParameterException;

    /**
     * 修改文件索引
     * @param file 文件结构体
     * @param userId 操作人
     * @return 修改数量
     */
    Integer updateFile(File file, Long userId) throws ParameterException;

    /**
     * 删除文件索引
     * @param dataId 数据编号
     * @param userId 操作人
     * @return 删除数量
     */
    Integer dropFile(Long dataId, Long userId);
}
