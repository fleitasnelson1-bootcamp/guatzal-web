package dataBase;

import java.sql.*;

public class GuatzakDB {
	
	public static Connection conexion(boolean autoCommit) throws SQLException {
			//Prueba de connexion
            // esta es la configuracion de la conexion a SQL Server
            final String url = "jdbc:sqlserver://localhost:1433;databaseName=Guatzak";

            // Crea una coneccion
            final Connection con = DriverManager.getConnection(url,"sa","123");
            con.setAutoCommit(autoCommit);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            return con;
    }
}
