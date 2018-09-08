package mode.behavioral.strategy;

public class TestStrategy {

	public static void main(String[] args){
		Context cxt = new Context(new Strategy1());
		cxt.doSomething();
		
		cxt = new Context(new Strategy2());
		cxt.doSomething();
		
		cxt = new Context(new Strategy3());
		cxt.doSomething();
	}

}
