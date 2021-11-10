1.Synchronized  同步锁


2.Lock       锁


异同：
1.Synchronized 是关键字;lock是一个java类；
2.Syncronized 自动上锁和解锁,发生异常的时候也会自动解锁；
而Lock需要手动执行上锁和解锁的需求，由于异常之后不会自动解锁，ulock()的解锁必须写到finall中；
3.Lock可以让等待锁的线程中断，而Synchronnized却不行，使用synchronized时，等待线程会一直等待下去，不能够响应中断；
4.通过Lock可以知道有没有成功获取锁，而synchronized却没办法办到
5.Lock可以提高多个线程进行读写操作的效率
从性能上来看，竞争资源不激烈时，两者性能差不多；资源竞争激烈，有大量线程抢夺资源时，Lock性能更优。
