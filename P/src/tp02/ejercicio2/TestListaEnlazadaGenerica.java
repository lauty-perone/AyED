package tp02.ejercicio2;

public class TestListaEnlazadaGenerica {
     
	public static void main(String []args) {
		Estudiante e1 = new Estudiante("Lautaro","Perone", "lautyperone@gmail.com", 3,"117 156");
		Estudiante e2 = new Estudiante("Jorge","Guzman", "guzmin@gmail.com", 2,"150 170");
		Estudiante e3 = new Estudiante("Raul","Perez", "rauli@gmail.com", 1,"110 180");
		Estudiante e4 = new Estudiante("Francisco","Raul", "franraul@gmail.com", 2,"90 190");
		ListaEnlazadaGenerica<Estudiante> lista = new ListaEnlazadaGenerica<Estudiante>();
		lista.agregarFinal(e1);
		lista.agregarFinal(e2);
		lista.agregarFinal(e3);
		lista.agregarFinal(e4);
		System.out.println("A continuacion se mostraran los datos de las personas ingresadas");

        for (int i=1;i<= lista.tamanio(); i++)
        	System.out.println(lista.elemento(i).tusDatos());
		}
		
}
