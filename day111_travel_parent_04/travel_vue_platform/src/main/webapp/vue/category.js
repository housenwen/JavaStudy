import request from './request.js'

/**
 * 分页信息
 * @param data
 */
export function findAllCategory(data) {
    return request({
        url: 'category/findAllCategory',
        method: 'get'
    })
}
