package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.constants.admin.AdminConstants;
import com.heima.common.exception.CustException;
import com.heima.feigns.ArticleFeign;
import com.heima.feigns.WemediaFeign;
import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.AuthDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserRealname;
import com.heima.model.wemedia.pojos.WmUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.mapper.ApUserRealnameMapper;
import com.heima.user.service.ApUserRealnameService;
import com.heima.utils.common.IdCardUtils;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @作者 itcast
 * @创建日期 2021/7/5 9:42
 **/
@Service
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {
    @Override
    public ResponseResult loadListByStatus(AuthDto dto) {
        //1. 校验参数
        if(dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        dto.checkParam(); // 分页
        //2. 执行查询
        //2.1 分页条件
        IPage pageReq = new Page(dto.getPage(),dto.getSize());
        //2.2 查询条件 (status)
        LambdaQueryWrapper<ApUserRealname> queryWrapper = Wrappers.<ApUserRealname>lambdaQuery();
        if(dto.getStatus() != null){
            queryWrapper.eq(ApUserRealname::getStatus,dto.getStatus());
        }
        IPage<ApUserRealname> pageResult = page(pageReq, queryWrapper);
        //3. 封装返回结果
        return new PageResponseResult(dto.getPage(),dto.getSize(),pageResult.getTotal(),pageResult.getRecords());
    }

    @Autowired
    ApUserMapper apUserMapper;


//    @Transactional(rollbackFor = Exception.class)

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult updateStatusById(AuthDto dto, Short status) {
        // 1. 检查参数  dto (id   status 2 9)
        if(dto == null || dto.getId() == null){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"id不能空");
        }
        if(status==null || (!status.equals(AdminConstants.FAIL_AUTH) && !status.equals(AdminConstants.PASS_AUTH))){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"审核状态错误");
        }
        // 2. 根据id查询实名认证信息
        ApUserRealname realnameInfo = getById(dto.getId());
        if(realnameInfo == null){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"实名认证信息不存在");
        }
        // 3. 判断实名认证信息的状态  状态==1 (待审核)
        if(!realnameInfo.getStatus().equals(AdminConstants.WAIT_AUTH)){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_ALLOW,"实名认证信息状态错误");
        }
        // 4. 根据实名认证关联userId 查询出 ap_user表信息
        ApUser apUser = apUserMapper.selectById(realnameInfo.getUserId());
        if(apUser == null){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"实名认证关联用户信息不存在");
        }
        // 调用用友云接口 真实的检测用户身份信息是否真实
//        if(status.equals(AdminConstants.PASS_AUTH)){
//            boolean checkResult = IdCardUtils.checkIdCardInfo(realnameInfo.getName(),realnameInfo.getIdno());
//            if(!checkResult){
//                CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"请输入正确的身份证信息");
//            }
//        }
        // 5. 修改当前实名信息的状态
        realnameInfo.setStatus(status);
        realnameInfo.setUpdatedTime(new Date());
        if(StringUtils.isNotBlank(dto.getMsg())){
            realnameInfo.setReason(dto.getMsg());
        }
        updateById(realnameInfo); // 修改审核状态
        // 6. 判断是否为审核通过 状态 == 9
        if(status.equals(AdminConstants.PASS_AUTH)){ // 代表审核通过


            // 7. 开通自媒体账户
            WmUser wmUser = createWmUser(apUser);
            // 8. 创建作者信息
            createAuthor(apUser,wmUser);
        }

        // 自定义个异常  分布式事务 seata框架
//        int i = 1/0;
        return ResponseResult.okResult();
    }

    @Autowired
    ArticleFeign articleFeign;

    /**
     * 创建作者信息
     * @param apUser
     * @param wmUser
     */
    private void createAuthor(ApUser apUser, WmUser wmUser) {
        ResponseResult<ApAuthor> apAuthorResult = articleFeign.findByUserId(apUser.getId());
        if(apAuthorResult.getCode().intValue() != 0){
            CustException.cust(AppHttpCodeEnum.SERVER_ERROR,apAuthorResult.getErrorMessage());
        }
        ApAuthor  author = apAuthorResult.getData();
        if(author!=null){
            CustException.cust(AppHttpCodeEnum.DATA_EXIST, "作者信息已经存在");
        }
        author = new ApAuthor();
        author.setName(apUser.getName());
        author.setType(2);
        author.setUserId(apUser.getId());
        author.setCreatedTime(new Date());
        author.setWmUserId(wmUser.getId());
        ResponseResult saveResult = articleFeign.save(author);
        if(saveResult.getCode().intValue() != 0 ){ // 远程调用失败
            CustException.cust(AppHttpCodeEnum.DATA_EXIST, saveResult.getErrorMessage());
        }
    }

    @Autowired
    WemediaFeign wemediaFeign;

    /**
     * 开通自媒体账户
     * @param apUser
     * @return
     */
    private WmUser createWmUser(ApUser apUser) {
        // 1. 先查询是否已经开通账户
        ResponseResult<WmUser> responseResult = wemediaFeign.findByName(apUser.getName());//使用用户名查询
        if(responseResult.getCode().intValue()!=0){
            CustException.cust(AppHttpCodeEnum.SERVER_ERROR,responseResult.getErrorMessage());
        }
        // 2. 没有开通，创建WmUser
        WmUser wmUser = responseResult.getData();
        if(wmUser!=null){
            CustException.cust(AppHttpCodeEnum.DATA_EXIST, "自媒体账户已经存在");
        }
        // 3. 保存WmUser
        wmUser = new WmUser();
        wmUser.setApUserId(apUser.getId());
        wmUser.setName(apUser.getName());
        wmUser.setPassword(apUser.getPassword());
        wmUser.setSalt(apUser.getSalt());
        wmUser.setImage(apUser.getImage());
        wmUser.setPhone(apUser.getPhone());
        wmUser.setStatus(9); // 正常
        wmUser.setType(0);
        wmUser.setCreatedTime(new Date());
        ResponseResult<WmUser> saveResult = wemediaFeign.save(wmUser);
        if(saveResult.getCode().intValue() != 0 ){
            CustException.cust(AppHttpCodeEnum.DATA_EXIST, saveResult.getErrorMessage());
        }
        return saveResult.getData();// 返回带id的wmUser
    }
}
