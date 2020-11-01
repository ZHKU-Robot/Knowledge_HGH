# 解决线程安全问题——同步机制

当有多个线程同时操作共同数据时会出现线程安全的问题。

**为什么会出现线程安全问题**

​	例如有一个买票系统，如果不加同步系统，当票只有一张时，有两个客户同时要来买票，此时就会有2个线程出现。两个线程同时执行相同的代码去判断票是否为零时都判断还有1张，都向客户同时出售，只有一张票却同时出售了两张，此时数据就出现了异常。此时我们就应该加入同步机制，两个线程要执行同步代码块是时只有一个线程能执行，另一个线程处于阻塞状态，当票只有一张时，获得锁的线程先执行，执行后票就减一变为零并释放锁，另一个线程获得锁后执行同步代码时判断票数为零，不会再向客户出售，此时数据就不会异常不会出现线程安全问题。

## 1. 同步方式1：synchronized

多个线程执行相同代码去操作共同数据时，对要执行的代码块用synchronized代码块封装起来，这时只有一个线程取得锁(同步监测器)才能执行这个代码块其他线程处于阻塞状态，要等取得锁的线程执行完释放锁后cpu再把锁分配给其他线程，其他线程才能执行同步代码块，所以用于synchronized的同步监测器必须唯一。

### 1.1. synchronized代码块

```java
synchronized(同步监测器){
    //需要同步的代码

}
```

1. 实现**Runnable接口**的实现类同使用synchronized同步。 代码为**SynchronizedTest1**

   - 同步监视器为实现类的对象

     ```java
     class Window implements Runnable{
         synchronized(this){
         	//同步代码块
     	}
     }
     ```

     

2. 继承**Thread类的子类**使用synchronized同步。代码为**SynchronizedTest2**

   - 同步监视器为子类本身

   ```java
   class Window extends Thread{
       synchronized(window.class){
       	//同步代码块
   	}
   }
   ```

   

### 1.2 synchronized方法

非静态的同步方法，同步监视器是：this

静态的同步方法，同步监视器是：当前类本身

1. 实现**Runnable接口**的实现类使用synchronized同步方法用实现类的对象(this)来充当锁。 代码为**SynchronizedTest3**

   ```java
   class window implements Runnable{
       public synchronized void show(){//同步监视器为实例化的对象
       	
   	}
   }
   ```

2. 继承Thread类的子类使用synchronized同步方法。 代码为**SynchronizedTest4**

   用synchronized声明的方法要用**static**声明，这样同步监测器就是类本身，确保多个线程使用同个同步监视器

```java
class window extends Thread{
    public static synchronized void show(){//同步监视器为类本身
    
	}	
}
```

## 2. Lock

lock是一个接口，ReentrantLock是Lock的一个实现类

RenntrantLock有两个构造器：①有参(fair)②无参，无参默认fair为false。当fair设置为true时，按线程先后顺序执行同步资源。

```java
class window extends Thread{
    ReentrantLock lock = new ReentrantLock();
    public void run(){
        try{
            lock.lock();//锁定，只能有一个线程能执行
            //同步资源
        }finally{
            lock.unlock();//解锁
        }
    }
}
```

代码案例：LoctTest

## 3. synchronized和Lock的区别

1. 相同点：都可以解决线程安全的问题
2. 不同点
   - 是否自动释放锁:synchronized执行相应代码后自动释放锁；Lock要手动释放锁（启动同步lock.lock()、结束同步lock.unlock()）