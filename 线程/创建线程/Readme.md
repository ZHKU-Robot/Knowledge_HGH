# 1.多线程

## 1.1 创建线程方式一：继承于Thread类

1. 创建一个继承于Thread的子类

2. 重写Thread中的run()方法    ( 线程对象调用start()方法后将调用run()方法 )

3. 实例化Thread的子类

4. 通过对象调用start()方法 

   作用：①启动当前线程 ② 调用当前线程的run()

```java
class MyThread extends Thread{//创建继承于Thread的子类
    public void run(){//重写Thread中的run()方法
        for(int i=0; i<100; i++){
            if(i%2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}
public class ThreadTest {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();//实例化Thread的子类
        MyThread t2 = new MyThread();

        t1.start();//对象调用start()方法
        t2.start();

    }
```

## 1.2创建线程方法二：实现Runnable接口

1. 创建实现Runnable接口的类
2. 实现类去实现Runnable接口中的抽象方法：run()
3. 创建实现类的对象
4. 将实现类的对象作为参数传到Thread类的构造器中，创建Thread的对象
5. 通过Thread类的对象调用start()

```java
class MThread implements Runnable{//实现Runnable的类
    public void run(){//实现run()方法
        for(int i=0; i<100; i++){
            if(i%2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}
public class RunableTest {
    public static void main(String[] args) {
        MThread mThread = new MThread();//创建实现类的对象
        Thread t1 = new Thread(mThread);//将实现类对象作为Thread构造器的参数并创建Thread类对象
        Thread t2 = new Thread(mThread);

        t1.start();//调用start方法启动当前线程并调用run()方法
        t2.start();
    }
}
```

## 1.3 比较两种创建线程的方式

1. 相同点：都要重写run()方法
2. 开发中优先选择实现Runnable()接口的方法
   - 实现方法没有类的单继承的局限性
   - 实现方法更适合处理多个线性有共享数据的情况

## 1.4 创建线程方式三：Callable接口

- 重写call方法，call方法有返回值
- 方法可以抛异常
- 支持泛型
- 需要借助Future接口，FutureTask是Future接口的实现类，同时实现了Runnable和Future接口

```java
class NumThread implements Callable{
    public object call throws Exception{
        //线程的内容
        return null;
    }
}
```

### 1.4.1 创建Callable接口的步骤

1. 创建一个实现Callable接口的实现类

2. 实现类重写Call方法

3. 实例化实现类对象

4. 将实现类对象作为FutureTask构造器参数创建FutureTask对象

5. 将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()

6. 获取Callable中call方法的返回值(futureTask.get())

   get()返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值。

代码为：ThreadNew

## 1.5 创建线程方式四：线程池

创建线程池方法：

- 提供指定线程数量的线程池
- 执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
- 关闭连接池

```java
public class ThreadPool {

    public static void main(String[] args) {
        //1. 提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
        //设置线程池的属性
//        System.out.println(service.getClass());
//        service1.setCorePoolSize(15);
//        service1.setKeepAliveTime();


        //2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new NumberThread());//适合适用于Runnable
        service.execute(new NumberThread1());//适合适用于Runnable

//        service.submit(Callable callable);//适合使用于Callable
        //3.关闭连接池
        service.shutdown();
    }
```



# 2.线程的同步周期

![image-20201101170102394](C:\Users\洪桂煌\Desktop\image-20201101170102394.png)

