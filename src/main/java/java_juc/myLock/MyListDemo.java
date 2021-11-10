package java_juc.myLock;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * AarryList在添加删除时没有上锁，导致线程不安全，一般处理集合写入不安全有以下三种方式：
 * 1.Vector
 *
 *
 *
 */
public class MyListDemo {
    public static void main(String[] args) {
//        List<String> strings = new ArrayList<>();
//        List<String> strings = new Vector<>();
//        List<String> strings = Collections.synchronizedList(new ArrayList<>());

        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();


        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                strings.add(UUID.randomUUID().toString().substring(0,5));

                System.out.println(Thread.currentThread().getName()+"::  "+strings);
            },String.valueOf(i)).start();

        }

    }

}
