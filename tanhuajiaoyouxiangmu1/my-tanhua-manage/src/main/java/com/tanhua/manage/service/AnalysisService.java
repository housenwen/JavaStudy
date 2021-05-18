package com.tanhua.manage.service;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanhua.manage.enums.AgeRangeEnum;
import com.tanhua.manage.enums.AreaEnum;
import com.tanhua.manage.enums.SexEnum;
import com.tanhua.manage.mapper.AnalysisByDayMapper;
import com.tanhua.manage.mapper.UserInfoMapper;
import com.tanhua.manage.pojo.AnalysisByDay;
import com.tanhua.manage.vo.AnalysisDistributionVo;
import com.tanhua.manage.vo.AnalysisUsersVo;
import com.tanhua.manage.vo.DataPointVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AnalysisService extends ServiceImpl<AnalysisByDayMapper, AnalysisByDay> {


    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 查询活跃用户的数量
     *
     * @param today 今日时间对象
     * @param offset 偏移量
     * @return
     */
    public Long queryActiveUserCount(DateTime today, int offset) {
        return this.queryUserCount(today, offset, "num_active");
    }

    /**
     * 查询注册用户的数量
     *
     * @param today 今日时间对象
     * @param offset 偏移量
     * @return
     */
    public Long queryRegisterUserCount(DateTime today, int offset) {
        return this.queryUserCount(today, offset, "num_registered");
    }

    /**
     * 查询登录用户的数量
     *
     * @param today 今日时间对象
     * @param offset 偏移量
     * @return
     */
    public Long queryLoginUserCount(DateTime today, int offset) {
        return this.queryUserCount(today, offset, "num_login");
    }

    private Long queryUserCount(DateTime today, int offset, String column){
        AnalysisByDay analysisByDay = super.getOne(Wrappers.<AnalysisByDay>query()
                .select("SUM("+column+") AS num_active")
                .le("record_date", today.toDateStr())
                .ge("record_date", DateUtil.offsetDay(today, offset).toDateStr())
        );
        return Long.valueOf(analysisByDay.getNumActive());
    }



    /**
     * 新增、活跃用户、次日留存率
     *
     * @param sd
     * @param ed
     * @param type
     * @return
     */
    public AnalysisUsersVo queryAnalysisUsersVo(Long sd, Long ed, Integer type) {
        DateTime startDate = DateUtil.date(sd);
        DateTime endDate = DateUtil.date(ed);

        AnalysisUsersVo analysisUsersVo = new AnalysisUsersVo();

        //今年数据
        analysisUsersVo.setThisYear(this.queryDataPointVos(startDate, endDate, type));
        //去年数据
        analysisUsersVo.setLastYear(this.queryDataPointVos(
                DateUtil.offset(startDate, DateField.YEAR, -1),
                DateUtil.offset(endDate, DateField.YEAR, -1),
                type)
        );

        return analysisUsersVo;
    }

    private List<DataPointVo> queryDataPointVos(DateTime sd, DateTime ed, Integer type) {
        String startDate = sd.toDateStr();
        String endDate = ed.toDateStr();

        String column = null;
        switch (type) { //101 新增 102 活跃用户 103 次日留存率
            case 101:
                column = "num_registered";
                break;
            case 102:
                column = "num_active";
                break;
            case 103:
                column = "num_retention1d";
                break;
            default:
                column = "num_active";
                break;
        }

        List<AnalysisByDay> analysisByDayList = super.list(Wrappers.<AnalysisByDay>query()
                .select("record_date , " + column + " as num_active")
                .ge("record_date", startDate)
                .le("record_date", endDate));

        return analysisByDayList.stream()
                .map(analysisByDay -> new DataPointVo(DateUtil.date(analysisByDay.getRecordDate()).toDateStr(), analysisByDay.getNumActive().longValue()))
                .collect(Collectors.toList());
    }



    private static final String[] LOCALS = {"上海", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江", "江苏"
            , "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "广西", "海南", "四川"
            , "贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏", "新疆", "北京", "天津", "重庆", "香港", "澳门", "台湾", "南海诸岛"};

    /**
     * 注册用户分布，行业top、年龄、性别、地区
     *
     * @param sd 开始时间
     * @param ed 结束时间
     * @return 注册用户分布，行业top、年龄、性别、地区
     */
    public AnalysisDistributionVo queryUserDistribution(Long sd, Long ed) {
        String startDate = DateUtil.date(sd).toDateStr();
        String endDate = DateUtil.date(ed).toDateStr();

        AnalysisDistributionVo analysisDistributionVo = new AnalysisDistributionVo();

        //行业top10
        analysisDistributionVo.setIndustryDistribution(this.userInfoMapper.findIndustryDistribution(startDate, endDate));

        //性别
        analysisDistributionVo.setGenderDistribution(this.userInfoMapper.findGenderDistribution(startDate, endDate)
                .stream().map(dataPointVo -> {
                    dataPointVo.setTitle(SexEnum.getSexByValue(Integer.valueOf(dataPointVo.getTitle())));
                    return dataPointVo;
                }).collect(Collectors.toList()));

        //年龄
        analysisDistributionVo.setAgeDistribution(new ArrayList<>());
        for (AgeRangeEnum ageRangeEnum : AgeRangeEnum.values()) {
            Long amount = userInfoMapper.countByAge(ageRangeEnum.getMin(), ageRangeEnum.getMax(), startDate, endDate);
            analysisDistributionVo.getAgeDistribution().add(new DataPointVo(ageRangeEnum.getDesc(), amount));
        }

        //地区
        analysisDistributionVo.setLocalDistribution(new ArrayList<>());
        for (String local : LOCALS) {
            Long amount = userInfoMapper.countByProvince(local + "%", startDate, endDate);
            analysisDistributionVo.getLocalDistribution().add(new DataPointVo(local, amount));
        }

        // 地区汇总数据
        Map<String, Long> areaMap = new HashMap<>();
        analysisDistributionVo.getLocalDistribution().forEach(dataPoint -> {
            String area = AreaEnum.getAreaByProvince(dataPoint.getTitle());
            areaMap.put(area, MapUtil.getLong(areaMap, area, 0L) + dataPoint.getAmount());
        });

        analysisDistributionVo.setLocalTotal(
                areaMap.entrySet().stream()
                        .map(entry -> new DataPointVo(entry.getKey(), entry.getValue()))
                        .sorted(Comparator.comparing(DataPointVo::getAmount).reversed())
                        .collect(Collectors.toList()));

        return analysisDistributionVo;
    }
}