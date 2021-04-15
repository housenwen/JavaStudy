import request from './request.js'

/**
 * 添加路线
 * @param data
 */
export function addRoute(data) {
    return request({
        url: 'routeService/addRoute',
        method: 'post',
        data: {
            'data': data,
            "moduleName": "添加路线",
            "operationType": "add"
        }
    })
}

/**
 * 修改路线
 * @param data
 */
export function updateRoute(data) {
    return request({
        url: 'routeService/updateRoute',
        method: 'post',
        data: {
            'data': data,
            "moduleName": "修改路线",
            "operationType": "update"
        }
    })
}

/**
 * 查询线路按线路ID
 * @param data
 */
export function findRouteById(data) {
    return request({
        url: 'routeService/findRouteById',
        method: 'post',
        data: {
            'data': data,
            "moduleName": "按ID查询路线",
            "operationType": "select"
        }
    })
}

/**
 * 线路分页查询
 * @param data
 */
export function findRouteByPage(data) {
    return request({
        url: 'route/favoriteRankByPageAndCondition',
        method: 'post',
        data:data
    })
}
