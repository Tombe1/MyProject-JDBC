package facadeExceptions;

public class ExpiredCouponException extends Exception {

	public ExpiredCouponException() {
		super("Coupon expired!");
	}
}
