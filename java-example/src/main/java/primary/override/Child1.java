package primary.override;

public class Child1 extends Basic{

//    public int i= 0 ;
    public void hello(){
        System.out.println("i am Child1 hello i="+i);
    }
    public static void staticHello(){
        System.out.println("i am Child1 staticHello");
    }

    public void superHello(){
        super.hello();
    }
    public static void main(String[] args) {

        Basic.staticHello();
        Child1.staticHello();
        Basic basic = new Basic();
        basic.hello();
        basic.staticHello();
        //不能直接把一个父类直接转换成子类
//        ((Child1)basic).superHello();
        Child1 child1 = new Child1();
        child1.hello();
        child1.superHello();
        System.out.println("1111111111111");
        child1.staticHello();
        //把子类上转型为父类
        Child1 c = new Child1();
        System.out.println("----"+c.hashCode());
        Basic basic2 = c;
        System.out.println("----"+basic.hashCode());
        //子类上转型父类后，不管父类被覆盖的方法是抽象的还是以实现的，都只能访问子类重写的方法
        basic2.hello();
//        basic2.newHello();
        System.out.println("===+===="+basic2.i);
        basic2.staticHello();
        //子类上转型成父类之后不能直接调用子类的属性，必须下转型子类
        System.out.println(((Child1) basic2).i);
        //子类上转型成父类之后不能直接调用子类的方法，必须下转型子类
//        ((Child1) basic2).superHello();
//        basic2.superHello();
        ((Child1) basic2).staticHello();


        Child1 a = new Child1();
        Basic b = new Basic();
        ((Child1)b).superHello();
    }
}
