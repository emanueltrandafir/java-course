package examples.design_patterns.behavioural;

import java.util.ArrayList;
import java.util.List;

public class Observer {
	public static void main(String[] args) {
		new Example().runExample();
	}
}


class Example {
	
	public void runExample() {

		// instantiating the subject/observable
		CashRegister cashRegister = new CashRegister();

		// instantiating the subscribers/observers
		IObserver uiObserver = new UiObserver();
		IObserver otherService = new OtherServiceObserver();
		
		//attaching the observers to the observable
		cashRegister.addObserver(uiObserver);
		cashRegister.addObserver(otherService);
		
		//mutating the subject should trigger the update method of the subscribers
		cashRegister.setState(CashRegister.State.INVENTORY_MODE);
		
	}
}

// the 'subject' or the 'observable' interface
interface Subject {
    void addObserver(IObserver observer);
    void deleteObserver(IObserver observer);
    void notifyObservers(Object payload);
}

// the 'observer' interface
interface IObserver {
    void update(Object payload);
}

// the observable implementation
class CashRegister implements Subject {
    
	public static enum State { SCREENSAVER, CHECKOUT_MODE, INVENTORY_MODE };
	
	private State state = State.CHECKOUT_MODE;
    private List<IObserver> observers = new ArrayList<>();

    public void setState( State newState ) {
    	this.state = newState;
    	notifyObservers( "state changed!" );
    }
    
	@Override
	public void addObserver(IObserver observer) {
		observers.add(observer);
	}

	@Override
	public void deleteObserver(IObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers(Object payload) {
		for(IObserver observer : observers) {
			observer.update(payload);
		}
	}
}

//the impl of a possible observer
class UiObserver implements IObserver {

	@Override
	public void update(Object payload) {
		// update gui or do other stuff here
		System.out.println("UiObserver notified!");
	}
}

//the impl of other observer
class OtherServiceObserver implements IObserver {

	@Override
	public void update(Object payload) {
		// do stuff here if needed
		System.out.println("OtherServiceObserver notified!");
	}
}