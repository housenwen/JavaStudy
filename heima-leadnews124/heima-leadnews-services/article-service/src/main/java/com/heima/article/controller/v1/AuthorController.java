package com.heima.article.controller.v1;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.article.service.ApAuthorService;
import com.heima.model.article.pojo.ApAuthor;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Api(value = "作者管理API",tags = "作者管理API")
@RestController
@RequestMapping("/api/v1/author")
public class AuthorController{
    @Autowired
    ApAuthorService apAuthorService;
    @ApiOperation("根据APP用户ID查询关联作者")
    @GetMapping("/findByUserId/{userId}")
    public ApAuthor findByUserId(@PathVariable("userId") Integer userId) {
        return apAuthorService.getOne(Wrappers.<ApAuthor>lambdaQuery().eq(ApAuthor::getUserId,userId));
    }
    @ApiOperation("保存作者信息")
    @PostMapping("/save")
    public ResponseResult save(@RequestBody ApAuthor apAuthor) {
        apAuthorService.save(apAuthor);
        return ResponseResult.okResult();
    }

    @ApiOperation("根据作者ID查询作者信息")
    @GetMapping("/one/{id}")
    public ApAuthor findById(@PathVariable("id") Integer id) {
        return apAuthorService.getById(id);
    }
}