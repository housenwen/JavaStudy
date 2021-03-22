import com.itheima.utils.JedisUtils;
import redis.clients.jedis.Jedis;

public class Test {
    @org.junit.Test
    public void test(){
        Jedis jedis = JedisUtils.getJedis();

        String name = jedis.get("name");

        System.out.println("redis中的数据："+name);

        JedisUtils.close(jedis);
    }
}
