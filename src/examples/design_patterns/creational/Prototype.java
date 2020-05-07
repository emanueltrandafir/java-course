package examples.design_patterns.creational;

import java.util.HashMap;
import java.util.Map;


public class Prototype {
	public static void main(String[] args) {
		new Example().runExample();
	}
}


class Example {
	
	public void runExample() {
		
		DiscountManager discounts = new DiscountManager();

		// different objects should be printed
		System.out.println( discounts.getDiscount(DiscountManager.DiscountTypes.FIXED) );
		System.out.println( discounts.getDiscount(DiscountManager.DiscountTypes.FIXED) );
		System.out.println( discounts.getDiscount(DiscountManager.DiscountTypes.PERCENTAGE) );
		System.out.println( discounts.getDiscount(DiscountManager.DiscountTypes.PERCENTAGE) ); 
		
	}
}



abstract class DiscountInvoice implements Cloneable {
	
	abstract int calculateAmountToPay(int totalAmount, int discountValue);
	
	public DiscountInvoice clone() {
		try {
			return (DiscountInvoice) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		} 
	}
}

//a possible discount concrete implementations
class PercentageDiscount extends DiscountInvoice {
	@Override
	int calculateAmountToPay(int totalAmount, int discountValue) {
		return totalAmount - (int)(totalAmount*discountValue/100);
	}
}
//other discount concrete implementations
class FixedDiscount extends DiscountInvoice {
	@Override
	int calculateAmountToPay(int totalAmount, int discountValue) {
		return totalAmount - discountValue;
	}
}

//the class that will generate the clones of the needed discounts
class DiscountManager { 
	public static enum DiscountTypes { FIXED, PERCENTAGE };
    private static Map<DiscountTypes, DiscountInvoice> discounts = new HashMap<>();  
       
    public DiscountManager() { 
    	discounts.put(DiscountTypes.FIXED, new FixedDiscount()); 
        discounts.put(DiscountTypes.PERCENTAGE, new PercentageDiscount()); 
    } 
       
    public static DiscountInvoice getDiscount(DiscountTypes type){ 
        return discounts.get(type).clone(); 
    } 
} 



