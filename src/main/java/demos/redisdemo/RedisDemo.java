package demos.redisdemo;

import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RedisDemo {
	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		//maven项目中resources目录下就是classpath下。
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("secret.properties");
		prop.load(is);

		Jedis jedis = new Jedis(prop.getProperty("redis_host"), 6380);
		jedis.auth(prop.getProperty("redis_pass"));
		String value = jedis.get("foo6");
		System.out.println(value);

		// TODO: 2016/10/21 集群暂时有问题,不能给定密码
		//GenericObjectPoolConfig pool = new GenericObjectPoolConfig();
		//pool.setMaxIdle(5);
		//pool.setMaxTotal(1024);
		//pool.setMaxWaitMillis(5000);
		//pool.setTestOnBorrow(true);
		//
		//HostAndPort node1= new HostAndPort(prop.getProperty("redis_host"),6379);
		//HostAndPort node2= new HostAndPort(prop.getProperty("redis_host"),6380);
		//Set<HostAndPort> nodeSet = new HashSet<HostAndPort>();
		//nodeSet.add(node1);
		//nodeSet.add(node2);
		//JedisCluster cluster = new JedisCluster(nodeSet,1000,1000,1000,"foobared",pool);
		//cluster.set("foo","bar2");
		//System.out.println(cluster.get("foo2"));

	}
}
