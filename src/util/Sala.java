package util;

public class Sala {

	private int _id;
	private int _tipo;
	private String _nombre;
	
	public Sala(int id, int tipo, String nombre) {
		set_id(id);
		set_tipo(tipo);
		set_nombre(nombre);
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_tipo() {
		return _tipo;
	}

	public void set_tipo(int _tipo) {
		this._tipo = _tipo;
	}

	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}
	
	
	
}
