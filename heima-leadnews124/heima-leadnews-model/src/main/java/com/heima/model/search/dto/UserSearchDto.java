package com.heima.model.search.dto;
import lombok.Data;
import java.util.Date;
@Data
public class UserSearchDto {

    // 设备ID
    Integer equipmentId;
    /**
    * 搜索关键字
    */
    String searchWords;
    /**
    * 当前页
    */
    int pageNum;
    /**
    * 分页条数
    */
    int pageSize;
    /**
     * 登录用户ID
     */
    Integer entryId;
    /**
    * 最小时间
    */
    Date minBehotTime;
    /**
     * 0 代表搜索功能首页
     * @return
     */
    public int getFromIndex(){
        if(this.pageNum<1)return 0;
        if(this.pageSize<1) this.pageSize = 10;
        return this.pageSize * (pageNum-1);
    }
}