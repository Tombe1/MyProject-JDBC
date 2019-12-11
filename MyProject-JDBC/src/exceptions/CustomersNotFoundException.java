package exceptions;

public class CustomersNotFoundException extends Exception{
	
	public CustomersNotFoundException() {
		super("Customer was not found!");
	}

}
