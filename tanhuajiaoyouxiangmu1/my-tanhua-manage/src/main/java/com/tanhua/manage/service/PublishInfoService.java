package com.tanhua.manage.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanhua.manage.enums.PublishAuditStateEnum;
import com.tanhua.manage.mapper.PublishInfoMapper;
import com.tanhua.manage.pojo.PublishInfo;
import org.springframework.stereotype.Service;

@Service
public class PublishInfoService extends ServiceImpl<PublishInfoMapper, PublishInfo> {

    /**
     * 根据发布id查询信息
     *
     * @param publishId
     * @return
     */
    public PublishInfo findInfoByPublishId(String publishId) {
        return super.getOne(Wrappers.<PublishInfo>lambdaQuery()
                .eq(PublishInfo::getPublishId, publishId)
        );
    }

    /**
     * 获取发布内容分页数据
     *
     * @param page      当前页码
     * @param pageSize  页尺寸
     * @param sortProp  排序字段
     * @param sortOrder ascending 升序 descending 降序
     * @param id        消息id
     * @param sd        开始时间
     * @param ed        结束时间
     * @param uid       用户ID
     * @return 发布内容分页数据
     */
    public IPage<PublishInfo> findInfoByPage(Integer page, Integer pageSize, String sortProp, String sortOrder, String id, Long sd, Long ed, Long uid, String state) {
        Page<PublishInfo> infoPage = new Page<>(page, pageSize);
        if (StrUtil.isNotEmpty(sortProp) && StrUtil.isNotEmpty(sortOrder)) {
            //设置排序
            sortProp = StrUtil.toUnderlineCase(sortProp);
            if ("ascending".equals(sortOrder)) {
                infoPage.setAsc(sortProp);
            } else {
                infoPage.setDesc(sortProp);
            }
        }
        LambdaQueryWrapper<PublishInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(id)) {
            lambdaQueryWrapper.like(PublishInfo::getUserId, id);
        }
        if (sd != null && ed != null && sd != -1 && sd.equals(ed)) {
            // 当 开始时间 和 结束时间 为相同时，即同一天。结束时间+1天毫秒数
            ed = ed + 86400000;
        }
        if (sd != null && sd > 0) {
            lambdaQueryWrapper.ge(PublishInfo::getCreateDate, sd);
        }
        if (ed != null && ed > 0) {
            lambdaQueryWrapper.le(PublishInfo::getCreateDate, ed);
        }
        if (uid != null) {
            lambdaQueryWrapper.eq(PublishInfo::getUserId, uid);
        } else {
            lambdaQueryWrapper.notIn(PublishInfo::getState, PublishAuditStateEnum.AUTO_BLOCK.getValue(), PublishAuditStateEnum.AUTO_PASS.getValue(), PublishAuditStateEnum.WAIT.getValue());
        }
        if (StrUtil.isNotEmpty(state) && !StrUtil.equals("all", state)) {
            lambdaQueryWrapper.eq(PublishInfo::getState, state);
        }
        return super.getBaseMapper().selectPage(infoPage, lambdaQueryWrapper);
    }

    /**
     * 统计发布内容
     *
     * @param id    消息id
     * @param sd    开始时间
     * @param ed    结束时间
     * @param uid   用户ID
     * @param state 审核类型
     * @return 统计数
     */
    public Long countInfo(String id, Long sd, Long ed, Long uid, String state) {
        LambdaQueryWrapper<PublishInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(id)) {
            lambdaQueryWrapper.like(PublishInfo::getUserId, id);
        }
        if (sd != null && ed != null && sd != -1 && sd.equals(ed)) {
            // 当 开始时间 和 结束时间 为相同时，即同一天。结束时间+1天毫秒数
            ed = ed + 86400000;
        }
        if (sd != null && sd > 0) {
            lambdaQueryWrapper.ge(PublishInfo::getCreateDate, sd);
        }
        if (ed != null && ed > 0) {
            lambdaQueryWrapper.le(PublishInfo::getCreateDate, ed);
        }
        if (uid != null) {
            lambdaQueryWrapper.eq(PublishInfo::getUserId, uid);
        }
        if (StrUtil.isNotEmpty(state)) {
            lambdaQueryWrapper.eq(PublishInfo::getState, state);
        }
        return super.getBaseMapper().selectCount(lambdaQueryWrapper).longValue();
    }
}