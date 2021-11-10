package java_thread.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * synchronized为对象锁，使用sychronized的时候可以新建对象或用使用this提供锁定的对象
 *
 * 若sychronized修饰的方法使用static关键字，则锁对象为该 类的class对象
 *
 *
 *
 *
 */

public class Sync2 {

    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                room.incre();
                System.out.println(room.getNum());
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                room.decre();
                System.out.println(room.getNum());

            }
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();



        System.out.println(room.getNum());

    }
}

@Slf4j
class Room{
    int num =0;
    public void incre(){
        synchronized (this){
            num++;


        }
    }

    public void decre(){
        synchronized (this){
            num--;

        }
    }

    public  int getNum(){
        synchronized (this){
            return num;
        }
    }
}

class Room1{
    int num =0;
    public synchronized void incre(){
            num++;
    }

    public synchronized void decre(){
            num--;
    }

    public synchronized int getNum(){
            return num;
    }
}