package com.heima.user.service.impl;

import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.constants.admin.AdminConstans;
import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.media.pojos.WmUser;
import com.heima.model.user.dtos.AuthDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserRealname;
import com.heima.user.feign.ApAuthorFeign;
import com.heima.user.feign.WmUserFeign;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.mapper.ApUserRealnameMapper;
import com.heima.user.service.ApUserRealnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {


    @Override
    public PageResponseResult loadListByStatus(AuthDto dto) {
        //参数为空
        if (dto == null) {
            return (PageResponseResult) ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //检查参数
        dto.checkParam();
        QueryWrapper<ApUserRealname> queryWrapper = new QueryWrapper<ApUserRealname>();
        if(dto.getStatus()!=null){
            queryWrapper.lambda().eq(ApUserRealname::getStatus,dto.getStatus());
        }
        IPage pageParam = new Page(dto.getPage(),dto.getSize());
        IPage page = page(pageParam, queryWrapper);
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(),(int)page.getTotal());
        responseResult.setCode(0);
        responseResult.setData(page.getRecords());
        return responseResult;
    }

    @Autowired
    private ApAuthorFeign apAuthorFeign;

    @Autowired
    private WmUserFeign wmUserFeign;

    @Autowired
    private ApUserMapper apUserMapper;

    @Override
    @GlobalTransactional(name="user_auth")
//    @Transactional
    public ResponseResult updateStatusById(AuthDto dto, Short status) {
        if (dto == null || dto.getId()==null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (statusCheck(status)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        ApUserRealname apUserRealname = new ApUserRealname();
        apUserRealname.setId(dto.getId());
        apUserRealname.setStatus(status);
        if (dto.getMsg() != null){
            apUserRealname.setReason(dto.getMsg());
        }
        //1 认证信息表 修改状态
        updateById(apUserRealname);

        //2 认证通过添加自媒体账号, 修改ap_user身份标记
        if (status.equals(AdminConstans.PASS_AUTH)) {
            // 3 创建自媒体账号以及 作者账号
            //int i = 1/0;  只能测试本地事务
            ResponseResult createResult = createWmUserAndAuthor(dto);
            if (createResult != null) {
                return createResult;
            }
            //TODO 发送通知消息
        }
        //int i = 1/0; 可以测试分布式事务
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 创建自媒体账号， 以及作者账号， 并更新apUser标签
     * @param dto
     * @return
     */
    private ResponseResult createWmUserAndAuthor(AuthDto dto) {
        //添加自媒体账号, 查询ap_user信息封装到wmuser中
        ApUserRealname aur = getById(dto.getId());
        ApUser apUser =  apUserMapper.selectById(aur.getUserId());
        if (apUser == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 检测自媒体用户是否存在
        WmUser wmUser = wmUserFeign.findByName(apUser.getName());
        if (wmUser == null || wmUser.getId()==null) {
            wmUser = new WmUser();
            //设置ApUserId
            wmUser.setApUserId(apUser.getId());
            wmUser.setCreatedTime(new Date());
            wmUser.setSalt(apUser.getSalt());
            wmUser.setName(apUser.getName());
            wmUser.setPassword(apUser.getPassword());
            wmUser.setStatus(9);
            wmUser.setPhone(apUser.getPhone());
            wmUserFeign.save(wmUser);
        }

        //创建作者账号
        createAuthor(wmUser);
        //修改ap_user标记
        apUser.setFlag(1);
        apUserMapper.updateById(apUser);
        return null;
    }

    /**
     * 创建自媒体账号
     * @param wmUser
     * @return
     */
    private void createAuthor(WmUser wmUser) {
        Integer apUserId = wmUser.getApUserId();
        ApAuthor apAuthor =  apAuthorFeign.findByUserId(apUserId);
        if (apAuthor == null) {
            apAuthor = new ApAuthor();
            apAuthor.setName(wmUser.getName());
            apAuthor.setType(AdminConstans.AUTHOR_TYPE);
            apAuthor.setCreatedTime(new Date());
            apAuthor.setUserId(apUserId);
            apAuthorFeign.save(apAuthor);
        }
    }

    /**
     * 状态监测
     * @param status
     * @return
     */
    private boolean statusCheck(Short status) {
        if (status == null
                || (!status.equals(AdminConstans.WAIT_AUTH)
                && !status.equals(AdminConstans.FAIL_AUTH)
                && !status.equals(AdminConstans.PASS_AUTH))) {
            return true;
        }
        return false;
    }
}