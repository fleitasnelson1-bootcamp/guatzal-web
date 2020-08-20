package util;

import java.sql.*;
import dataBase.GuatzakDB;

//Inspirado por el video 52 del profesor Amin
public class Conector implements AutoCloseable{
	
	private Connection _connection;
	
	/**
	 * Returna la conexion con la base de datos
	 * @return Connection
	 * @throws SQLException
	 */
	public Connection getConector()  throws SQLException{
		
		try {
			if(null == _connection) {
				_connection = GuatzakDB.conexion(false);
			}
		}catch (SQLException e) {
			throw new Error("Error al iniciar el servidor");
		}
		return _connection;
		
	}
	
	/**
	 * Devuelve un nuevo Conector
	 * @return Conector
	 */
	public static Conector newInstance() {
		return new Conector();
	}
	
	/**
	 * Cierra a conexion con el servidor
	 */
	public void close() throws Exception {
		try {
			if(null!=_connection) {
				_connection.close();
			}
		} catch (SQLException e) {
			throw new Error("Error al cerrar el servidor");
		}
	}
	
	/**
	 * Realiza el commit de la conexion
	 * @throws SQLException
	 */
	public void commit()  throws SQLException{
		try {
			_connection.commit();
		} catch (SQLException e) {
			throw new Error("Error al commit");
		}
	}
}
