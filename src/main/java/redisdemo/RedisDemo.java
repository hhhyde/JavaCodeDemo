package redisdemo;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2016/10/20.
 */
public class RedisDemo {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("host");
		jedis.auth("password");
		String value = jedis.get("foo");
		System.out.println(value);
	}
}
