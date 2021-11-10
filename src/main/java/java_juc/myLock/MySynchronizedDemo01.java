package java_juc.myLock;

class  Tickiet{
    private final  int  tNum=num;
    private  static int num= 30;

    public   synchronized int sale(){
        if (num>0){
            num--;
            System.out.println(Thread.currentThread().getName()+"正在买票,已经售票："+(tNum-num)+"，剩余数量："+num);

        }
        return num;
    }

}

public class MySynchronizedDemo01 {
    public static void main(String[] args) {
        Tickiet tickiet = new Tickiet();

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
