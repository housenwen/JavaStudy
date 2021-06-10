package com.heima.feigns.admin;
import com.heima.model.admin.pojo.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
@FeignClient("leadnews-admin")
public interface AdminFeign {
    @GetMapping("/api/v1/channel/channels")
    ResponseResult selectAllChannel();
}