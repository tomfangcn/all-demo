package test;

public class B extends A {

	// @Override
	// public void sayHelloByTom() {
	// // TODO Auto-generated method stub
	// System.out.println("nihao");
	// }

	public void sayHello() {
		// sayHelloByTom();
		System.out.println("nihao");
	}

	public static void main(String[] args) {
		A b = new A();
		b.sayHello();
	}
}
