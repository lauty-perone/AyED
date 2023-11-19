package tp02.ejercicio2;

public class Estudiante extends Persona{
    private int comision;
    private String direccion;
	
    
    public Estudiante  (String nombre, String apellido, String email,int comision,String direccion) {
		super(nombre, apellido, email);
		this.setComision(comision);
		this.setDireccion(direccion);
	}


	public int getComision() {
		return comision;
	}


	public void setComision(int comision) {
		this.comision = comision;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
    public String tusDatos() {
    	String aux= "Estudiante"+"\n"+super.tusDatos()+
    			"\nComision: "+this.getComision()+
    			"\nDireccion: "+this.getDireccion();
    	return aux;
    }
    
}
