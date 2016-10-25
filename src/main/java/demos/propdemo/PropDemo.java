package demos.propdemo;

import java.io.IOException;
import java.util.Properties;

public class PropDemo {
	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(("secure.properties")));
		System.out.println(prop.getProperty("redis_host"));
	}
}
