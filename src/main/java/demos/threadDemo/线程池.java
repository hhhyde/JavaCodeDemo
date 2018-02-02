package demos.threadDemo;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.sql.Time;
import java.util.concurrent.*;

public class 线程池 {
    public static void main(String[] as) throws InterruptedException, ExecutionException, TimeoutException {
        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

        // common Thread Pool
        // maximumPoolSize 线程池容量,超过容量的会保存到队列(workQueue)中,超出队列容量的任务会遵照制定的策略(handler)处理
        ExecutorService pool = new ThreadPoolExecutor(15, 20, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1), nameThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

        pool.execute(线程池::doSomething);
        pool.execute(线程池::doSomething);
        pool.execute(线程池::doSomething);
        pool.execute(线程池::doSomething);
        pool.execute(线程池::doSomething);
        pool.execute(线程池::doSomething);
        pool.execute(线程池::doSomething);
        pool.execute(线程池::doSomething);
        pool.execute(线程池::doSomething);
        pool.execute(线程池::doSomething);
        pool.execute(线程池::doSomething);
        pool.execute(线程池::doSomething);
        pool.execute(线程池::doSomething);
        pool.execute(线程池::doSomething);
        pool.shutdown();
    }

    private static void doSomething() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

    private static String doSomethingWithResult() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "done";
    }
}
