package negocioImpl;

import java.util.List;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import entidades.Persona;
import negocio.PersonaNegocio;

public class PersonaNegocioImpl implements PersonaNegocio{
	
	PersonaDao personasDao = new PersonaDaoImpl();

	public Persona get(String dni) {
		return personasDao.get(dni);
	}

	public List<Persona> get() {
		return personasDao.get();
	}

	public void post(Persona persona) throws PersonaException {
		String validaciones = "";
		if(persona == null)
			validaciones += "El registro de personas se encuentra vacio.";
		else {
			if(persona.getApellido().isEmpty() )
				validaciones += "Se debe completar el campo apellido.";
			if(persona.getNombre().isEmpty() )
				validaciones += "Se debe completar el campo nombre.";
			if(persona.getDni().isEmpty() ) {
				validaciones += "Se debe completar el campo dni.";
			}
			else if (personasDao.get(persona.dni) != null){
					validaciones += "Ya se encuentra una persona registrada con ese dni.";
			}
		}
		
		if(!validaciones.isEmpty())
			throw new PersonaException(validaciones);
		
		boolean isSuccess = personasDao.post(persona);
		
		if(!isSuccess)
			throw new PersonaException("No se pudo guardar el registro de Persona.");
	}

	public void update(Persona persona) throws PersonaException {
		String validaciones = "";
		if(persona == null)
			validaciones += "El registro de personas se encuentra vacio.";
		else {
			if(persona.getApellido().isEmpty() )
				validaciones += "Se debe completar el campo apellido.";
			if(persona.getNombre().isEmpty() )
				validaciones += "Se debe completar el campo nombre.";
		}
		
		if(!validaciones.isEmpty())
			throw new PersonaException(validaciones);
		
		boolean isSuccess = personasDao.update(persona);
		
		if(!isSuccess)
			throw new PersonaException("No se pudo guardar el registro de Persona.");
		
	}

	public void delete(String dni) throws PersonaException {
		String validaciones = "";
		if (personasDao.get(dni) == null)
			validaciones += "No se encontro el registro de persona que se desea eliminar.";
		
		if(!validaciones.isEmpty())
			throw new PersonaException(validaciones);
		
		boolean isSuccess = personasDao.delete(dni);
		
		if(!isSuccess)
			throw new PersonaException("No se pudo guardar el registro de Persona.");
	}

}
