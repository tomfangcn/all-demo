package mode.behavioral.observer;



public class Observer2 extends Observer {

	public Observer2(Subject subject){
		this.subject = subject;
		subject.attach(this);
	}

	public void doSomething(){
		System.out.println("Observer2 recieve");
	}
}
