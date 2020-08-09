package dataBase;

import java.sql.*;

import ultil.Conector;

public class LoginData {
	//inspirado en el video 52
	public void insert(Conector co, String nick, String password, String salt) {
		
		try(Connection con = co.getConector()){
			
			if(nick!=null && password!=null && salt !=null) {
				try(PreparedStatement pstm = con.prepareStatement("INSERT INTO Usuario(Nombre_user,Contraseña_user,Salt_user) values (?,?,?)", new String[] {"Id_user"})){
					pstm.setString(1, nick);
					pstm.setString(2, password);
					pstm.setString(3, salt);
					pstm.executeQuery();
				}
			}
			
		}catch (SQLException e) {
			throw new Error("Error al iniciar el servidor");
		}
		
	}
	
}
