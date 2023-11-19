package Parcial;

public class Estadio {
	
	private String nombreEstadio;
	private String nombreCiudad;
	
	public Estadio(String nombreEstadio, String nombreCiudad) {
		this.nombreEstadio = nombreEstadio;
		this.nombreCiudad = nombreCiudad;
	}

	public String getNombreEstadio() {
		return nombreEstadio;
	}

	public void setNombreEstadio(String nombreEstadio) {
		this.nombreEstadio = nombreEstadio;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}
	
	
	
	
}
