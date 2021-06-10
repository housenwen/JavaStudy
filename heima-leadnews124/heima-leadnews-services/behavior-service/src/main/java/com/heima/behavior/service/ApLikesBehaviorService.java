package com.heima.behavior.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.behavior.dto.LikesBehaviorDto;
import com.heima.model.behavior.pojo.ApLikesBehavior;
import com.heima.model.common.dtos.ResponseResult;

/**
 * <p>
 * APP点赞行为表 服务类
 * </p>
 * @author itheima
 */
public interface ApLikesBehaviorService extends IService<ApLikesBehavior> {
	public ResponseResult like(LikesBehaviorDto dto);
}