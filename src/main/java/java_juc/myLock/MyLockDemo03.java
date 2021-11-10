package java_juc.myLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程定制化通信  A执行5次，B执行10次，C执行15次，整体再循环10次。
 */

class share {
    private static int version = 1;
    private Lock lock = new ReentrantLock();
    Condition con1 = lock.newCondition();
    Condition con2 = lock.newCondition();
    Condition con3 = lock.newCondition();

    public void runA(int loop) throws InterruptedException {
        lock.lock();

        try {
            while (version != 1) {
                con1.await();
            }

            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "***,执行第" + i + "次，循环第" + loop + "回");
            }

            version = 2;
            con2.signal();

        } finally {
            lock.unlock();

        }


    }

    public void runB(int loop) throws InterruptedException {
        lock.lock();


        try {
            while (version != 2) {
                con2.await();
            }

            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "***,执行第" + i + "次，循环第" + loop + "回");
            }

            version = 3;
            con3.signal();

        } finally {
            lock.unlock();

        }
    }

        public void runC(int loop) throws InterruptedException {
            lock.lock();

            try {
                while (version != 3) {
                    con3.await();
                }

                for (int i = 0; i < 15; i++) {
                    System.out.println(Thread.currentThread().getName() + "***,执行第" + i + "次，循环第" + loop + "回");
                }

                version = 1;
                con1.signal();

            } finally {
                lock.unlock();

            }


        }


    }


    public class MyLockDemo03 {
        public static void main(String[] args) {
            share share = new share();
            new Thread(()->{
                for (int i = 0; i < 10; i++) {

                    try {
                        share.runA(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            },"A_Thread").start();


            new Thread(()->{
                for (int i = 0; i < 10; i++) {

                    try {
                        share.runB(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            },"B_Thread").start();


            new Thread(()->{
                for (int i = 0; i < 10; i++) {

                    try {
                        share.runC(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            },"C_Thread").start();

        }

}
