package facadeExceptions;

public class LoginFailedException extends Exception {
	
	public LoginFailedException() {
		super("Cant login!");
	}

}
