package servicios;

import java.sql.SQLException;
import java.util.ArrayList;

import dataBase.DAOdata;
import ultil.Conector;
import ultil.Sala;

public class GuatzakService {

	private final DAOdata _data = new DAOdata();
	
	public void enviarMensaje(Conector co, int id, int id_sala,String mensaje) throws SQLException {
		try {
			_data.enviar(co, id, id_sala, mensaje, new java.sql.Date(8));
		}catch(SQLException e) {
			throw new Error(e.getMessage());
		}
		
		
	}
	
	public int getId(Conector co, String user) {
		return _data.getIdUser(co, user);
	}
	
	public ArrayList<Sala> getSalas(Conector co, int id){
		
		return _data.getSalas(co, id);
		
	}
	
	
}
