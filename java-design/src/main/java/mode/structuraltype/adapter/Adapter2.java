package mode.structuraltype.adapter;
/**
 * 
 * @author Administrator
 * 适配器1
 */
public class Adapter2 extends Source1 implements Target1{
	
	private Source1 source1;
	
	
	public Adapter2(Source1 source1) {
		super();
		this.source1 = source1;
	}

	
	
	public void setSource1(Source1 source1) {
		this.source1 = source1;
	}

	@Override
	public void method1() {
		source1.method1();
	}
	
	@Override
	public void method2() {
		source1.method2();
	}
	
	@Override
	public void method3() {
		System.out.println("this is the Target1 method3!"); 
	}
}
