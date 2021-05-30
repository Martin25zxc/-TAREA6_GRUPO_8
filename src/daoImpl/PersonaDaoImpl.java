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
		
	public boolean post(Persona usuario)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isSuccess = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, usuario.getDni());
			statement.setString(2, usuario.getNombre());
			statement.setString(3, usuario.getApellido());
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
	
	public boolean update(Persona usuario)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isSuccess = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, usuario.getDni());
			statement.setString(4, usuario.getDni());
			statement.setString(2, usuario.getNombre());
			statement.setString(3, usuario.getApellido());
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
	
	public boolean delete(Persona usuario)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isSuccess = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, usuario.getDni());
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
		ArrayList<Persona> usuarios = new ArrayList<Persona>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				usuarios.add(mapResultUsuario(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return usuarios;
	}
	
	private Persona mapResultUsuario(ResultSet resultSet) throws SQLException
	{
		String id = resultSet.getString("idusuario");
		String nombre = resultSet.getString("Nombre");
		String tel = resultSet.getString("Telefono");
		return new Persona(id, nombre, tel);
	}
}