package exer;

/**
 * @Author 洪桂煌
 * @Date 2020/11/1
 */
class Clerk{
    private int produceCount = 0;
    public synchronized void produceProduct() {
        if(produceCount<20){
            produceCount++;
            System.out.println(Thread.currentThread().getName()+"开始生产第："+produceCount+"件产品");
            notify();
        }else{
            //等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }//客户

    public synchronized void customProduct() {
        if(produceCount>0){
            System.out.println(Thread.currentThread().getName()+"开始消费第："+produceCount+"件产品");
            produceCount--;
            notify();
        }else{
            //等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Producer extends Thread{//生产者
    Clerk  clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    public void run(){
        System.out.println("生产者开始生产");
        while(true){
            clerk.produceProduct();
        }
    }
}
class Customer extends Thread{//消费者
    Clerk  clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }
    public void run(){
        System.out.println("消费者开始消费");
        while(true){
            clerk.customProduct();
        }
    }
}
public class ProdeceTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer p = new Producer(clerk);
        p.setName("生产者一");
        Customer c1 = new Customer(clerk);
        c1.setName("消费者一");

        p.start();
        c1.start();
    }
}

