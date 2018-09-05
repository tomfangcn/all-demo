package mode.structuraltype.adapter;
/**
 * 
 * @author Administrator
 * 适配器1
 */
public class Adapter1 extends Source1 implements Target1{
	
	@Override
	public void method2() {
		System.out.println("this is the Target1 method2(Override)!"); 
	}
	
	@Override
	public void method3() {
		System.out.println("this is the Target1 method3!"); 
	}
}
