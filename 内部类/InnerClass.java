package java2;

import java2.Person.Dog;

public class InnerClass {
	public static void main(String[] args) {
		//创建静态内部类的对象
		Dog dog = new Person.Dog();
		dog.talk();
		
		//创建非静态内部类的实例
		Person person = new Person();
		Person.cat cat = person.new cat();
		cat.talk();
		System.out.println("**************调用不同属性“方法display");
		cat.display("形参");
	}
}

class Person{
	String name = "人";
	int age;
	
	public void talk() {
		System.out.println("hello");
	}
	
	
	static class Dog{//静态内部类
		
		
		public void talk() {
			System.out.println("汪汪汪");
		}
	}
	
	class cat{//非静态内部类
		String name = "猫";
		
		public void talk() {
			System.out.println("喵喵喵");
			Person.this.talk();//调用外部类的方法
		}
		
		public void display(String name) {
			System.out.println(name); //方法的形参
			System.out.println(this.name);//内部类的属性
			System.out.println(Person.this.name);//外部类的属性
		}
		
	}
}