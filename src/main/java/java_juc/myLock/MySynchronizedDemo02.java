package java_juc.myLock;
//synchronized实现线程间的通信

class numUtil{
    private static   int num=0;

    public synchronized void  incr(){
//        if (num>0){//虚假唤醒
        while (num>0){//虚假唤醒
            try {
                this.wait(); //wait()方法，从哪里停止就从哪里开始，由于if判断一次之后就直接执行判断之后的代码，即wait被唤醒之后直接执行num++;
                //while判断执行完代码块后会重进入while的判断，即代码块结束之后会再次判断，不会直接执行num++,可以避免虚假唤醒的问题
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        num++;
        System.out.println(Thread.currentThread().getName()+" :: "+num);

        this.notifyAll();

    }

    public synchronized void  decr(){
        while (num<=0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        num--;
        System.out.println(Thread.currentThread().getName()+" :: "+num);

        this.notifyAll();

    }

}

public class MySynchronizedDemo02 {
    public static void main(String[] args) {
        numUtil numUtil = new numUtil();



            new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    numUtil.incr();

                }
            },"A").start();

            new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    numUtil.decr();

                }
            },"B").start();




                new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    numUtil.incr();

                }
                },"C").start();

                new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    numUtil.decr();

                }
                },"D").start();




    }

}
