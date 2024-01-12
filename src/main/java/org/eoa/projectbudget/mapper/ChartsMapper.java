package org.eoa.projectbudget.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.eoa.projectbudget.entity.Charts;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/12 11:41
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: ChartsMapper
 * @Description: 图表持久层
 * @Version: 1.0
 **/

@Mapper
public interface ChartsMapper extends BaseMapper<Charts> {


}
