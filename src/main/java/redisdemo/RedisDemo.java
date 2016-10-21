package redisdemo;

import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RedisDemo {
	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		//maven项目中resources目录下就是classpath下。
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("secure.properties");
		prop.load(is);
		Jedis jedis = new Jedis(prop.getProperty("redis_host"));
		jedis.auth(prop.getProperty("redis_pass"));
		String value = jedis.get("foo");
		System.out.println(value);
	}
}
