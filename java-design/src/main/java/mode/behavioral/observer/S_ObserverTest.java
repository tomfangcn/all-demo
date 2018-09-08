package mode.behavioral.observer;

public class S_ObserverTest {

	public static void main(String[] args){
		
		S_Subject s_subject = new S_Subject();
		
		s_subject.addAttach(new S_Observer1());
		s_subject.addAttach(new S_Observer2());
		s_subject.addAttach(new S_Observer3());

		
		s_subject.sendMsg("msg change");
	}

	
}
