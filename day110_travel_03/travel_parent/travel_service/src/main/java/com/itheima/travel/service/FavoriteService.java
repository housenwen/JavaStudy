package com.itheima.travel.service;

        import com.github.pagehelper.PageInfo;
        import com.itheima.travel.req.FavoriteVo;
        import com.itheima.travel.req.PageBean;
        import com.itheima.travel.req.RouteVo;

public interface FavoriteService {

    // public PageBean findMyFavorite(FavoriteVo favoriteVo);
    public PageInfo<RouteVo> findMyFavorite(FavoriteVo favoriteVo);

    public int addFavorite(FavoriteVo favoriteVo);


    /**
     * 是否收藏
     * @param favoriteVo
     * @return
     */
    public boolean isFavorited(FavoriteVo favoriteVo);
}
