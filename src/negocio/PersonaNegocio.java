package negocio;
import java.util.List;

import entidades.Persona;
import negocioImpl.PersonaException;

public interface PersonaNegocio {
	public void post (Persona persona) throws PersonaException;
	public void update (Persona persona) throws PersonaException;
	public void delete(String dni) throws PersonaException;
	public Persona get(String dni);
	public List<Persona> get();
}
