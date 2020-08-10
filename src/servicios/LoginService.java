package servicios;

import java.security.SecureRandom;

import org.mindrot.jbcrypt.BCrypt;

import dataBase.LoginData;
import ultil.Conector;

public class LoginService {

	private final LoginData _login = new LoginData();
	
	public void insertar(Conector co,String nombre, String contraseña) {
		try {
			
			String salt = BCrypt.gensalt();
		
			String saltedPassword = BCrypt.hashpw(contraseña,salt);
			_login.insert(co, nombre, saltedPassword, salt);
		
		}finally{
			
		};
	
		
	}
	
}
