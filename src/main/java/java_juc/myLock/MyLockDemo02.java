package java_juc.myLock;
//lock实现线程间的通信


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在方法中抛出异常的时候，如果是在加锁的方法体内抛出异常，则会出现死锁的情况，原因未知！！！
 */
class numUtil1{
    private static   int num=0;
    public  Lock lock=new ReentrantLock();
     public Condition con = lock.newCondition();

    public  void  incr() throws InterruptedException {
//        if (num>0){//虚假唤醒

        lock.lock();

        try {

            while (num == 1) {//虚假唤醒
                con.await(); //wait()方法，从哪里停止就从哪里开始，由于if判断一次之后就直接执行判断之后的代码，即wait被唤醒之后直接执行num++;
                //while判断执行完代码块后会重进入while的判断，即代码块结束之后会再次判断，不会直接执行num++,可以避免虚假唤醒的问题
            }

                num++;
                System.out.println(Thread.currentThread().getName() + " :: " + num);
                con.signalAll();
            }finally{
                lock.unlock();
            }

        }


    public  void  decr()
    throws InterruptedException {
//        if (num>0){//虚假唤醒

        lock.lock();

        try {

            while (num!=1){//虚假唤醒

                    con.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+" :: "+num);
            con.signalAll();
        }finally {
            lock.unlock();
        }

    }

}

public class MyLockDemo02 {
    public static void main(String[] args) {
        numUtil1 numUtil = new numUtil1();



            new Thread(()->{
                System.out.println("A");
                for (int i = 0; i < 10; i++) {
                    try {
                        numUtil.incr();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            },"A").start();

        new Thread(()->{
            System.out.println("B");
            for (int i = 0; i < 10; i++) {
                try {
                    numUtil.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"B").start();


        new Thread(()->{
            System.out.println("C");
            for (int i = 0; i < 10; i++) {
                try {
                    numUtil.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"C").start();

        new Thread(()->{
            System.out.println("D");
            for (int i = 0; i < 10; i++) {
                try {
                    numUtil.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            },"D").start();




    }

}
