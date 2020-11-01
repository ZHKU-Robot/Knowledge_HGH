package exer;

/**
 * @Author 洪桂煌
 * @Date 2020/11/1
 */

class Account{
    private int money;

    public Account(int money){
        this.money = money;
    }

    public synchronized void deposit(int amt){
        if(amt>0){
            notify();
            money += amt;
            System.out.println(Thread.currentThread().getName()+"存钱成功，余额为："+money);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
class Customer1 extends Thread{
    private Account acct;

    public Customer1(Account acct) {
        this.acct = acct;
    }
    public void run(){
        for(int i=0; i<3; i++){
            acct.deposit(1000);
        }
    }


}
public class AccountTest {
    public static void main(String[] args) {
        Account acct = new Account(0);

        Customer1 c1 = new Customer1(acct);
        Customer1 c2 = new Customer1(acct);
        c1.start();
        c2.start();
    }
}


