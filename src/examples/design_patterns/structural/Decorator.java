package examples.design_patterns.structural;

 
public class Decorator {
	public static void main(String[] args) {
		new Example().runExample();
	}
}


class Example {
	
	public void runExample() {
	
		int amountToPay = 100;
		int discountPercentage = 10;

		Invoice invoiceWithDiscount = new InvoiceWithDiscount();
		Invoice invoiceWithDiscountAndTva = new TvaDecorator(invoiceWithDiscount);
		Invoice invoiceWithDiscountAndTips = new TipsDecorator(invoiceWithDiscount);
		Invoice invoiceWithDiscountTvaAndTips = new TipsDecorator(invoiceWithDiscountAndTva);

		System.out.println( invoiceWithDiscount.calculatePrice(amountToPay, discountPercentage) );
		System.out.println( invoiceWithDiscountAndTva.calculatePrice(amountToPay, discountPercentage) );
		System.out.println( invoiceWithDiscountAndTips.calculatePrice(amountToPay, discountPercentage) );
		System.out.println( invoiceWithDiscountTvaAndTips.calculatePrice(amountToPay, discountPercentage) );
	}
}

//the interface
interface Invoice {
	int calculatePrice(int totalAmount ,  int discountValue );
}

//the concrete implementations
class InvoiceWithDiscount implements Invoice {
	@Override
	public int calculatePrice( int totalAmount , int percentageValue ) {
		return totalAmount - (int)(totalAmount * percentageValue / 100); 
	}
}

//first decorator class
class TvaDecorator implements Invoice {

	private static final int TVA_PERCENT = 19;
	private Invoice discount;
	
	public TvaDecorator(Invoice discount) {
		this.discount = discount;
	}

	@Override
	public int calculatePrice(int totalAmount, int discountValue) { 
		totalAmount += (int)(totalAmount*TVA_PERCENT/100);
		return discount.calculatePrice(totalAmount, discountValue);
	}
}

//second decorator class
class TipsDecorator implements Invoice {

	private static final int TIPS_PERCENT = 10;
	private Invoice discount;
	
	public TipsDecorator(Invoice discount) {
		this.discount = discount;
	}

	@Override
	public int calculatePrice(int totalAmount, int discountValue) { 
		int amountAfterDiscount = discount.calculatePrice(totalAmount, discountValue);
		return amountAfterDiscount + (int)(amountAfterDiscount*TIPS_PERCENT/100);
	}
}
