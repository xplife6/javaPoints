package java_thread.cas;

import javax.print.attribute.standard.MediaSizeName;
import java.time.LocalDate;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;

public class test {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(test01());
//        ThreadPoolExecutor
//        Lock
//        String
//        new ThreadPoolExecutor()
//        Executors.newFixedThreadPool()
//        RecursiveTask
//        Object
//        Integer integer = new Integer(3);
//        LocalDate
        System.out.println("asda");
        System.out.println("点三次提交");
        System.out.println("master");
        System.out.println("hot-fix");
        System.out.println("changeonline");
    }


    public  static  int test01() throws InterruptedException {
        int i=0;
        try {
//            int a=10/0;
            System.out.println("try");
            return 2;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("exp");
//            Thread.sleep(3000);
            return 3;
        }finally {
            System.out.println("finally");
            int b=1+1;
//            System.out.println(b);
            return 1;
        }
//        return i;
    }
}
