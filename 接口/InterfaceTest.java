package java1;

public class InterfaceTest{
	public static void main(String[] args) {
		Phane phane = new Phane();
		phane.fly();
	}
}

interface Flyable{
	public abstract void fly();
}

class Phane implements Flyable{
	public void fly() {
		System.out.println("我能飞");
	}
}