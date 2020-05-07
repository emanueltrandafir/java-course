package examples.design_patterns.structural;


public class Proxy {
	public static void main(String[] args) {
		new Example().runExample();
	}
}


class Example {
	
	public void runExample() {
		
		InvoiceDiscount discount = new PercentageDiscountProxy();
		
		int amount1 = discount.calculateAmountToPay(100, 13);
		System.out.println(amount1);
		
		int amount2 = discount.calculateAmountToPay(-100, 20);
		// should stop to proxy

	}
	
}


interface InvoiceDiscount {
	int calculateAmountToPay( int totalAmount, int discountValue );
} 

//the full implementation
class PercentageDiscount implements InvoiceDiscount {
	@Override
	public int calculateAmountToPay(int totalAmount, int discountValue) {
		return totalAmount - (int)(totalAmount - discountValue);
	}
}

// the proxy implementation
class PercentageDiscountProxy implements InvoiceDiscount {
	
	InvoiceDiscount actualDiscount = new PercentageDiscount();
	
	@Override
	public int calculateAmountToPay(int totalAmount, int discountValue) {
		if(totalAmount < 0 || discountValue < 0) {
			System.err.println("the amount and the discount should be positive values!");
			return 0; //or throw exception
		}
		return actualDiscount.calculateAmountToPay(totalAmount, discountValue);
		// this doesn't get evaluated when the proxy validation failes
	}
}