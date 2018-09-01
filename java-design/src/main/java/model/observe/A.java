package model.observe;

public class A {

	public static class B {
		private static A a = new A();

	}

	public class D {
		private A a = new A();

	}

	public A getInstanceByB() {
		return B.a;
	}

	public A getInstanceByD() {
		return (new D()).a;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A.B ab = new A.B();
		System.out.println(ab.a);
		System.out.println(A.B.a);
		A.D ad = (new A()).new D();
		System.out.println(ad.a);

	}

}