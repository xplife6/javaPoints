package java_juc.myLock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LTicket {
    private final int tNum=num;
    private static int num = 30;
    private final ReentrantLock lock = new ReentrantLock();

    public  int sale() {

        lock.lock();

        try {

            if (num>0){
                num--;
                System.out.println(Thread.currentThread().getName()+"正在买票,已经售票："+(tNum-num)+"，剩余数量："+num);
            }
        }finally {
            lock.unlock();
        }



        return num;
    }
}

public class MyLockDemo01 {
    public static void main(String[] args) {
        LTicket tickiet = new LTicket();

        new Thread(()->{
            int sale;
            do {
                sale= tickiet.sale();

            }while (sale>1);
        },"A:").start();

        new Thread(()->{
            int sale;
            do {
                sale= tickiet.sale();

            }while (sale>1);
        },"B:").start();

        new Thread(()->{
            int sale;
            do {
                sale= tickiet.sale();

            }while (sale>1);
        },"C:").start();
    }
}
