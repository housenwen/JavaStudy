package itheima;

public class Test {
    public static void main(String[] args) {
        GirlFriend girlFriend = new GirlFriend();
        girlFriend.setHigh(155.0);
        girlFriend.setKing(130);
        girlFriend.setName("王熙凤");
        girlFriend.show();
        girlFriend.cook();
        girlFriend.wash();

        GirlFriend girlFriend1 = new GirlFriend("林黛玉",170,100);
        girlFriend1.show();

    }
}
