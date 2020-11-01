package java2;

/**
 * @Author 洪桂煌
 * @Date 2020/11/1
 */
class Window5 implements Runnable{
    private int ticket = 1;
    Object obj = new Object();
    public void run(){
        while(true){
            synchronized (obj){
                obj.notify();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(ticket<=100){
                    System.out.println(Thread.currentThread().getName()+":"+ticket);
                    ticket++;
                }else{
                    break;
                }
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}
public class Communication {
    public static void main(String[] args) {
        Window5 w = new Window5();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();

    }
}
