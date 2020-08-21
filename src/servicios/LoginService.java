package servicios;

import org.mindrot.jbcrypt.BCrypt;

import dataBase.LoginData;
import util.Conector;

public class LoginService {

	private final LoginData _login = new LoginData();
	
	/**
	 * Encripta la contraseña recibida y ejecuta el comando para agregar el nuevo usuario a la base de datos
	 * @param co
	 * @param nombre
	 * @param contraseña
	 */
	public void insertar(Conector co,String nombre, String contraseña) {
		try {
			
			String salt = BCrypt.gensalt();
		
			String saltedPassword = BCrypt.hashpw(contraseña,salt);
			_login.insert(co, nombre, saltedPassword, salt);
		
		}finally{
			
		};
	
		
	}
	
	/**
	 * Verifica que el usuario y la contraseña sean los correctos
	 * @param co
	 * @param nombre
	 * @param contraseña
	 * @return
	 */
	public boolean verify(Conector co, String nombre, String contraseña) {
		String[] pass = _login.selectUser(co, nombre);
		
		if(pass[0].equals(BCrypt.hashpw(contraseña,pass[1]))) {
			return true;
		}
		
		return false;
	}
	
}
