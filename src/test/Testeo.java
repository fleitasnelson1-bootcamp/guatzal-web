package test;

import java.sql.Connection;
import java.sql.SQLException;

import dataBase.GuatzakDB;
import ultil.Conector;

public class Testeo {

	public static void main(String[] args) throws SQLException {
		
		Conector co = Conector.newInstance();
		
		try(Connection con = co.getConector()){
			System.out.println("Funciona");
		}
		
	}
	
}
