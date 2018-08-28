package demos.stream聚合操作;

import org.springframework.util.StreamUtils;

import java.nio.file.Files;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Stream 作为 Java 8 的一大亮点，它与 java.io 包里的 InputStream 和 OutputStream 是完全不同的概念。它也不同于 StAX 对 XML 解析的 Stream，也不是 Amazon Kinesis 对大数据实时处理的 Stream。Java 8 中的 Stream 是对集合（Collection）对象功能的增强，它专注于对集合对象进行各种非常便利、高效的聚合操作（aggregate operation），或者大批量数据操作 (bulk data operation)。Stream API 借助于同样新出现的 Lambda 表达式，极大的提高编程效率和程序可读性。同时它提供串行和并行两种模式进行汇聚操作，并发模式能够充分利用多核处理器的优势，使用 fork/join 并行方式来拆分任务和加速处理过程。通常编写并行代码很难而且容易出错, 但使用 Stream API 无需编写一行多线程的代码，就可以很方便地写出高性能的并发程序。所以说，Java 8 中首次出现的 java.util.stream 是一个函数式语言 + 多核时代综合影响的产物。<a href='https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/'>参考详解</a>
 * <hr />
 * 总之，Stream 的特性可以归纳为：
 *<ul>
 *	<li>不是数据结构</li>
 *	<li>它没有内部存储，它只是用操作管道从 source（数据结构、数组、generator function、IO channel）抓取数据。</li>
 *	<li>它也绝不修改自己所封装的底层数据结构的数据。例如 Stream 的 filter 操作会产生一个不包含被过滤元素的新 Stream，而不是从 source 删除那些元素。</li>
 *	<li>所有 Stream 的操作必须以 lambda 表达式为参数</li>
 *	<li>不支持索引访问</li>
 *	<li>你可以请求第一个元素，但无法请求第二个，第三个，或最后一个。不过请参阅下一项。</li>
 *	<li>很容易生成数组或者 List</li>
 *	<li>惰性化</li>
 *	<li>很多 Stream 操作是向后延迟的，一直到它弄清楚了最后需要多少数据才会开始。</li>
 *	<li>Intermediate 操作永远是惰性化的。</li>
 *	<li>并行能力</li>
 *	<li>当一个 Stream 是并行化的，就不需要再写多线程代码，所有对它的操作会自动并行进行的。</li>
 *	<li>可以是无限的</li>
 *	<li>集合有固定大小，Stream 则不必。limit(n) 和 findFirst() 这类的 short-circuiting 操作可以对无限的 Stream 进行运算并很快完成。</li>
 *</ul>
 */
public class 聚合操作 {
    List<Obj> transactions = new ArrayList<>();

    void java7时代() {
        transactions = init();

        List<Obj> groceryTransactions = new ArrayList<>();
        for (Obj t : transactions) {
            if (t.getType().equals("1")) {
                groceryTransactions.add(t);
            }
        }
        Collections.sort(groceryTransactions, (t1, t2) -> t2.getValue().compareTo(t1.getValue()));

        List<Integer> transactionIds = new ArrayList<>();
        for (Obj t : groceryTransactions) {
            transactionIds.add(t.getId());
        }
    }

    void java8时代() {
        transactions = init();

        transactions.parallelStream().
                filter(t -> t.getType().equals("1")).
                sorted(comparing(Obj::getValue).reversed()).
                map(t -> t.getId()).
                collect(toList());
    }


    void 构造流的几种常见方法() {
        // 1. Individual values
        Stream<String> stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String[] strArray = new String[]{"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        //  数值流的构造
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);


        // 流转换为其它数据结构
        // 1. 流 转 数组
        String[] strArray1 = stream.toArray(String[]::new);
        // 2. 流 转 集合
        List<String> list1 = stream.collect(Collectors.toList());
        List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
        Set set1 = stream.collect(Collectors.toSet());
        Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
        // 3. 流 转 字符串
        String str = stream.collect(Collectors.joining()).toString();



    }

    List<Obj> init() {
        Obj obj1 = new Obj("1", 1, "1");
        Obj obj2 = new Obj("2", 2, "1");
        Obj obj3 = new Obj("3", 3, "2");
        Obj obj4 = new Obj("4", 4, "1");
        Obj obj5 = new Obj("5", 5, "2");
        Obj obj6 = new Obj("6", 6, "1");

        transactions.add(obj1);
        transactions.add(obj2);
        transactions.add(obj3);
        transactions.add(obj4);
        transactions.add(obj5);
        transactions.add(obj6);
        return transactions;
    }

    public static void main(String[] sss) {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        // 串行输出
//        outputStream.forEach(t -> System.out.println(t));
        // 并行输出
//        outputStream.parallel().forEach(t -> System.out.println(t));
        System.out.println(outputStream.findFirst().get());
//        System.out.println(outputStream);

        Stream.generate(new Random()::nextInt).limit(10).forEach(System.out::println);

        IntStream.generate(() -> (int) (System.nanoTime() % 100)).
                limit(10).forEach(System.out::println);
    }


}

class Obj {
    String value;
    int id;
    String type;

    public Obj(String value, int id, String type) {
        this.value = value;
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}