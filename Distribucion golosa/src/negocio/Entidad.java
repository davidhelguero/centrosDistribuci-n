package negocio;

import java.io.Serializable;

public class Entidad implements Serializable{
	private String nombre;
	private double latitud;
	private double longitud;
	private static final long serialVersionUID = 1L;
	
	public Entidad(String nombre, double latitud, double longitud) {
		setNombre(nombre);
		this.latitud = latitud;
		setLongitud(longitud);
	}
	
	private void setNombre(String nombre) {
		if(!nombre.isBlank())
			this.nombre = nombre;
		else
			throw new IllegalArgumentException("El nombre no puede estar vacío");
	}

	private void setLongitud(double longitud) {
		if(longitud >= 0)
			this.longitud = longitud;
		else
			throw new IllegalArgumentException("La longitud no puede ser negativa");
	}

	public String getNombre() {
		return nombre;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}
	
	
}
