package com.heima.search.service.impl;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.search.dto.UserSearchDto;
import com.heima.model.search.pojo.ApAssociateWords;
import com.heima.search.model.Trie;
import com.heima.search.service.ApAssociateWordsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @作者 itcast
 * @创建日期 2021/6/8 15:33
 *
 * xml bean     init-method
 **/
@Service
public class ApAssociateWordsServiceImpl implements ApAssociateWordsService, InitializingBean {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public ResponseResult findAssociate(UserSearchDto userSearchDto) {
        if (StringUtils.isBlank(userSearchDto.getSearchWords())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        List<ApAssociateWords> associateWords = mongoTemplate.find(Query.query(Criteria.where("associateWords").regex(".*" + userSearchDto.getSearchWords() + ".*")), ApAssociateWords.class);
        return ResponseResult.okResult(associateWords);
    }
    // 变量 引用4中类型:   强引用   软引用  弱引用  虚引用
    public static SoftReference<Trie> softReference;
    @Override
    public ResponseResult findAssociateV2(UserSearchDto userSearchDto) {
        // 当项目启动时  初始化Trie树
        Trie trie = softReference.get();
        if(trie == null){
            initTrieData();
        }
        // 查询联想词  从Trie树种获取包含的联想词
        List<String> dataList = trie.getData(userSearchDto.getSearchWords());
        List<ApAssociateWords> associateWords = dataList.stream().map(str -> {
            ApAssociateWords words = new ApAssociateWords();
            words.setAssociateWords(str);
            return words;
        }).collect(Collectors.toList());
        // 返回结果
        return ResponseResult.okResult(associateWords);
    }

    public void initTrieData(){
        List<ApAssociateWords> list = mongoTemplate.findAll(ApAssociateWords.class);
        Trie trie = new Trie();
        // 将所有联想词 存入到Trie树种
        list.stream().map(ApAssociateWords::getAssociateWords).forEach(trie::insert);
        softReference = new SoftReference<Trie>(trie);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
          // 当前Spring实例准备完毕后  执行的初始化方法
        System.out.println("=============初始化方法=============");
        initTrieData();
    }
}
