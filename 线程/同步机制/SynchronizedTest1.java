package javaa;

/**
 * @Author 洪桂煌
 * @Date 2020/10/30
 */
class Window1 implements Runnable{
    private int ticket = 100;

    public void run(){
        synchronized (this){
            while(true){
                if(ticket>0){
                    System.out.println(Thread.currentThread().getName()+":"+ticket);
                    ticket--;
                }
            }
        }

    }
}
public class SynchronizedTest1 {
    public static void main(String[] args) {
        Window1 w1 = new Window1();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w1);
        Thread t3 = new Thread(w1);

        t1.start();
        t2.start();
        t3.start();
    }
}
