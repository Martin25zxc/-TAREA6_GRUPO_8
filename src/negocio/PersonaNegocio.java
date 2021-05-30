package negocio;
import java.util.List;

import entidades.Persona;

public interface PersonaNegocio {
	public void post (Persona persona) throws Exception;
	public void update (Persona persona) throws Exception;
	public void delete(String dni) throws Exception;
	public Persona get(String dni);
	public List<Persona> get();
}
