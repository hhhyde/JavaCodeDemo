package httpclientdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Async;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.concurrent.FutureCallback;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.apache.http.client.fluent.Request.Get;

public class HttpClientDemo {
	public static void main(String[] args) throws InterruptedException {
		HttpClientDemo demo = new HttpClientDemo();
		demo.fluentAsync();
	}

	/***
	 * 下载文件
	 */
	private void download() {
		try {
			Content response = Get("https://raw.githubusercontent.com/spacelan/wechat-emoticon/master/emoticons.json")
					.execute().returnContent();
			JSONArray jsons = JSON.parseArray(response.asString());
			for (int i = 0; i < jsons.size(); i++) {
				JSONObject json = jsons.getJSONObject(i);
				System.out.println(json.get("url"));
				byte[] emoji = Get(json.get("url").toString()).execute().returnContent().asBytes();
				FileUtils.writeByteArrayToFile(new File("D:/a/" + i + ".gif"), emoji);
			}
			System.out.println(jsons.size());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * post方法带参数
	 *
	 * @throws IOException
	 */
	private void postHead() throws IOException {
		//通过代理执行一个POST请求并添加一个自定义的头部属性,请求包含一个HTML表单类型的请求体
		//将返回的响应内容存入文件
		Request.Post("http://blog.csdn.net/vector_yi")
				.addHeader("X-Custom-header", "stuff")
				.viaProxy(new HttpHost("myproxy", 8080))
				.bodyForm(Form.form().add("username", "vip").add("password", "secret").build())
				.execute().saveContent(new File("result.dump"));
	}

	// TODO: 2016/10/20 待研究

	/***
	 * 异步执行多个请求
	 *
	 * @throws InterruptedException
	 */
	private void fluentAsync() throws InterruptedException {
		// 创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程。
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		Async async = Async.newInstance().use(threadPool);

		Request[] requests = new Request[]{
				Get("http://www.google.com/"),
				Get("http://www.yahoo.com/"),
				Get("http://www.apache.com/"),
				Get("http://www.apple.com/")
		};
		Queue<Future<Content>> queue = new LinkedList<Future<Content>>();
		// 异步执行GET请求
		for (final Request request : requests) {
			Future<Content> future = async.execute(request, new FutureCallback<Content>() {

				public void failed(final Exception ex) {
					System.out.println(ex.getMessage() + ": " + request);
				}

				public void completed(final Content content) {
					System.out.println("Request completed: " + request);
				}

				public void cancelled() {
				}

			});
			queue.add(future);
		}

		while (!queue.isEmpty()) {
			Future<Content> future = queue.remove();
			try {
				future.get();
			} catch (ExecutionException ex) {
			}
		}
		System.out.println("Done");
		threadPool.shutdown();
	}
}