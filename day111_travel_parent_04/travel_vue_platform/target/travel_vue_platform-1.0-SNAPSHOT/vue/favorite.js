import request from './request.js'

/**
 * 我的收藏
 * @param data
 */
export function findMyFavorite(pageNum,pageSize) {
    return request({
        url: 'favoriteService/findMyFavorite',
        method: 'post',
        data: {
            'pageNum':pageNum,
            'pageSize':pageSize,
            "moduleName":"我的收藏",
            "operationType":"select" }
    })
}

/**
 * 是否关注
 * @param data
 */
export function isFavorited(data) {
    return request({
        url: 'favoriteService/isFavorited',
        method: 'post',
        data: {
            'data': data,
            "moduleName":"是否关注",
            "operationType":"select" }
    })
}

/**
 * 添加收藏
 * @param data
 */
export function addFavorite(data) {
    return request({
        url: 'favoriteService/addFavorite',
        method: 'post',
        data: {
            'data': data,
            "moduleName":"添加收藏",
            "operationType":"add" }
    })
}
