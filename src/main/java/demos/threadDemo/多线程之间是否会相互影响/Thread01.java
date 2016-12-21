package demos.threadDemo.多线程之间是否会相互影响;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by kke on 2016/12/16.
 */
public class Thread01 {
    public static void main(String[] aa){
        System.out.println(123);
        Thread t1 = new Thread(new Thread1());
        t1.start();
        Thread t2 = new Thread(new Thread2());
        t2.start();
    }
}

class Thread1 implements Runnable{

    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date());
        }
    }
}

class Thread2 implements Runnable{

    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("AAA");
        }
    }
}