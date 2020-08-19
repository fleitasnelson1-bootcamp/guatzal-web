package servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
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
			_data.enviar(co, id, id_sala, mensaje);
		}catch(SQLException e) {
			throw new Error(e.getMessage());
		}
		
	}
	
	public int getId(Conector co, String user) {
		return _data.getIdUser(co, user);
	}
	
	public ArrayList<Sala> getSalas(Conector co, int id) throws SQLException{
		
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
			//Para crear un array de objetos json.
			JsonArrayBuilder builder = Json.createArrayBuilder();
			//Creo una lista de objetos json. Luego recorro y agrego cada uno al builder.
			_data.getSalas(co, id).stream()
					.map((sala) -> Json.createObjectBuilder()
							.add("id", sala.get_id())
							.add("name", sala.get_nombre())
							.build() )
					.collect(Collectors.toList())
					.forEach((sala) -> builder.add(sala));
			
			return builder.build();
		}
		catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
}
