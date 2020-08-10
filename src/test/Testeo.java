package test;

import java.sql.Connection;
import java.sql.SQLException;

import dataBase.GuatzakDB;
import dataBase.LoginData;
import ultil.Conector;

public class Testeo {

	public static void main(String[] args) throws SQLException {
		
		LoginData data = new LoginData();
		
		System.out.println(data.selectUser(new Conector(), "William"));
		
	}
	
}
