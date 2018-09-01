package model.observe;

import java.util.ArrayList;
import java.util.List;

public class Observable {

	private List<Observer> observers = new ArrayList<Observer>();

	public boolean regist(Observer observer) {
		return observers.add(observer);
	}

	public boolean remove(Observer observer) {
		return observers.remove(observer);
	}

	public void notifyObserver() {
		observers.stream().forEach(e -> {
			e.update(this);
		});
	}
}
