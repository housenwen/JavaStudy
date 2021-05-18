package com.tanhua.manage.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tanhua.dubbo.api.QuanZiApi;

import com.tanhua.dubbo.pojo.Publish;
import com.tanhua.manage.enums.PublishAuditStateEnum;
import com.tanhua.manage.exception.BusinessException;
import com.tanhua.manage.pojo.PublishAuditLog;
import com.tanhua.manage.pojo.PublishInfo;
import com.tanhua.manage.pojo.UserInfo;
import com.tanhua.manage.service.PublishAuditLogService;
import com.tanhua.manage.service.PublishInfoService;
import com.tanhua.manage.service.UserInfoService;
import com.tanhua.manage.service.UserService;
import com.tanhua.manage.vo.MovementsVo;
import com.tanhua.manage.vo.Pager;
import com.tanhua.manage.vo.TotalVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manage/messages")
public class MovementsController {

    @Autowired
    private PublishInfoService publishInfoService;

    @DubboReference(version = "1.0.0")
    private QuanZiApi quanZiApi;
    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PublishAuditLogService publishAuditLogService;
    /**
     * 消息翻页
     *
     * @param page      当前页码
     * @param pageSize  页尺寸
     * @param sortProp  排序字段
     * @param sortOrder ascending 升序 descending 降序
     * @param id        消息id
     * @param sd        开始时间
     * @param ed        结束时间
     * @param uid       用户ID
     * @return 消息分页数据
     */
    @GetMapping
    public Pager<MovementsVo> findByPage(@RequestParam(name = "page", defaultValue = "1") Integer page
            , @RequestParam(name = "pagesize", defaultValue = "10") Integer pageSize
            , @RequestParam(name = "sortProp", required = false) String sortProp
            , @RequestParam(name = "sortOrder", required = false) String sortOrder
            , @RequestParam(name = "id", required = false) String id
            , @RequestParam(name = "sd", required = false) Long sd
            , @RequestParam(name = "ed", required = false) Long ed
            , @RequestParam(name = "uid", required = false) Long uid
            , @RequestParam(name = "state", required = false) String state) {

        IPage<PublishInfo> infoPage = publishInfoService.findInfoByPage(page, pageSize, sortProp, sortOrder, id, sd, ed, uid, state);
        //状态合计
        // 当前状态合计 仅包含 3为待人工审核，4为人工审核拒绝，5为人工审核通过 和 3个状态聚合数
        Long num_wait_maul = publishInfoService.countInfo(id, sd, ed, uid, PublishAuditStateEnum.WAIT_MAUL.getValue());
        Long num_maul_block = publishInfoService.countInfo(id, sd, ed, uid, PublishAuditStateEnum.MAUL_BLOCK.getValue());
        Long num_maul_pass = publishInfoService.countInfo(id, sd, ed, uid, PublishAuditStateEnum.MAUL_PASS.getValue());
        Long count = num_wait_maul + num_maul_block + num_maul_pass;

        List<TotalVo> totals = new ArrayList<>();
        totals.add(new TotalVo("全部", "all", count));
        totals.add(new TotalVo("待审核", PublishAuditStateEnum.WAIT_MAUL.getValue(), num_wait_maul));
        totals.add(new TotalVo("已通过", PublishAuditStateEnum.MAUL_PASS.getValue(), num_maul_pass));
        totals.add(new TotalVo("已拒绝", PublishAuditStateEnum.MAUL_BLOCK.getValue(), num_maul_block));

        return new Pager(infoPage.getTotal()
                , infoPage.getSize()
                , infoPage.getPages()
                , infoPage.getCurrent()
                , infoPage.getRecords().stream().map(info -> fillMovements(info)).collect(Collectors.toList())
                , totals);
    }

    /**
     * 填充消息信息
     *
     * @param info 原始消息信息
     * @return 填充后信息
     */
    private MovementsVo fillMovements(PublishInfo info) {
        MovementsVo vo = BeanUtil.toBean(info, MovementsVo.class);
        vo.setId(info.getPublishId());
        Publish publish = quanZiApi.queryPublishById(info.getPublishId());
        if (publish != null) {
            vo.setText(publish.getText());
            vo.setMedias(publish.getMedias());
        } else {
            //TODO 模拟生成测试数据
            vo.setText("动态消息的标题~~~" + info.getUserId());
            vo.setMedias(ListUtil.toList(
                    "https://tanhua-dev.oss-cn-zhangjiakou.aliyuncs.com/images/logo/15.jpg",
                    "https://tanhua-dev.oss-cn-zhangjiakou.aliyuncs.com/images/logo/18.jpg",
                    "https://tanhua-dev.oss-cn-zhangjiakou.aliyuncs.com/images/logo/7.jpg"
            ));
        }
        UserInfo userInfo = this.userInfoService.queryUserInfo(info.getUserId());
        if (userInfo != null) {
            vo.setUserId(userInfo.getUserId());
            vo.setNickname(userInfo.getNickName());
            vo.setUserLogo(userInfo.getLogo());
        }
        return vo;
    }

