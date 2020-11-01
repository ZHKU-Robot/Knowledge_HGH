package javaa;

import static java.lang.Thread.sleep;

/**
 * @Author 洪桂煌
 * @Date 2020/11/1
 */
class Window3 implements Runnable{
    private int ticket = 100;

    public synchronized void run(){//锁为this对象
        while(true){
            show();
        }
    }

    public void show(){
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(ticket>0){
            System.out.println(Thread.currentThread().getName()+":"+ticket);
            ticket--;
        }
    }

}
public class SynchronizedTest3 {
    public static void main(String[] args) {
        Window3 w3 = new Window3();

        Thread t1 = new Thread(w3);
        Thread t2 = new Thread(w3);
        Thread t3 = new Thread(w3);

        t1.start();
        t2.start();
        t3.start();
    }
}

