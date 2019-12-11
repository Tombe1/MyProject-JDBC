package exceptions;

public class CompaniesNotFoundException extends Exception {
	
	public CompaniesNotFoundException() {
		super("Company was not found!");
	}

}
