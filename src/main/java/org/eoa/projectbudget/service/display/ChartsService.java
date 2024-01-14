package org.eoa.projectbudget.service.display;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.Charts;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/12 14:36
 * @PackageName: org.eoa.projectbudget.service.display
 * @ClassName: ChartsService
 * @Description: 图表业务类
 * @Version: 1.0
 **/
public interface ChartsService {

    /**
     * 获取图表
     *
     * @param dataId 图表编号
     * @param userId  用户编号
     * @return 图表实体类
     */
    Charts getCharts(Long dataId, Long userId);

    /**
     * 获取图表数组
     *
     * @param wrapper 查询条件
     * @param userId  用户id
     * @return 图表实体类数组
     */
    List<Charts> getChartsList(QueryWrapper<Charts> wrapper, Long userId);

    /**
     * 新建图表
     *
     * @param charts 图表数据
     * @param userId 用户id
     * @return 图标编号
     */
    Long newCharts(Charts charts, Long userId);

    /**
     * 更新图表
     *
     * @param charts 图表数据
     * @param userId 用户编号
     * @return 更新数量
     */
    Integer updateCharts(Charts charts, Long userId);

    /**
     * 删除图表
     *
     * @param dataId 图表编号
     * @param userId 用户编号
     * @return 更新数量
     */
    Integer deleteCharts(Long dataId, Long userId);
}
