package javaa;

/**
 * @Author 洪桂煌
 * @Date 2020/11/1
 */
class Window4 extends Thread{
    private static int ticket = 100;

    public  void run(){
        while(true){
            show();
        }
    }

    public static synchronized void show(){
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
public class SynchronizedTest4 {
    public static void main(String[] args) {
        Window4 w1 = new Window4();
        Window4 w2 = new Window4();
        Window4 w3 = new Window4();

        w1.start();
        w2.start();
        w3.start();
    }
}
