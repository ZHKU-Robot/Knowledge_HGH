内部类
1. Java中允许将一个类A声明在另一个类B中，则类A就是内部类，类B称为外部类

2. 内部类的分类：成员内部类（静态、非静态）  vs 局部内部类(方法内、代码块内、构造器内)

3. 成员内部类：

    3.1 作为外部类的成员：

- 调用外部类的结构

- 可以被static修饰

- 可以被4种不同的权限修饰

  3.2 另一方面，作为一个类：

- 类内可以定义属性、方法、构造器等

- 可以被final修饰，表示此类不能被继承

- 可以被abstract修饰

4.关注如下的3个问题
- 4.1 如何实例化成员内部类的对象

  ```java
  //Person为外部类，Dog为静态内部类，Cat为非静态内部类
  
  //创建静态内部类的对象
  Dog dog = new Person.Dog();
  dog.talk();
  		
  //创建非静态内部类的实例
  Person person = new Person();
  Person.cat cat = person.new cat();
  ```

  

- 4.2 如何在成员内部类中区分调用外部类的结构

  ```java
  public void display(String name) {//此方法为内部类的一个方法
  			System.out.println(name); //方法的形参
  			System.out.println(this.name);//内部类的属性
  			System.out.println(Person.this.name);//外部类的属性
  		}
  ```

- 4.3 开发中局部内部类的使用  见《InnerClassTest1.java》

