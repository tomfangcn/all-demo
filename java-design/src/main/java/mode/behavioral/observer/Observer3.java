package mode.behavioral.observer;



public class Observer3 extends Observer {

	public Observer3(Subject subject){
		this.subject = subject;
		subject.attach(this);
	}

	public void doSomething(){
		System.out.println("Observer3 recieve");
	}
}
