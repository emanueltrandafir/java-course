package examples.design_patterns.behavioural;

public class Template {
	public static void main(String[] args) {
		new Example().runExample();
	}
}


class Example {
	
	public void runExample() {

		Invoice tenEuroDiscountWthTips = new TenEuroDiscountWithTipsInvoice();
		Invoice tipsAndTva = new TipsAndTvaInvoice();

		System.out.println( tenEuroDiscountWthTips.calculateAmountToPay(100) );
		System.out.println( tipsAndTva.calculateAmountToPay(100) );
		
	}
}

// the template abstract class
abstract class Invoice {

	protected int tips = 0;
	protected int tva = 0;
	protected int discount = 0;
	
	// the 'hooks'
	abstract void addTips();
	abstract void addTva();
	abstract void addDiscount();
	
	public final int calculateAmountToPay( int totalAmount ) {
		addTips();
		addTva();
		addDiscount();
		return totalAmount + tva + tips - discount;
	}
}

// a possible concrete implementation
class TenEuroDiscountWithTipsInvoice extends Invoice {

	@Override
	void addTips() {
		tips = 12; 
		return; // do something here if needed
	}
	@Override
	void addTva() {
		 return;  // do something here if needed
	}
	@Override
	void addDiscount() {
		discount = 10; 
		return; // do something here if needed
	}
}

//other concrete implementation
class TipsAndTvaInvoice extends Invoice {

	@Override
	void addTips() {
		tips = 12; 
		return; // do something here if needed
	}
	@Override
	void addTva() {
		tva = 19;
		return;  // do something here if needed
	}
	@Override
	void addDiscount() {
		return; // do something here if needed
	}
}