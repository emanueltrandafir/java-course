package examples.design_patterns.behavioural;


public class State {
	public static void main(String[] args) {
		new Example().runExample();
	}
}


class Example {
	
	public void runExample() {
		
		CashRegisterUsingStatePattern cashRegister = new CashRegisterUsingStatePattern();

		cashRegister.setState(CashRegisterUsingStatePattern.State.CHECKOUT_MODE);
		cashRegister.onScreenTouched();
		cashRegister.onScreenSwiped(); 

		cashRegister.setState(CashRegisterUsingStatePattern.State.INVENTORY_MODE);
		cashRegister.onScreenTouched();
		cashRegister.onScreenSwiped(); 

		cashRegister.setState(CashRegisterUsingStatePattern.State.SCREENSAVER);
		cashRegister.onScreenTouched();
		cashRegister.onScreenSwiped(); 
		
	}
}



//the base object that can be in different states
// the implementation WITHOUT using the state pattern
class CashRegisterWithoutTheStatePattern {
 
	public static enum State { SCREENSAVER, CHECKOUT_MODE, INVENTORY_MODE };
	
	private State state = State.CHECKOUT_MODE;
	
	public void setState( State newState ) {
		this.state = newState;
	}
	
	public void onScreenTouched() {
		switch(state) {
			case SCREENSAVER: System.out.println("screensaver toched, returning to the previous state"); return;
			case CHECKOUT_MODE: System.out.println("checkout screen touched, checking if transaction is  finished"); return;
			case INVENTORY_MODE: System.out.println("inventory screen touched, starting the inventory report"); return;
			default: return;
		}
	}
	
	public void onScreenSwiped() {
		switch(state) {
			case SCREENSAVER: System.out.println("screensaver swiped, returning to the previous state"); return;
			case CHECKOUT_MODE: System.out.println("checkout screen swiped, startng new transaction"); return;
			case INVENTORY_MODE: System.out.println("inventory screen swiped, cancelng the inventory report"); return;
			default: return;
		}
	}
}


// same functionality, but USING the state pattern will need an interface with the common methods
interface ScreenInteraction {
	void onScreenTouched();
	void onScreenSwiped();
}

//for each of the states it will be created a implementation of this interface
class ScreensaverInteraction implements ScreenInteraction {
	@Override
	public void onScreenTouched() {
		System.out.println("screensaver toched, returning to the previous state");
	}
	@Override
	public void onScreenSwiped() {
		System.out.println("screensaver swiped, returning to the previous state");
	}
}

class CheckoutInteraction implements ScreenInteraction {
	@Override
	public void onScreenTouched() {
		System.out.println("checkout screen touched, checking if transaction is finished");
	}
	@Override
	public void onScreenSwiped() {
		System.out.println("checkout screen swiped, startng new transaction");
	}
}

class InventoryInteraction implements ScreenInteraction {
	@Override
	public void onScreenTouched() {
		System.out.println("inventory screen touched, starting the inventory report");
	}
	@Override
	public void onScreenSwiped() {
		System.out.println("inventory screen swiped, cancelng the inventory report");
	}
}

// the class with mutating state will have one of each these impl as member
class CashRegisterUsingStatePattern{
	
	public static enum State { SCREENSAVER, CHECKOUT_MODE, INVENTORY_MODE };
	
	private ScreenInteraction inventory = new InventoryInteraction();
	private ScreenInteraction screensaver = new ScreensaverInteraction();
	private ScreenInteraction checkout = new CheckoutInteraction();
	
	private ScreenInteraction currentState = checkout;

	public void onScreenTouched() {
		currentState.onScreenTouched();
	}
	
	public void onScreenSwiped() {
		currentState.onScreenSwiped();
	}
	
	public void setState( State newState ) {
		switch (newState) {
			case SCREENSAVER: currentState = screensaver; return;
			case CHECKOUT_MODE: currentState = checkout; return;
			case INVENTORY_MODE: currentState = inventory; return;
			default: return;
		}
	}
}



