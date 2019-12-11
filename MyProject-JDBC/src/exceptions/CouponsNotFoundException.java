package exceptions;

public class CouponsNotFoundException extends Exception {
	
	public CouponsNotFoundException() {
		super("Coupon was not found!");
	}

}
