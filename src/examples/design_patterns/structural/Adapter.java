package examples.design_patterns.structural;

import java.util.Date;

public class Adapter {
	public static void main(String[] args) {
		new Example().runExample();
	}
}


class Example {
	
	public void runExample() {
	
		Invoice invoiceAdapter = new InvoiceDiscountAdapter();
		int amountToPay = invoiceAdapter.calculateTotalPrice(100, 12);
		System.out.println( amountToPay );
	}
}

//the target interface
interface Invoice {
	int calculateTotalPrice(int totalAmount ,  int fixedDiscountValue);
}

//the concrete implementations
class InvoiceDiscountAdapter implements Invoice {

	private static final String ADAPTEE_CURRENCY = "EUR";

	@Override
	public int calculateTotalPrice(int totalAmount, int fixedDiscountValue) { 
		AdapteeDiscount adaptee = new AdapteeDiscount(totalAmount, new Date(), ADAPTEE_CURRENCY);
		return adaptee.amountToPay(fixedDiscountValue);
	}
}

// the adaptee class that doesn't implement the target interface
// -> it has a different method name, signature, constructor

class AdapteeDiscount{
	
	private int amount;
	private Date transactionDate;
	private String currency;

	public AdapteeDiscount(int amount, Date transactionDate, String currency) {
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.currency = currency;
	}
	
	public int amountToPay(int fixedDiscount) {
		return amount - fixedDiscount;
	}
}
 