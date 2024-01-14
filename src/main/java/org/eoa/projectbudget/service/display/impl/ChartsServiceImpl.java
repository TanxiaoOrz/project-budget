package org.eoa.projectbudget.service.display.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.Charts;
import org.eoa.projectbudget.mapper.ChartsMapper;
import org.eoa.projectbudget.service.display.ChartsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 16:07
 * @PackageName: org.eoa.projectbudget.service.display.impl
 * @ClassName: ChartsServiceImpl
 * @Description: TODO
 * @Version: 1.0
 */

@Service
public class ChartsServiceImpl implements ChartsService {

    private final Logger log = LoggerFactory.getLogger("DisplayModule");

    @Autowired
    ChartsMapper chartsMapper;

    @Override
    public List<Charts> getChartsList(QueryWrapper<Charts> wrapper, Long userId) {
        List<Charts> charts = chartsMapper.selectList(wrapper);
        log.info("用户+>{}批量获取图表数据=>{}",userId,charts.size());
        return charts;
    }

    @Override
    public Charts getCharts(Long dataId, Long userId) {
        Charts charts = chartsMapper.selectById(dataId);
        log.info("用户=>{}获取图表=>{},success=>{}",userId,dataId,charts==null);
        return charts;
    }

    @Override
    public Long newCharts(Charts charts, Long userId) {
        charts.setCreator(userId).setCreateTime(new Date());

        chartsMapper.insert(charts);
        log.info("用户=>{}创建图表=>{}",userId,charts.getDataId());
        return charts.getDataId();
    }

    @Override
    public Integer updateCharts(Charts charts, Long userId) {
        Charts old = chartsMapper.selectById(charts.getDataId());
        charts.setCreator(old.getCreator())
                .setCreateTime(old.getCreateTime());
        int i = chartsMapper.updateById(charts);
        log.info("用户=>{}修改图表=>{}",userId,charts.getDataId());
        return i;
    }

    @Override
    public Integer deleteCharts(Long dataId, Long userId) {
        Integer drop = chartsMapper.deleteById(dataId);
        log.info("用户=>{}废弃图表=>{}",userId,dataId);
        return drop;
    }

}
