package servicios;

import java.security.MessageDigest;
import java.security.SecureRandom;

import dataBase.LoginData;
import ultil.Conector;

public class LoginService {

	private final LoginData login = new LoginData();
	
	public void insertar(Conector co,String nombre, String contrase�a) {
		try {
			
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[32];
			random.nextBytes(salt);
		
			String saltedPassword = contrase�a+salt.toString();
			
			login.insert(co, nombre, saltedPassword, salt.toString());
		
		};
	
		
	}
	
}
