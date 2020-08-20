package test;

import java.sql.SQLException;

import dataBase.LoginData;
import servicios.GuatzakService;
import servicios.LoginService;
import util.Conector;
import util.Mensaje;

public class Testeo {

	public static void main(String[] args) throws SQLException {
		
		LoginService ls = new LoginService();
		Conector co = Conector.newInstance();
		
		ls.insertar(co, "Pepe", "1234");
		ls.insertar(co, "Pepa", "1234");
		ls.insertar(co, "Pepo", "1234");
		
	}
	
}
