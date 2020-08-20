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
	
	public ArrayList<Sala> getSalas(Conector co, int id) throws Exception{
		
		return _data.getSalas(co, id);
		
	}
	
	public ArrayList<Mensaje> getMensajes(Conector co, int idSala) throws Exception{
		
		return _data.getMensajes(co, idSala);
		
	}
	
	public void crearSala(Conector co,int tipo, String nombre, ArrayList<User> miembros) {
		int id_Sala=_data.agregarSala(co, tipo, nombre);
		_data.agregarASala(co, id_Sala, miembros);
	}
	
	
	public ArrayList<User> getContactos(Conector co,int id_user){
		return _data.getContactos(co, id_user);
	}
	
	/*
	 * Estos metodos en vez de retornar listas retornan JsonArray.
	 * https://javaee.github.io/javaee-spec/javadocs/javax/json/Json.html#createArrayBuilder-java.util.Collection- 
	 */
	
	public JsonArray getSalasJson(Conector co, int id) throws Exception {
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
			throw new Exception(e.getMessage());
		}
	}
	
	public JsonArray getMensajesJson(Conector co, int idSala) throws Exception {
		try {
			//Para crear un array de objetos json.
			JsonArrayBuilder builder = Json.createArrayBuilder();
			//Creo una lista de objetos json. Luego recorro y agrego cada uno al builder.
			_data.getMensajes(co, idSala).stream()
					.map((msg) -> Json.createObjectBuilder()
							.add("date", msg.get_fecha().toString())
							.add("message", msg.get_mensaje())
							.add("username", msg.get_usuario())
							.build())
					.collect(Collectors.toList())
					.forEach((msg) -> builder.add(msg));
			
			return builder.build();
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public JsonArray getContactosJson(Conector co, int idUser) throws Exception {
		try {
			//Para crear un array de objetos json.
			JsonArrayBuilder builder = Json.createArrayBuilder();
			//Creo una lista de objetos json. Luego recorro y agrego cada uno al builder.
			_data.getContactos(co, idUser).stream()
					.map((contacto) -> Json.createObjectBuilder()
							.add("id", contacto.get_id())
							.add("name", contacto.get_nombre())
							.build())
					.collect(Collectors.toList())
					.forEach((contacto) -> builder.add(contacto));
			
			return builder.build();
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
