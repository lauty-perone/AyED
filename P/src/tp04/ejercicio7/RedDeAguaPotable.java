package tp04.ejercicio7;

import tp04.ejercicio1.ArbolGeneral;
import tp02.*;

public class RedDeAguaPotable {
	
	private ArbolGeneral<Character> red;

	public RedDeAguaPotable(ArbolGeneral<Character> red) {
		this.red = red;
	}
	
	public double minimoCaudal (double caudal) {
		
		double []resultado = new double [1];
		resultado[1] = 9999.99;
		if (!this.red.esVacio()) {
			this.minimoCaudal(caudal, red, resultado);
		}
		return resultado[1];
	}
	
	private void minimoCaudal(double caudal, ArbolGeneral<Character> red,double[]resultado) {
		if (red.tieneHijos()) {
			ListaGenerica<ArbolGeneral<Character>> hijos = this.red.getHijos();
			caudal = caudal / hijos.tamanio();
			if (caudal < resultado[1])
				resultado[1] = caudal;
			hijos.comenzar();
			while (!hijos.fin())
				minimoCaudal(caudal,hijos.proximo(),resultado);
		}
	}
}
