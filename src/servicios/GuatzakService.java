package servicios;

import dataBase.DAOdata;
import ultil.Conector;

public class GuatzakService {

	private final DAOdata _data = new DAOdata();
	
	public void enviarMensaje(Conector co,String user,String mensaje) {
		try {
			int id = _data.getIdUser(co, user);
			ind sala = _data.getIdSala();
		}finally {
			
		}
		
		
	}
	
	
}
