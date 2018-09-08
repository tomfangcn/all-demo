package mode.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {

	private List<Observer> observers = new ArrayList<Observer>();
	
	
	private int state;


	public List<Observer> getObservers() {
		return observers;
	}


	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
		notifyAllObservers();
	}
	
	public void attach(Observer observer){
		this.observers.add(observer);
	}
	
public void notifyAllObservers(){
	for(Observer observer : observers){
		observer.doSomething();
	}
}
	
	
}
