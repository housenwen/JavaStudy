package com.heima.wemedia.controller.v1;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dto.NewsAuthDto;
import com.heima.model.wemedia.dto.WmNewsDto;
import com.heima.model.wemedia.dto.WmNewsPageReqDto;
import com.heima.model.wemedia.pojo.WmNews;
import com.heima.wemedia.service.WmNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "自媒体文章API",tags = "自媒体文章API")
@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController{
    @Autowired
    WmNewsService wmNewsService;
    @ApiOperation("自媒体文章条件查询")
    @PostMapping("/list")
    public ResponseResult findAll(@RequestBody WmNewsPageReqDto dto){
        return wmNewsService.findAll(dto);
    }

    @ApiOperation("自媒体文章发布、修改、草稿")
    @PostMapping("/submit")
    public ResponseResult submitNews(@RequestBody WmNewsDto dto) {
        return wmNewsService.submitNews(dto);
    }

    @ApiOperation("根据ID查询自媒体文章")
    @GetMapping("/one/{id}")
    public ResponseResult findWmNewsById(@PathVariable("id") Integer id) {
        return wmNewsService.findWmNewsById(id);
    }

    @ApiOperation("根据ID删除自媒体文章")
    @GetMapping("/del_news/{id}")
    public ResponseResult delNews(@PathVariable("id") Integer id) {
        return wmNewsService.delNews(id);
    }

    @ApiOperation(value = "设置自媒体文章上下架",notes = " 上架状态: 1   下架状态: 0")
    @PostMapping("/down_or_up")
    public ResponseResult downOrUp(@RequestBody WmNewsDto dto) {
        return wmNewsService.downOrUp(dto);
    }

    @ApiOperation("根据ID查询自媒体文章-服务内部调用")
    @GetMapping("/findOne/{id}")
    public WmNews findById(@PathVariable("id") Integer id) {
        return wmNewsService.getById(id);
    }

    @ApiOperation("根据ID修改自媒体文章-服务内部调用")
    @PutMapping("/update")
    public ResponseResult updateWmNews(@RequestBody WmNews wmNews) {
        wmNewsService.updateById(wmNews);
        return ResponseResult.okResult();
    }
    @ApiOperation(value="查询审核通过待发布",notes="查询状态为4 或 8,  发布时间小于等于当前时间的文章数据")
    @GetMapping("/findRelease")
    public List<Integer> findRelease() {
        return wmNewsService.findRelease();
    }


    @ApiOperation(value = "分页查询封装列表_返回vo",notes = "条件中包含 文章名称 文章状态信息")
    @PostMapping("/list_vo")
    public ResponseResult findList(@RequestBody NewsAuthDto dto) {
        return wmNewsService.findList(dto);
    }


    @ApiOperation("根据文章id查询vo对象")
    @GetMapping("/one_vo/{id}")
    public ResponseResult findWmNewsVo(@PathVariable("id") Integer id) {
        return wmNewsService.findWmNewsVo(id);
    }

    @ApiOperation("修改文章审核状态_审核通过")
    @PostMapping("/auth_pass")
    public ResponseResult authPass(@RequestBody NewsAuthDto dto) {
        return wmNewsService.updateStatus(WmNews.Status.ADMIN_SUCCESS.getCode(),dto);
    }
    @ApiOperation("修改文章审核状态_审核失败")
    @PostMapping("/auth_fail")
    public ResponseResult authFail(@RequestBody NewsAuthDto dto) {
        return wmNewsService.updateStatus(WmNews.Status.FAIL.getCode(),dto);
    }
}