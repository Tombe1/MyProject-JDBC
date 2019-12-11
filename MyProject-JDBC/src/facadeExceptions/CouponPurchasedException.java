package facadeExceptions;

public class CouponPurchasedException extends Exception {

	public CouponPurchasedException() {
		super("Coupon was not found!");
	}

}
