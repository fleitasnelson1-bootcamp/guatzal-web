package util;

import java.sql.*;

public class Mensaje {

	private String _usuario;
	private Date _fecha;
	private Time _tiempo;
	private String _mensaje;
	
	public Mensaje(String usuario, Date fecha,Time tiempo, String mensaje) {
		set_usuario(usuario);
		set_fecha(fecha);
		set_mensaje(mensaje);
		set_tiempo(tiempo);
	}

	public void set_tiempo(Time tiempo) {
		_tiempo = tiempo;
	}
	
	public Time get_tiempo() {
		return _tiempo;
	}

	public String get_usuario() {
		return _usuario;
	}

	public void set_usuario(String _usuario) {
		this._usuario = _usuario;
	}

	public Date get_fecha() {
		return _fecha;
	}

	public void set_fecha(Date _fecha) {
		this._fecha = _fecha;
	}

	public String get_mensaje() {
		return _mensaje;
	}

	public void set_mensaje(String _mensaje) {
		this._mensaje = _mensaje;
	}
	
	
}
