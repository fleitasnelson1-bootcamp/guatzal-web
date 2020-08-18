package servicios;

import java.sql.SQLException;
import java.util.ArrayList;

import dataBase.DAOdata;
import util.Conector;
import util.Mensaje;
import util.Sala;
import util.User;

public class GuatzakService {

	private final DAOdata _data = new DAOdata();
	
	public void enviarMensaje(Conector co, int id, int id_sala,String mensaje) throws SQLException {
		try {
			_data.enviar(co, id, id_sala, mensaje, new java.sql.Date(8));
		}catch(SQLException e) {
			throw new Error(e.getMessage());
		}
		
		_data.enviar(co, id, id_sala, mensaje, new java.sql.Date(8));	
		
	}
	
	public int getId(Conector co, String user) {
		return _data.getIdUser(co, user);
	}
	
	public ArrayList<Sala> getSalas(Conector co, int id){
		
		return _data.getSalas(co, id);
		
	}
	
	public ArrayList<Mensaje> getMensajes(Conector co, int idSala){
		
		return _data.getMensajes(co, idSala);
		
	}
	
	public void crearSala(Conector co,int tipo, String nombre, ArrayList<User> miembros) {
		int id_Sala=_data.agregarSala(co, tipo, nombre);
		_data.agregarASala(co, id_Sala, miembros);
	}
	
	
	public ArrayList<User> getContactos(Conector co,int id_user){
		return _data.getContactos(co, id_user);
	}
}
