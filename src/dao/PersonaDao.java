package dao;

import java.util.List;

import entidades.Persona;

public interface PersonaDao {
	public boolean post (Persona persona);
	public boolean update (Persona persona);
	public boolean delete (Persona persona);
	public Persona get(String dni);
	public List<Persona> get();
	
}