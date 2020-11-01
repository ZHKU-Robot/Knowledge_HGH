做了这个两个案例：发现有个套路

- 对于共享数据，创建一个类去定义和操作共享数据

```java
class Account{
    private int balance;//共享数据
    
    private synchronized void deposit(int amt){//同步方法
        this.balance += amt;
    }
}
```

- 创建操作共享数据的线程类

```java
class Custom extends Thread{
    private Account acct;
    public Custom(Account acct){
        this.acct = acct
    }
    public void run(){
        acct.deposit;//通过对象调用同步方法
    }
}
```

- 主方法声明并启动线程

```java
class Test{
    public static void main(String[] args){
        Account acct = new Account();//实例化含共享数据的类
        
        Custom c1 = new Custom(acct);//将共享数据类作为线程类构造函数的参数实例化线程
        Custom c1 = new Custom(acct);//两个线程使用同个共享数据类，在同步方法中使用同个锁
        
        c1.start();
        c2.start();
    }
}
```

