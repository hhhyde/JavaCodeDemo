package demos.threadDemo.多线程之间是否会相互影响;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 当一个大文件徐亚处理时，使用线程池榨干处理器资源
 */
public class 线程池处理大文件 {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(100),
                new ThreadFactoryBuilder().setNameFormat("test-%d").build(),
                (r, executor) -> {
                    if (!executor.isShutdown()) {
                        try {
                            // 主线程将会被阻塞
                            executor.getQueue().put(r);
                        } catch (InterruptedException e) {
                            // should not be interrupted
                        }
                    }

                });
        File file = new File("/Users/Desktop/aa/多行文件");


        LineIterator iterator = null;
        try {
            iterator = IOUtils.lineIterator(new FileInputStream(file), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;

        while (iterator.hasNext()) {
            String line = iterator.nextLine();
            executorService.submit(() -> convertToDB(line));
        }
//        System.out.println("结束");
    }

    private static void convertToDB(String line) {
        System.out.print(line);
    }

}
