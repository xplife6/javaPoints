package java_thread.sync;

import com.sun.media.jfxmediaimpl.platform.PlatformManager;
import org.springframework.boot.autoconfigure.transaction.PlatformTransactionManagerCustomizer;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;

public class Snyc3 {


    private   int num=1;
    public  int get(){
        return num;
    }

    public    void changeNum(int i){
        num=i;
    }
    public static void main(String[] args) {
//     Error
//     Exception
//        PlatformTransactionManagerCustomizer
//        LockSupport.park();
//        Integer.valueOf()
        Snyc3 snyc3 = new Snyc3();
        Snyc3 snyc31 = new Snyc3();
        snyc3.changeNum(3);
        snyc31.changeNum(2);
        System.out.println(snyc3.get());


    }

}
