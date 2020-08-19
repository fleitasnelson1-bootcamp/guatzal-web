package test;

import java.sql.SQLException;

import servicios.GuatzakService;
import util.Conector;

public class Testeo {

	public static void main(String[] args) throws SQLException {
		
		GuatzakService gs = new GuatzakService();
		Conector co = Conector.newInstance();
		String mensaje = "test test testx2";
		
		gs.enviarMensaje(co, 1,19, mensaje);
		
	}
	
}
