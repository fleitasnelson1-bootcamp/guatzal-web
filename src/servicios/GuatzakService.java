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
	
	/**
	 * Ejecuta el comando para enviar mensaje
	 * @param co
	 * @param id
	 * @param id_sala
	 * @param mensaje
	 * @throws SQLException
	 */
	public void enviarMensaje(Conector co, int id, int id_sala,String mensaje) throws SQLException {
		try {
			_data.enviar(co, id, id_sala, mensaje);
		}catch(SQLException e) {
			throw new Error(e.getMessage());
		}
		
	}
	
	/**
	 * Devuelve el Id del nick del user
	 * @param co
	 * @param user
	 * @return int
	 */
	public int getId(Conector co, String user) {
		return _data.getIdUser(co, user);
	}
	
	/**
	 * Devuelve las Salas en la cual el usuario con la id Participa
	 * @param co
	 * @param id
	 * @return Sala
	 */
	public ArrayList<Sala> getSalas(Conector co, int id){
		return _data.getSalas(co, id);
	}
	
	/**
	 * Devuelve todos los mensajes dentro de la sala
	 * @param co
	 * @param idSala
	 * @return Mensaje
	 */
	public ArrayList<Mensaje> getMensajes(Conector co, int idSala){
		
		return _data.getMensajes(co, idSala);
		
	}
	
	/**
	 * Crea una sala nueva en la base de datos y agrega a los miembros a ella
	 * @param co
	 * @param tipo
	 * @param nombre
	 * @param miembros
	 */
	public void crearSala(Conector co,int tipo, String nombre, ArrayList<User> miembros) {
		int id_Sala=_data.agregarSala(co, tipo, nombre);
		_data.agregarASala(co, id_Sala, miembros);
	}
	
	/**
	 * Devuelve todos los contactos del usuario
	 * @param co
	 * @param id_user
	 * @return User
	 */
	public ArrayList<User> getContactos(Conector co,int id_user){
		return _data.getContactos(co, id_user);
	}
}
