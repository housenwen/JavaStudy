package com.itheima.travel.service;

import com.itheima.travel.req.AffixVo;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AffixServiceTest extends BasicTest {

    @Test
    public void findAffixByBusinessId() {

        AffixVo affixVo = AffixVo.builder()
                .businessId(1l)
                .build();

        List<AffixVo> affixVoList = affixService.findAffixByBusinessId(affixVo);

        for(AffixVo vo:affixVoList){
            System.out.println(vo);
        }
    }
}