package java2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 洪桂煌
 * @Date 2020/11/1
 */
class Window implements Runnable{
    private int ticket = 100;
    private ReentrantLock lock = new ReentrantLock(true);
    @Override
    public void run() {
        while(true){
            try{
                lock.lock();//锁定，只能有一个线程能执行
                if(ticket>0){
                    System.out.println(Thread.currentThread().getName()+"出售第："+ticket+"张票");
                    ticket--;
                }else{
                    break;
                }
            }finally{
                lock.unlock();//解锁
            }

        }
    }
}
public class LoctTest {
    public static void main(String[] args) {
        Window w = new Window();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }

}
