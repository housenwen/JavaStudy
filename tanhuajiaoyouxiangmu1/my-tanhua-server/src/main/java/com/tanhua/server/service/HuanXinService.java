package com.tanhua.server.service;

import cn.hutool.core.bean.BeanUtil;
import com.tanhua.common.utils.UserThreadLocal;
import com.tanhua.dubbo.api.HuanXinApi;
import com.tanhua.dubbo.pojo.HuanXinUser;
import com.tanhua.dubbo.vo.PageInfo;
import com.tanhua.server.vo.HuanXinUserVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class HuanXinService {

    @DubboReference(version = "1.0.0")
    private HuanXinApi huanXinApi;

    public HuanXinUserVo queryHuanXinUser() {
        Long userId = UserThreadLocal.get();
        HuanXinUser huanXinUser = this.huanXinApi.queryHuanXinUser(userId);
        return BeanUtil.toBeanIgnoreError(huanXinUser, HuanXinUserVo.class);
    }
}
