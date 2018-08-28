package demos.threadDemo.多线程之间是否会相互影响;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class 多个线程操作同一集合 {

    static Map<String,String> map= new HashMap<>();
    static Map<String,Integer> map1= new HashMap<>();
    static ConcurrentMap<String,Integer> concurrentMap= new ConcurrentHashMap<>();

    public static void main(String[] aa) throws InterruptedException {
        map1.put("aa",0);
        concurrentMap.putIfAbsent("aa",0);
        System.out.println(444);

        for (int i=0;i<100;i++){
            new Thread(new 同步锁(), i + "").start();
        }

        Thread.sleep(10000);
        System.out.println("结束");
    }
}

class 同步锁 implements Runnable{

    @Override
    public void run() {
        多个线程操作同一集合.map1.put("aa",多个线程操作同一集合.map1.get("aa")+1);
        多个线程操作同一集合.concurrentMap.put("aa",多个线程操作同一集合.concurrentMap.get("aa")+1);

         synchronized (同步锁.class){
            多个线程操作同一集合.map.put("onlyOneKey", Thread.currentThread().getName());


            System.out.println(
                    Thread.currentThread().getName() + "=" + 多个线程操作同一集合.map.get("onlyOneKey")+","
                            +Thread.currentThread().getName().equals(多个线程操作同一集合.map.get("onlyOneKey")));
        }
    }

}