package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import dataBase.GuatzakDB;
import dataBase.LoginData;
import servicios.GuatzakService;
import ultil.Conector;

public class Testeo {

	public static void main(String[] args) throws SQLException {
		
		GuatzakService gs = new GuatzakService();
		Conector co = Conector.newInstance();

		
		gs.crearSala(co, 1, "Testeando");
		
	}
	
}
