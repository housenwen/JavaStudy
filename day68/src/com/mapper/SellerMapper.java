package com.mapper;

import com.pojo.Seller;

public interface SellerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);
}