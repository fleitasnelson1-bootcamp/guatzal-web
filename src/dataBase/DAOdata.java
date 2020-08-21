package dataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.Conector;
import util.Mensaje;
import util.Sala;
import util.User;

public class DAOdata {
	/**
	 * Envia el mensaje a la base de datos para ser guardado
	 * @param co 
	 * @param id
	 * @param sala
	 * @param mensaje
	 * @throws SQLException
	 */
	public void enviar(Conector co,int id,int sala, String mensaje) throws SQLException{
		try(Connection con = co.getConector()){
			
			if(mensaje!=null) {
				try(PreparedStatement pstmt = con.prepareStatement("INSERT INTO Mensaje(Id_user, Id_sala, Mensaje, Fecha) values (?,?,?,getDate())", new String[] {"Id_men"})){
					pstmt.setInt(1, id);
					pstmt.setInt(2, sala);
					pstmt.setString(3, mensaje);
					pstmt.executeUpdate();
					co.commit();
				}
			}
			
		}catch (SQLException e) {
			throw new Error(e.getMessage());
		}
	}
	
	/**
	 * Devuelve el ID del usuario recibido
	 * @param co
	 * @param user
	 * @return int
	 */
	public int getIdUser(Conector co,String user) {
		try(Connection con = co.getConector()){
			
			if(user!=null) {
				try(PreparedStatement pstmt = con.prepareStatement("Select Id_user from Usuario where Nombre_user = ?")){
					pstmt.setString(1, user);
					pstmt.executeQuery();
					try (ResultSet rs = pstmt.executeQuery()) {
						if (rs.next()) {
							return rs.getInt(1);
						}
					}
				}
			}
			
		}catch (SQLException e) {
			throw new Error("Error al iniciar el servidor");
		}
		return 0;
	}
	
	/**
	 * Devuelve una lista con las salas perteneciente al id del usuario
	 * @param co
	 * @param id
	 * @return ArrayList<Sala>
	 * @throws Exception 
	 */
	public ArrayList<Sala> getSalas(Conector co,int id) throws Exception{
		ArrayList<Sala> salas = new ArrayList<>();
		try(Connection con = co.getConector()){
			if(id > 0) {
				try(PreparedStatement pstmt = con.prepareStatement("exec get_salas ?")){
					pstmt.setInt(1, id);
					try (ResultSet rs = pstmt.executeQuery()) {
						while (rs.next()) {
							salas.add(new Sala(rs.getInt(1),rs.getInt(2),rs.getString(3)));
						}
					}
				}
			}
			
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return salas;
	}
	
	/**
	 * Devuelve una lista con todos los mensajes pertenecientes a la id de la sala
	 * @param co
	 * @param sala
	 * @return ArrayLits<Mensaje>
	 * @throws Exception 
	 */
	public ArrayList<Mensaje> getMensajes(Conector co, int sala) throws Exception{
		ArrayList<Mensaje> mensajes = new ArrayList<>();
		try(Connection con = co.getConector()){
			if(sala > 0) {
				try(PreparedStatement pstmt = con.prepareStatement("exec get_mensajes ?")){
					pstmt.setInt(1, sala);
					try (ResultSet rs = pstmt.executeQuery()) {
						while (rs.next()) {
							mensajes.add(new Mensaje(rs.getString(1),rs.getDate(2),rs.getTime(2),rs.getString(3)));
						}
					}
				}
			}
			
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return mensajes;
	}
	
	/**
	 * Crea una sala nueva en la base de datos y devuelve la ip de la sala
	 * @param co
	 * @param tipo
	 * @param nombre
	 * @return
	 */
	public int agregarSala(Conector co,int tipo, String nombre) {
		
		try(Connection con = co.getConector()){
			
			if(nombre!=null && tipo != 0 ) {
				try(PreparedStatement pstmt = con.prepareStatement("INSERT INTO Sala(Id_tipo,Nombre_sala) values (?,?)", Statement.RETURN_GENERATED_KEYS)){
					pstmt.setInt(1, tipo);
					pstmt.setString(2, nombre);
					pstmt.executeUpdate();
					co.commit();
					try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
			            if (generatedKeys.next()) {
			                return generatedKeys.getInt(1);
			            }
			            else {
			                throw new SQLException("Error al consegir la id generada");
			            }
			        }
				}
			}
			
		}catch (SQLException e) {
			throw new Error("Error al iniciar el servidor");
		}
		return 0;
	}
	
	/**
	 * Devuelve una lista de todos los Usuarios en Guatzak
	 * @param co
	 * @param id_user
	 * @return ArrayList<User>
	 */
	public ArrayList<User> getContactos(Conector co,int id_user){
		ArrayList<User> contactos = new ArrayList<>();
		try(Connection con = co.getConector()){
			
			if(id_user>0) {
				try(PreparedStatement pstmt = con.prepareStatement("Select Id_user, Nombre_user from Usuario where NOT Id_user = ?")){
					pstmt.setInt(1, id_user);
					pstmt.executeQuery();
					try (ResultSet rs = pstmt.executeQuery()) {
						if (rs.next()) {
							contactos.add(new User(rs.getInt(1),rs.getString(2)));
						}
					}
				}
			}
			
		}catch (SQLException e) {
			throw new Error("Error al iniciar el servidor");
		}
		
		return contactos;
	}
	
	/**
	 * Agrega a la sala los usuarios recibidos 
	 * @param co
	 * @param id_Sala
	 * @param miembros
	 */
	public void agregarASala(Conector co,int id_Sala,ArrayList<User> miembros) {
		
		try(Connection con = co.getConector()){
			
			if(miembros.size()>0) {
				for(User u:miembros) {
					try(PreparedStatement pstmt = con.prepareStatement("INSERT INTO Sala_Usuario(Id_sala,Id_usuario) values (?,?)")){
						pstmt.setInt(1, id_Sala);
						pstmt.setInt(2, u.get_id());
						pstmt.executeUpdate();
						co.commit();
					}
				}
			}
			
		}catch (SQLException e) {
			throw new Error("Error al iniciar el servidor");
		}
		
	}
	
}
