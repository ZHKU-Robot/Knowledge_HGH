# 线程通信

多个线程同时操作共享数据要求交流，就要用到通信。

## 1.1通信的方法

- wait():调用次方法，阻塞线程，并释放锁
- notify()：唤醒被wait阻塞的线程。如果有多个阻塞的线程，唤醒优先级高的那个。
- notifyAll()；唤醒被wait阻塞的全部线程

**例如：打印1到100的数，让两个线程交替打印。**

```java
class Window5 implements Runnable{
    private int ticket = 1;
    Object obj = new Object();
    public void run(){
        while(true){
            synchronized (obj){
                obj.notify();//唤醒线程
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
                    obj.wait();//阻塞线程并释放锁
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
```

## 1.2 sleep()和wait()的异同

1. 相同点
   - 一旦调用此方法，线程进入阻塞状态
2. 不同点
   - 两个方法声明位置不同：sleep()声明与Thread类中，wait()声明与Object()中
   - 调用要求不同：sleep()可以在任何地方调用，wait()只能在同步方法或同步代码块中调用
   - 是否释放同步监视器：如果两方法同时在同步方法或同步代码块被调用sleep()不会释放锁，wait()会释放锁。

