package mode.behavioral.observer;



public class Observer1 extends Observer {

	public Observer1(Subject subject){
		this.subject = subject;
		subject.attach(this);
	}

	public void doSomething(){
		System.out.println("Observer1 recieve");
	}
}
