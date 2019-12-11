package facadeExceptions;

public class CustomerCantBeUpdatedException extends Exception {
	
	public CustomerCantBeUpdatedException() {
		super("You cant update this Customer!");
	}


}
