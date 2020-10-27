# 1. 多态
## 1.1. 理解多态性
可以理解为一个事物的多种形态。
## 1.2. 何为多态性：
 对象的多态性：父类的引用指向子类的对象（或子类的对象赋给父类的引用）

## 1.3. 多态的使用：虚拟方法调用
- 有了对象的多态性以后，我们在编译期，只能调用父类中声明的方法，但在运行期，我们实际执行的是子类重写父类的方法。
- 总结：编译，看左边；运行，看右边。
- 对象的多态性，只适用于方法，不适用于属性（编译和运行都看左边）
## 1.4.多态性的使用前提
① 类的继承关系  
② 方法的重写
## 1.5. 多态的小案例代码
定义一个person类，类中有eat这个方法，student类继承person并重写person类中的eat方法。多态声明Person p = new Student();

下面代码输出："学生要多吃有营养的食物"
```java
public class polymorphic {
	public static void main(String[] args) {
		Person p = new Student();//多态
		p.eat();//调用student中eat方法，输出“学生要多吃有营养的食物”
	}
}

class Person{
	int age;
	String name;
	
	public void eat() {
		System.out.println("吃饭啦");
	}
}

class Student extends Person{
	public void eat() {
		System.out.println("学生要多吃有营养的食物");
	}
}
```
## 1.6.  父类的引用不能调用子类特有的方法
使用向下转型才能调用子类特有的属性和方法。

```java
//例如Student为Person的子类
Person p = new Student();
Student stu = (Student)p; //向下转型
```
## 1.7. instanceof
可以使用instanceof关键字看对象A是否为类B的实例。

```java
Person p = new Student();
p instanceof Student;//返回true
```
- 类C是类B的子类，类B是类A的子类，多态实例化A a = new C();
	a是类A、B、C的实例化对象