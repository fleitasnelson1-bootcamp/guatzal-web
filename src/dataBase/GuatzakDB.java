package dataBase;

import java.sql.*;

public class GuatzakDB {
	
	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new Error("No se encontro el JAR");
		} 
	}
	
	public static Connection conexion(boolean autoCommit) throws SQLException {
			//Prueba de connexion
            // esta es la configuracion de la conexion a SQL Server
            final String url = "jdbc:sqlserver://DESKTOP-KGE6TMK:1433;databaseName=Guatzak";
            
            // Crea una coneccion
            final Connection con = DriverManager.getConnection(url,"nelson","123");
            
            con.setAutoCommit(autoCommit);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            return con;
    }
}
