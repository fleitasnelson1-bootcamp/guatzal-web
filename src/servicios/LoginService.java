package servicios;

import org.mindrot.jbcrypt.BCrypt;

import dataBase.LoginData;
import util.Conector;

public class LoginService {

	private final LoginData _login = new LoginData();
	
	/**
	 * Encripta la contrase�a recibida y ejecuta el comando para agregar el nuevo usuario a la base de datos
	 * @param co
	 * @param nombre
	 * @param contrase�a
	 */
	public void insertar(Conector co,String nombre, String contrase�a) {
		try {
			
			String salt = BCrypt.gensalt();
		
			String saltedPassword = BCrypt.hashpw(contrase�a,salt);
			_login.insert(co, nombre, saltedPassword, salt);
		
		}finally{
			
		};
	
		
	}
	
	/**
	 * Verifica que el usuario y la contrase�a sean los correctos
	 * @param co
	 * @param nombre
	 * @param contrase�a
	 * @return
	 */
	public boolean verify(Conector co, String nombre, String contrase�a) {
		String[] pass = _login.selectUser(co, nombre);
		
		if(pass[0].equals(BCrypt.hashpw(contrase�a,pass[1]))) {
			return true;
		}
		
		return false;
	}
	
}
