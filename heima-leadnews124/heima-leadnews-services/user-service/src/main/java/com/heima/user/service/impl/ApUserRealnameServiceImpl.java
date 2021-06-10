package com.heima.user.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.constants.admin.AdminConstants;
import com.heima.common.exception.CustException;
import com.heima.common.exception.CustomException;
import com.heima.feigns.article.ArticleFeign;
import com.heima.feigns.wemedia.WemediaFeign;
import com.heima.model.article.pojo.ApAuthor;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dto.AuthDto;
import com.heima.model.user.pojo.ApUser;
import com.heima.model.user.pojo.ApUserRealname;
import com.heima.model.wemedia.pojo.WmUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.mapper.ApUserRealnameMapper;
import com.heima.user.service.ApUserRealnameService;
import com.heima.utils.common.IdCardUtils;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @作者 itcast
 * @创建日期 2021/5/24 19:28
 **/
@Service
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {
    @Override
    public ResponseResult loadListByStatus(AuthDto dto) {
        // 1. 检验
        dto.checkParam();
        // 2. 创建查询条件   分页条件  查询参数
        IPage<ApUserRealname> pageReq = new Page<>(dto.getPage(),dto.getSize());
        LambdaQueryWrapper<ApUserRealname> queryWrapper = Wrappers.<ApUserRealname>lambdaQuery();

        if(dto.getStatus() != null){
            queryWrapper.eq(ApUserRealname::getStatus,dto.getStatus());
        }
        // 3. 执行查询
        IPage<ApUserRealname> pageResult = this.page(pageReq, queryWrapper);
        // 4. 封装返回结果
        PageResponseResult pageResponseResult = new PageResponseResult(dto.getPage(),dto.getSize(),pageResult.getTotal());
        pageResponseResult.setData(pageResult.getRecords());
        return pageResponseResult;
    }

    @Autowired
    ApUserMapper apUserMapper;
    /**
     * @param dto  id 实名认证的id    msg 失败原因
     * @param status 9 审核通过  2 审核失败
     * @return
     */
    @GlobalTransactional
    @Override
    public ResponseResult updateStatusById(AuthDto dto, Short status) {
        // 检查参数  id必须存在
        if(dto.getId() == null){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"实名认证id参数不能为空");
//            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID,"实名认证id参数不能为空");
        }
        // 查询实名认证的数据 （status 1: 待审核 ）
        ApUserRealname realName = getById(dto.getId());
        if(realName == null){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"实名认证信息不存在");
        }
        // 判断审核状态是否为 待审核 => 1
        if(realName.getStatus() != AdminConstants.WAIT_AUTH){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_ALLOW,"该实名认证不是待审核状态");
        }
        // 根据关联userId查询出对应的Ap_user关联数据
        ApUser apUser = apUserMapper.selectById(realName.getUserId());
        if(apUser == null){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"关联的用户信息不存在");
        }
        // 更新实名认证的状态,原因
        realName.setStatus(status);
        if(StringUtils.isNotBlank(dto.getMsg())){
            realName.setReason(dto.getMsg());
        }
        updateById(realName);
        // 如果status=9 审核通过，需要 生成自媒体账户   生成作者信息
        if(status.equals(AdminConstants.PASS_AUTH) ){
//            boolean flag = IdCardUtils.checkIdCardInfo(realName.getName(), realName.getIdno());
//            if(!flag){
//                CustException.cust(AppHttpCodeEnum.SERVER_ERROR,"实名认证失败，身份证信息不匹配");
//            }
            WmUser wmUser = createWmUser(apUser);
            if(wmUser == null || wmUser.getId() == null){
                CustException.cust(AppHttpCodeEnum.SERVER_ERROR,"创建自媒体账户信息失败");
            }
            ResponseResult responseResult = createApAuthor(wmUser);
            if(responseResult.getCode() != 0){
                CustException.cust(AppHttpCodeEnum.SERVER_ERROR,responseResult.getErrorMessage());
            }
        }
//        int i = 1/0;
        // 修改apUser 账户类型为自媒体人
        apUser.setFlag((short)1);
        apUser.setIdentityAuthentication(true);
        apUserMapper.updateById(apUser);
        return ResponseResult.okResult();
    }

    @Autowired
    ArticleFeign articleFeign;

    /**
     * 远程创建作者信息
     * @param wmUser
     * @return
     */
    private ResponseResult createApAuthor(WmUser wmUser) {
        // 查询 ap_user_id相关联的作者信息 是否存在
        ApAuthor author = articleFeign.findByUserId(wmUser.getApUserId());
        if(author!=null){
            CustException.cust(AppHttpCodeEnum.DATA_EXIST,"关联的作者信息已经存在");
        }
        // 创建作者信息
        author = new ApAuthor();
        author.setName(wmUser.getName());
        author.setType(2);
        author.setUserId(wmUser.getApUserId());
        author.setCreatedTime(new Date());
        author.setWmUserId(wmUser.getId());
        return articleFeign.save(author);
    }

    @Autowired
    WemediaFeign wemediaFeign;
    /**
     * 远程 创建自媒体账户
     * @param apUser
     * @return
     */
    private WmUser createWmUser(ApUser apUser) {
        // 远程查询自媒体账户 如果账户已存在，抛账户存在异常
        WmUser wmUser = wemediaFeign.findByName(apUser.getName());
        if(wmUser!=null){
            CustException.cust(AppHttpCodeEnum.SERVER_ERROR,"自媒体账户已经存在");
        }
        // 如果不存在，基于apUser创建自媒体账号
        wmUser = new WmUser();
        BeanUtils.copyProperties(apUser,wmUser);
        wmUser.setId(null);
        wmUser.setApUserId(apUser.getId());
        return wemediaFeign.save(wmUser); // 保存成功后 返回的wmUser携带了 id
    }
}
