package introduction;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lintingjie on 2018/8/22.
 */

public class test {

    public static void main(String[] args) throws InterruptedException {
        UnsafeSequence unsafeSequence = new UnsafeSequence();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        long beginTime  = System.currentTimeMillis();

        Worker worker1 = new Worker("张三", countDownLatch, unsafeSequence);
        Worker worker2 = new Worker("李四", countDownLatch, unsafeSequence);
        worker1.start();
        worker2.start();
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间： "+(endTime-beginTime));

    }

    static class Worker extends Thread{
        String workerName;
        UnsafeSequence unsafeSequence;
        CountDownLatch latch;

        public Worker(String workerName, CountDownLatch latch, UnsafeSequence unsafeSequence){
            this.workerName=workerName;
            this.latch=latch;
            this.unsafeSequence=unsafeSequence;
        }
        public void run(){
            doWork(workerName, unsafeSequence);//工作了
            latch.countDown();//工人完成工作，计数器减一

        }

        private void doWork(String workerName, UnsafeSequence unsafeSequence){
            for (int i=0;i<1000000;i++){
//                System.out.println(workerName+": "+unsafeSequence.nextValue());
                System.out.println(workerName+": "+unsafeSequence.nextValueSync());
            }

        }
    }


}
