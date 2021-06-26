package cn.itcast.consumer.web;

import cn.itcast.consumer.config.RedisProperties;
import cn.itcast.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisProperties redisProperties;

    @GetMapping("/rest/{id}")
    public User consumerUserById(@PathVariable("id") Long id){
        String url = "http://127.0.0.1:8081/user/" + id;
        return restTemplate.getForObject(url, User.class);
    }

    @GetMapping("/hello")
    public RedisProperties disUserById(){
        return redisProperties;
    }
}
