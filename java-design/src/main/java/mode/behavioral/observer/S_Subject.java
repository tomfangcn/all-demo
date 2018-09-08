package mode.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class S_Subject {

	private List<S_Observer> s_observers = new ArrayList<S_Observer>();

	public void sendMsg(String msg) {
		notifyAll(msg);
	}

	// 订阅
	public void addAttach(S_Observer s_observer) {
		s_observers.add(s_observer);
	}

	// 通知所有订阅的观察者
	private void notifyAll(String msg) {
		for (S_Observer s_observer : s_observers) {
			s_observer.doSomething(msg);
		}
	}

}
