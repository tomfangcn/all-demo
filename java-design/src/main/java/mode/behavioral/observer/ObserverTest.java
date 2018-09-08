package mode.behavioral.observer;

public class ObserverTest {

	public static void main(String[] args){
		
		Subject subject = new Subject();
		new Observer1(subject);
		new Observer2(subject);
		new Observer3(subject);
		
		subject.setState(10);
		subject.setState(15);
		
	}

	
}
