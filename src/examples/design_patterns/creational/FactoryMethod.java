package examples.design_patterns.creational;

import java.util.Currency;

import com.google.gson.Gson;

import examples.design_patterns.creational.AbstractDiscountFactory.DiscountType;


public class FactoryMethod {
	
	public static void main(String[] args) {
		new Example().runExample();
	}
	
}



class Example {
	
	public void runExample() {

		AbstractDiscountFactory euroFactory = new EuroDiscountFactory();
		AbstractDiscountFactory dollarFactory = new DollarDiscountFactory();

		Discount discount1 = euroFactory.createDiscount(DiscountType.FIXED);
		Discount discount2 = euroFactory.createDiscount(DiscountType.PERCENTAGE);
		Discount discount3 = dollarFactory.createDiscount(DiscountType.FIXED);
		Discount discount4 = dollarFactory.createDiscount(DiscountType.PERCENTAGE);
		
		Gson gson = new Gson();
		System.out.println( gson.toJson(discount1) );
		System.out.println( gson.toJson(discount2) );
		System.out.println( gson.toJson(discount3) );
		System.out.println( gson.toJson(discount4) );
	}
		
}



// abstract factory
abstract class AbstractDiscountFactory {
	
	public static enum DiscountType {
		FIXED, PERCENTAGE
	}
	
	abstract Discount createDiscount(DiscountType type);
}

//first impl of the factory
class EuroDiscountFactory extends AbstractDiscountFactory {
	@Override
	Discount createDiscount(DiscountType type) {
		switch(type){
			case PERCENTAGE: return new PercentageDiscount( "EUR" );
			case FIXED: return new FixedDiscount( "EUR" );
			default : throw new IllegalArgumentException();
		}
	}
}
//other impl of the factory
class DollarDiscountFactory extends AbstractDiscountFactory {
	@Override
	Discount createDiscount(DiscountType type) {
		switch(type){
			case PERCENTAGE: return new PercentageDiscount( "USD" );
			case FIXED: return new FixedDiscount( "USD" );
			default : throw new IllegalArgumentException();
		}
	}
}


// the abstract base class Discount
abstract class Discount {

	protected int totalAmount;
	protected String currency;
	
	public Discount (String currency) {
		this.currency = currency;
	}
	
	abstract int calculateDiscount( int value );
	
}

// the concrete implementations of Discount
class PercentageDiscount extends Discount {
	public PercentageDiscount(String currency) {
		super(currency); 
	}

	@Override
	int calculateDiscount( int percentageValue ) {
		return (int) (totalAmount * percentageValue / 100); 
	}
}
class FixedDiscount extends Discount {
	public FixedDiscount(String currency) {
		super(currency); 
	}
	
	@Override
	int calculateDiscount(int fixedDiscountValue) {
		return fixedDiscountValue;
	}
}

