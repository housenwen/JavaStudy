package Password;

import java.util.Scanner;

/**请使用代码描述

 1. 写一个方法实现用户登陆，传入用户名和密码
 2. 如果用户名错误，就抛出自定义登陆异常(LoginException)，异常信息为用户名不存在。
 3. 如果密码错了就也抛出登陆异常,异常信息为密码错误
 4. 如果用户名和密码都对了,输出: 欢迎xxx

 说明：正确用户名和密码都是admin
 *
 */
public class Demo {
    public static void main(String[] args) {
        while (true) {
            try {
                login("admin", "admin");
                break;
            } catch (LoginException e) {
                System.out.println(e);

            }
        }
    }

    private static void login(String admin, String admin1) throws LoginException {

        Scanner sc = new Scanner(System.in);


        System.out.println("请输入姓名：");
        String loginName = sc.nextLine();

        System.out.println("请输入密码：");
        String loginPassWord = sc.nextLine();

        if (!loginName.equals(admin)) {
            throw new LoginException("用户名不正确！");
        }
        if (!loginPassWord.equals(admin1)) {

            throw new LoginException("密码不正确");
        } else {
            // iii.如果能来到下面,就说明用户和密码都是对的,输出: 欢迎xxx
            System.out.println("欢迎登陆！！！！");
//            System.exit(0);

        }
    }
}

