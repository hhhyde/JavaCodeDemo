package demos.concurrent;

import java.util.*;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
public class 线程安全实现 {
    public static void main(String[] a) {
        List<String> aa = new ArrayList<>();
        aa.add("a");
        aa.add("b");
        aa.add("c");
        aa.add("d");
        aa.add("e");

//        try {
//            for (String ele : aa) {
//                if ("a".equals(ele)) {
//                    aa.remove(ele);
//                }
//            }
//        } catch (ConcurrentModificationException e) {
//            System.out.println(e);
//        }
//        System.out.println(aa.size());
//
//
//        List<String> bb = Collections.synchronizedList(aa);
//        for (String ele : bb){
//            if ("b".equals(ele)) {
//                bb.remove(ele);
//            }
//        }
//        System.out.println(bb.size());

        Iterator<String> ss=aa.iterator();
        while (ss.hasNext()){
            if ("a".equals(ss.next())){
                ss.remove();
            }
        }
        System.out.println(aa.size());
    }
}
