package dao;

import java.util.List;

import entidades.Persona;

public interface PersonaDao {
	public boolean post (Persona usuario);
	public boolean update (Persona usuario);
	public boolean delete (Persona usuario);
	public List<Persona> get();
	
}