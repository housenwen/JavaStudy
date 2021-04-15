//声明cookie的key
const TokenKey = 'accessToken'

//获得会话token
export function getToken() {
  return Cookies.get(TokenKey)
}

//添加会话token
export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

//移除会话token
export function removeToken() {
  return Cookies.remove(TokenKey)
}
