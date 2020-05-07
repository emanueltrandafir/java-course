package examples.design_patterns.structural;

public class Facade {
	public static void main(String[] args) {
		new Example().runExample();
	}
}


class Example {
	
	public void runExample() {
		
		DiscountCalculator discountCalculator = new DiscountCalculator();
		
		int discount1 = discountCalculator.calculateFixedDiscount(200, 25);
		int discount2 = discountCalculator.calculatePercentageDiscount(200, 25);
		
		System.out.println( discount1 );
		System.out.println( discount2 );
		
	}
}


//the facade class
class DiscountCalculator {
	private PercentageDiscount percentageDiscount;
	private FixedDiscount fixedDiscount;

	public DiscountCalculator() {
		percentageDiscount = new PercentageDiscount();
		fixedDiscount = new FixedDiscount();
	}

	public int calculateFixedDiscount(int total, int discount) {
		return fixedDiscount.calculatePriceAfterDiscount(total, discount);
	}
	public int calculatePercentageDiscount(int total, int discount) {
		return percentageDiscount.calculatePriceAfterDiscount(total, discount);
	}
}


//the interface
interface Discount {
	int calculatePriceAfterDiscount(int totalAmount ,  int discountValue );
}

//the concrete implementations
class PercentageDiscount implements Discount {
	@Override
	public int calculatePriceAfterDiscount( int totalAmount , int percentageValue ) {
		return totalAmount - (int)(totalAmount * percentageValue / 100); 
	}
}
class FixedDiscount implements Discount {
	@Override
	public int calculatePriceAfterDiscount(int totalAmount , int fixedDiscountValue) {
		return totalAmount - fixedDiscountValue;
	}
}