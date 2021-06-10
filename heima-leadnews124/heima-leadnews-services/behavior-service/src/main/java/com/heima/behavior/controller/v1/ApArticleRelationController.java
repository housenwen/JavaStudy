package com.heima.behavior.controller.v1;
import com.heima.behavior.service.ApArticleRelationService;
import com.heima.model.behavior.dto.ApArticleRelationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
@Api("文章行为关系API")
@RestController
@RequestMapping("/api/v1/article_relation")
public class ApArticleRelationController {
    @Autowired
    ApArticleRelationService apArticleRelationService;
    @ApiOperation("查询用户是否点赞，是否不喜欢")
    @PostMapping("one") // {islike:true,isunlike:true,entryId:实体ID}
    public Map findApArticleRelation(@RequestBody ApArticleRelationDto dto) {
        return apArticleRelationService.findApArticleRelation(dto);
    }
}