package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PersonaDao;
import entidades.Persona;

public class PersonaDaoImpl implements PersonaDao
{
	private static final String insert = "INSERT INTO Personas(Dni, Nombre, Apellido) VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM Personas WHERE Dni = ?";
	private static final String update = "update Personas set Dni = ?, Nombre = ?, Apellido = ? where Dni = ?";
	private static final String readall = "SELECT * FROM Personas";
	private static final String get = "SELECT * FROM Personas where Dni = ?";
	
	public boolean post(Persona persona)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isSuccess = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, persona.getDni());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getApellido());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isSuccess = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isSuccess;
	}
	
	public boolean update(Persona persona)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isSuccess = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, persona.getDni());
			statement.setString(4, persona.getDni());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getApellido());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isSuccess = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isSuccess;
	}
	
	public boolean delete(String dni)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isSuccess = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, dni);
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isSuccess = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	public List<Persona> get()
	{
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Persona> personas = new ArrayList<Persona>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(mapResultpersona(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	
	public Persona get(String dni) {
		PreparedStatement statement;
		ResultSet resultSet; 
		Persona persona = null;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(get);
			statement.setString(1, dni);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				persona = mapResultpersona(resultSet);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return persona;
	}
	
	private Persona mapResultpersona(ResultSet resultSet) throws SQLException
	{
		String id = resultSet.getString("Dni");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("Apellido");
		return new Persona(id, nombre, apellido);
	}
}