package javaa;

/**
 * @Author 洪桂煌
 * @Date 2020/11/1
 */
public class SynchronizedTest2 {
    public static void main(String[] args){
        Window2 w1 = new Window2();
        Window2 w2 = new Window2();
        Window2 w3 = new Window2();

        w1.start();
        w2.start();
        w3.start();
    }
}
class Window2 extends Thread{
    public static int ticket = 100;
    public void run(){
        while(true){
            synchronized(Window2.class){
                if(ticket>0){
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+":"+ticket);
                    ticket--;
                }else{
                    break;
                }
            }
        }
    }
}
