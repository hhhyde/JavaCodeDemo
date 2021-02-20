package demos.threadDemo.多线程之间是否会相互影响;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多个线程操作用一对象时，如何保证线程安全
 */
public class 多个线程操作同一集合 {

    // 移动要使用原子类，才能保证线程安全，否则还是不安全的
    static ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();

    public static void main(String[] aa) throws InterruptedException {
        System.out.println("开始");
        for (int i = 0; i < 100; i++) {
            new Thread(new 同步锁(), i + "").start();
        }

        Thread.sleep(2000);
        System.out.println("结束");
    }
}

class 同步锁 implements Runnable{
    @Override
    public void run() {
         synchronized (同步锁.class) {
             多个线程操作同一集合.map.put("key", new AtomicInteger(Integer.parseInt(Thread.currentThread().getName())));
             System.out.println(
                     Thread.currentThread().getName() + "=" + 多个线程操作同一集合.map.get("key") + ","
                             + Thread.currentThread().getName().equals(多个线程操作同一集合.map.get("key") + ""));
         }
    }

}