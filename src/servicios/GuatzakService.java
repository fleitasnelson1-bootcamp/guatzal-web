package servicios;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

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
	
	public JsonArray getSalasJson(Conector co, int id) throws SQLException {
		try {
			ArrayList<Sala> salas = _data.getSalas(co, id);
			JsonArray salasJson = Json.createArrayBuilder().build();
			salas.forEach(sala -> {
				//Creo un json que quiero enviar. 
				JsonObject salaJson = Json.createObjectBuilder()
						.add("chat_id", sala.get_id())
						.add("chat_name", sala.get_nombre())
						.build();
				//Cargo cada json creado en el array.
				salasJson.add(salaJson);
			});
			return salasJson;
		}
		catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
}
