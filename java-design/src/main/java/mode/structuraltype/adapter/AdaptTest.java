package mode.structuraltype.adapter;

/**
 * 
 * @author Administrator
 * 适配器测试类
 */
public class AdaptTest {

	public static void main(String[] args){
//		test1();
//		test2();
		test3();
	}
	
	static void test1(){
		Target1 target1 =  new Adapter1();
		target1.method1();
		target1.method2();
		target1.method3();
	}
	
	static void test2(){
		Target1 target1 =  new Adapter2(new Source1());
		target1.method1();
		target1.method2();
		target1.method3();
	}
	
	static void test3(){
		Source2 source21 =  new Adapter3sub1();
		source21.method1();
		source21.method2();
		source21.method3();
		
		Source2 source22 =  new Adapter3sub1();
		source22.method1();
		source22.method2();
		source22.method3();
	}
}
