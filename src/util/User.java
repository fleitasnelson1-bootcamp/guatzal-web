package util;

public class User {

	private int _id;
	private String _nombre;
	
	public User(int id, String nombre) {
		set_id(id);
		set_nombre(nombre);
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}
	
	
	
}
