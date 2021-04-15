import request from './request.js'

/**
 * 买家信息
 * @param data
 */
export function findSellerVoByid(data) {
    return request({
        url: 'sellerService/findSellerVoByid',
        method: 'post',
        data: {
            'data': data,
            "moduleName":"查询买家信息",
            "operationType":"select" }
    })
}
