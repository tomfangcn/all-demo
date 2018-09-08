package mode.behavioral.strategy;

public class Context {

	private Strategy strategy;

	public Context(Strategy strategy) {
		super();
		this.strategy = strategy;
	}

	
	public void doSomething(){
		strategy.doSomething();
	}
	
}