    /**
     * 消息详情
     *
     * @param id 消息id
     * @return 消息信息
     */
    @GetMapping("/{id}")
    public MovementsVo findById(@PathVariable(name = "id") String id) {
        PublishInfo info = publishInfoService.findInfoByPublishId(id);
        if (info == null) {
            throw new BusinessException("查询的对象不存在");
        }
        return fillMovements(info);
    }
    /**
     * 消息置顶
     *
     * @param id 消息id
     * @return 操作结果
     */
    @PostMapping("/{id}/top")
    public Boolean top(@PathVariable(name = "id") String id) {
        PublishInfo info = publishInfoService.findInfoByPublishId(id);
        if (info == null) {
            throw new BusinessException("操作的对象不存在");
        }
        info.setTopState(2);
        publishInfoService.saveOrUpdate(info);
        return true;
    }

    /**
     * 消息取消置顶
     *
     * @param id 消息id
     * @return 操作结果
     */
    @PostMapping("/{id}/untop")
    public Boolean untop(@PathVariable(name = "id") String id) {
        PublishInfo info = publishInfoService.findInfoByPublishId(id);
        if (info == null) {
            throw new BusinessException("操作的对象不存在");
        }
        info.setTopState(1);
        publishInfoService.saveOrUpdate(info);
        return true;
    }
    /**
     * 消息通过
     *
     * @param ids 消息id集合
     * @return 操作结果
     */
    @PostMapping("/pass")
    public Boolean pass(@RequestBody List<String> ids) {
        ids.forEach(id -> {
            PublishInfo info = publishInfoService.findInfoByPublishId(id);
            if (info != null) {
                if (!info.getState().equals(PublishAuditStateEnum.WAIT_MAUL.getValue())) {
                    throw new BusinessException("当前状态有误，请检查后操作");
                }
                // 当前消息状态为 待人工审核时才可操作
                info.setState(PublishAuditStateEnum.MAUL_PASS.getValue());
                PublishAuditLog log = new PublishAuditLog(null, info.getPublishId(), info.getState(), PublishAuditStateEnum.MAUL_PASS.getValue());
                publishInfoService.saveOrUpdate(info);
                publishAuditLogService.save(log);
            }
        });
        return true;
    }

    /**
     * 消息拒绝
     *
     * @param ids 消息id集合
     * @return 操作结果
     */
    @PostMapping("/reject")
    public Boolean reject(@RequestBody List<String> ids) {
        ids.forEach(id -> {
            PublishInfo info = publishInfoService.findInfoByPublishId(id);
            if (info != null) {
                if (!info.getState().equals(PublishAuditStateEnum.WAIT_MAUL.getValue())) {
                    throw new BusinessException("当前状态有误，请检查后操作");
                }
                // 当前消息状态为 待人工审核时才可操作
                PublishAuditLog log = new PublishAuditLog(null, info.getPublishId(), info.getState(), PublishAuditStateEnum.MAUL_BLOCK.getValue());
                info.setState(PublishAuditStateEnum.MAUL_BLOCK.getValue());
                publishInfoService.saveOrUpdate(info);
                publishAuditLogService.save(log);
            }
        });
        return true;
    }

    /**
     * 消息撤销
     *
     * @param ids 消息id集合
     * @return 操作结果
     */
    @PostMapping("/revocation")
    public Boolean revocation(@RequestBody List<String> ids) {
        ids.forEach(id -> {
            PublishInfo info = publishInfoService.findInfoByPublishId(id);
            if (info != null) {
                if (!info.getState().equals(PublishAuditStateEnum.MAUL_BLOCK.getValue()) && !info.getState().equals(PublishAuditStateEnum.MAUL_PASS.getValue())) {
                    throw new BusinessException("当前状态有误，请检查后操作");
                }
                // 当前消息状态为 人工审核通过或拒绝 时才可操作
                PublishAuditLog log = new PublishAuditLog(null, info.getPublishId(), info.getState(), PublishAuditStateEnum.WAIT_MAUL.getValue());
                info.setState(PublishAuditStateEnum.WAIT_MAUL.getValue());
                publishInfoService.saveOrUpdate(info);
                publishAuditLogService.save(log);
            }
        });
        return true;
    }
}
