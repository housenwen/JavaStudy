import { getToken, removeToken } from './auth.js'
import { logout } from './user.js'

// 每次请求携带cookies信息
axios.defaults.withCredentials = true;

// create an axios instance
const service = axios.create({
    baseURL: "http://127.0.0.1:8080",
    timeout: 5000 // request timeout
});

//请求之前拦截
service.interceptors.request.use(
    config => {
        // config.headers['accessToken'] = getToken();
        // console.log('设置头部传递accessToken:' + config.headers['accessToken']);
        return config
    },
    error => {
        // do something with request error
        console.log(error) ;// for debug
        return Promise.reject(error)
    }
);

//返回之后处理
service.interceptors.response.use(
    response => {
        console.log(response);
        const res = response.data;
        console.log(res.code);
        if (res.code !== '200') {
            //未知异常处理
            if (res.code === '-1') {
                Vue.prototype.$confirm('超时，可以取消继续留在该页面，或者重新登录', '确定登出', {
                    confirmButtonText: '重新登录',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    removeToken();
                    location.href="index.html";
                })
            }
            //未登录处理
            else if (res.code === '1001') {
                logout().then(() => {
                    location.href="login.html";
                })
            } else {
                Vue.prototype.$message({
                    message: res.msg,
                    type: 'error',
                    duration: 5 * 1000
                });
                return res
            }
        } else {
            return res
        }
    },
//错误处理
    error => {
        console.log('err' + error); // for debug
        Vue.prototype.$message({
            message: '请求出错，请联系管理员!',
            type: 'error',
            duration: 5 * 1000
        });
        return Promise.reject(error)
    }
);

export default service



