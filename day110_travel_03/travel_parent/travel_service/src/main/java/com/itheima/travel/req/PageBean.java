package com.itheima.travel.req;

import com.itheima.travel.pojo.Route;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {

    private List<Route> routeList;

    private long totalCount;
}
