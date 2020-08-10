package ultil;

import java.sql.*;
import dataBase.GuatzakDB;

//Inspirado por el video 52 del profesor Amin
public class Conector implements AutoCloseable{
	
	private Connection _connection;
	
	public Connection getConector() {
		
		try {
			if(null == _connection) {
				_connection = GuatzakDB.conexion(false);
			}
		}catch (SQLException e) {
			throw new Error("Error al iniciar el servidor");
		}
		return _connection;
		
	}
	
	public static Conector newInstance() {
		return new Conector();
	}

	public void close() throws Exception {
		try {
			if(null!=_connection) {
				_connection.close();
			}
		} catch (SQLException e) {
			throw new Error("Error al cerrar el servidor");
		}
	}
	
	public void commit() {
		try {
			_connection.commit();;
		} catch (SQLException e) {
			throw new Error("Error al commit");
		}
	}
}
