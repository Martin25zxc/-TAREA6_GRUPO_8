package negocioImpl;

import java.io.IOException;

public class PersonaException extends IOException {
	private static final long serialVersionUID = -2892419532443123476L;
	
	public PersonaException()
	{
		super();
	}
	
	public PersonaException(String message)
	{
		super(message);
	}
}