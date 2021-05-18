package com.tanhua.manage.service;

import com.tanhua.manage.vo.AnalysisDistributionVo;
import com.tanhua.manage.vo.AnalysisUsersVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAnalysisService {

    @Autowired
    private AnalysisService analysisService;

    @Test
    public void testQueryAnalysisUsersVo() {
        AnalysisUsersVo analysisUsersVo = this.analysisService.queryAnalysisUsersVo(1598580158301L, 1601172158301L, 101);
        analysisUsersVo.getThisYear().forEach(dataPointVo -> System.out.println(dataPointVo.getTitle() +":" + dataPointVo.getAmount()));
        System.out.println("-------------------钱钱钱");
        analysisUsersVo.getLastYear().forEach(dataPointVo -> System.out.println(dataPointVo.getTitle() +":" + dataPointVo.getAmount()));
    }

    @Test
    public void testQueryUserDistribution(){
        AnalysisDistributionVo analysisDistributionVo = this.analysisService.queryUserDistribution(1598580158301L, 1601172158301L);
        analysisDistributionVo.getIndustryDistribution().forEach(dataPointVo -> System.out.println(dataPointVo.getTitle() +":" + dataPointVo.getAmount()));

        System.out.println("-------------------");
        analysisDistributionVo.getGenderDistribution().forEach(dataPointVo -> System.out.println(dataPointVo.getTitle() +":" + dataPointVo.getAmount()));

        System.out.println("-------------------");
        analysisDistributionVo.getAgeDistribution().forEach(dataPointVo -> System.out.println(dataPointVo.getTitle() +":" + dataPointVo.getAmount()));

        System.out.println("-------------------");
        analysisDistributionVo.getLocalDistribution().forEach(dataPointVo -> System.out.println(dataPointVo.getTitle() +":" + dataPointVo.getAmount()));

        System.out.println("-------------------");
        analysisDistributionVo.getLocalTotal().forEach(dataPointVo -> System.out.println(dataPointVo.getTitle() +":" + dataPointVo.getAmount()));
    }
}