package facadeExceptions;

public class NoCouponsException extends Exception {

	public NoCouponsException() {
		super("Company was not found!");

	}
}
