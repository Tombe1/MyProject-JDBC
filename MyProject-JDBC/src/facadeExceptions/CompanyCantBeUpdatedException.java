package facadeExceptions;

public class CompanyCantBeUpdatedException extends Exception {
	
	public CompanyCantBeUpdatedException() {
		super("You cant update this Company!");
	}


}
