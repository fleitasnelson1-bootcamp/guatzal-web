package dataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ultil.Conector;
import ultil.Sala;

public class DAOdata {

	public void enviar(Conector co,int id,int sala, String mensaje, Date fecha) throws SQLException{
		try(Connection con = co.getConector()){
			
			if(mensaje!=null) {
				try(PreparedStatement pstmt = con.prepareStatement("INSERT INTO Mensaje(Id_user, Id_sala, Mensaje, Fecha) values (?,?,?,?)", new String[] {"Id_user"})){
					pstmt.setInt(1, id);
					pstmt.setInt(2, sala);
					pstmt.setString(3, mensaje);
					pstmt.setDate(4, fecha);
					pstmt.executeQuery();
				}
			}
			
		}catch (SQLException e) {
			throw new Error(e.getMessage());
		}
	}
	
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
	
	public int getIdSala(Conector co,String user) {
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
	
	public ArrayList<Sala> getSalas(Conector co,int id){
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
			
		}catch (SQLException e) {
			throw new Error("Error al iniciar el servidor");
		}
		return salas;
	}
}
