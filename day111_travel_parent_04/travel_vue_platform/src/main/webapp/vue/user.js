import request from './request.js'

/**
 * 用户注册
 * @param data
 */
export function registerUser(data) {
  return request({
    url: 'userService/registerUser',
    method: 'post',
    data: {
      'data': data,
      "moduleName":"用户注册",
      "operationType":"register" }
  })
}

/**
 * 用户登录
 * @param data
 */
export function loginUser(data) {
  console.log("用户登录："+data);
  return request({
    url: '/user/login',
    method: 'post',
    data: data
  })
}

/**
 * 当前用户
 */
export function currentSubject() {
  return request({
    url: '/user/getCurrentUser',
    method: 'get'
  })
}

/**
 * 用户退出
 */
export function logout() {
  return request({
    url: '/user/logout',
    method: 'get'
  })
}

/**
 * 用户登录
 * @param data
 */
export function isLogin(data) {
  return request({
    url: '/user/islogin',
    method: 'get'
  })
}









