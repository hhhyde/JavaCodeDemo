package demos.异步聚合;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 异步聚合 {
    /**
     * 比如说要调用 abcde 这五个微服务，调用 b 的前提是先调用 a，cde 没有这种顺序要求。最终数据要求整合。怎么才能让时延变小？？
     */
    String serviceA() {
        try {
            int sleepInt = RandomUtils.nextInt(0, 10);
            Thread.sleep(sleepInt * 1000L);
            System.out.println("A" + sleepInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "A";
    }

    String serviceB() {
        try {
            int sleepInt = RandomUtils.nextInt(0, 10);
            Thread.sleep(sleepInt * 1000L);
            System.out.println("B" + sleepInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "B";
    }

    String serviceC() {
        try {
            int sleepInt = RandomUtils.nextInt(0, 10);
            Thread.sleep(sleepInt * 1000L);
            System.out.println("C" + sleepInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "C";
    }

    String serviceD() {
        try {
            int sleepInt = RandomUtils.nextInt(0, 10);
            Thread.sleep(sleepInt * 1000L);
            System.out.println("D" + sleepInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "D";
    }

    String serviceE() {
        try {
            int sleepInt = RandomUtils.nextInt(0, 10);
            Thread.sleep(sleepInt * 1000L);
            System.out.println("E" + sleepInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "E";
    }

    public static void main(String[] args) throws InterruptedException {
        异步聚合 aa = new 异步聚合();
        long start = System.currentTimeMillis();
        CompletableFuture<String> completableFutureA = CompletableFuture.supplyAsync(aa::serviceA).thenApply(s -> {
            // a服务完成后,调用b服务,并将ab服务合并
            try {
                s += CompletableFuture.supplyAsync(aa::serviceB).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return s;
        });
        CompletableFuture<String> completableFutureC = CompletableFuture.supplyAsync(aa::serviceC);
        CompletableFuture<String> completableFutureD = CompletableFuture.supplyAsync(aa::serviceD);
        CompletableFuture<String> completableFutureE = CompletableFuture.supplyAsync(aa::serviceE);
        String collect = Stream.of(completableFutureA, completableFutureC, completableFutureD, completableFutureE).map(CompletableFuture::join).collect(Collectors.joining());
        long end = System.currentTimeMillis();
        System.out.println(collect);
        System.out.println("执行时间" + (end - start) / 1000);
    }
}
