package java_thread.sync;

public class Sync1 {
    static int num=0;
    static  Object lock=new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                synchronized (lock){
                    num++;
                    System.out.println(num);
                }
            }
        }, "t1");


        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                synchronized (lock){
                    num--;
                    System.out.println(num);

                }
            }
        }, "t2");

        t1.start();

        t2.start();
        t1.join();
        t2.join();
        //一下写法会出现500的输出结果，原因是线程t1执行完成，线程t2才创建，创建完后主线程继续执行输出结果，此时t2还在运行
//        new Thread(() -> {
//            for (int i = 0; i < 500; i++) {
//                num++;
//            }
//        }, "t1").start();
//
//
//        new Thread(() -> {
//            for (int i = 0; i < 500; i++) {
//                num--;
//            }
//        }, "t2").start();

        System.out.println(num);



    }

}
