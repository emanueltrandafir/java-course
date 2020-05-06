package examples.design_patterns.creational;

import java.util.Currency;

import javax.swing.text.DefaultEditorKit.DefaultKeyTypedAction;

import com.google.gson.Gson;
 



public class SimpleFactory {
	
	public static void main(String[] args) {
		new Example().runExample();
	}
	
}



class Example {
	
	public void runExample() {

		DiscountFactory factory = new DiscountFactory();
		
		Discount discount1 = factory.createDiscount(DiscountFactory.DiscountType.FIXED);
		Discount discount2 = factory.createDiscount(DiscountFactory.DiscountType.PERCENTAGE);
		
		Gson gson = new Gson();
		System.out.println( gson.toJson(discount1) );
		System.out.println( gson.toJson(discount2) );

	}
}


// the factory class
class DiscountFactory {
	
	public static enum DiscountType {
		FIXED, PERCENTAGE
	}
	
	public Discount createDiscount(DiscountType type){
		switch(type){
			case PERCENTAGE: return new PercentageDiscount();
			case FIXED: return new FixedDiscount();
			default : throw new IllegalArgumentException();
		}
	}
}


//the abstract base class
abstract class Discount {

	protected int totalAmount;
	protected Currency currency;
	
	abstract int calculateDiscount( int value );
	
}

//the concrete implementations
class PercentageDiscount extends Discount {
	@Override
	int calculateDiscount( int percentageValue ) {
		return (int) (totalAmount * percentageValue / 100); 
	}
}
class FixedDiscount extends Discount {
	@Override
	int calculateDiscount(int fixedDiscountValue) {
		return fixedDiscountValue;
	}
}
