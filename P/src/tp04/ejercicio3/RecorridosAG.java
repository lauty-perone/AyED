package tp04.ejercicio3;

import tp02.ListaEnlazadaGenerica;
import tp02.ListaGenerica;
import tp03.ejercicio1.ColaGenerica;
import tp04.ejercicio1.ArbolGeneral;

public class RecorridosAG {
	
	private boolean esImpar(Integer num) {
		if (num % 2 != 0)
			return true;
		return false;
	}
	
	public ListaGenerica<Integer> numerosImparesMayoresQuePreOrden (ArbolGeneral 
			<Integer> a, Integer n){
		ListaGenerica<Integer> resultado = new ListaEnlazadaGenerica<Integer>();
		if (!a.esVacio()) {
			preOrden(a,n,resultado);
		}
		return resultado; 
	}

	private void postOrden(ArbolGeneral<Integer> a, Integer n, ListaGenerica<Integer> resultado) {
		
		if (a.tieneHijos()) {
			ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
			hijos.comenzar();
			while(!hijos.fin())
				this.postOrden(hijos.proximo(), n, resultado);
		}
		if (this.esImpar(a.getDato()) && a.getDato() > n)
			resultado.agregarFinal(a.getDato());
	}

	private void inOrden(ArbolGeneral<Integer> a, Integer n, ListaGenerica<Integer> resultado) {
		
		if (a.tieneHijos()){
			ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
			hijos.comenzar();
			if (!hijos.esVacia())
				this.inOrden(hijos.proximo(), n, resultado);
			if (this.esImpar(a.getDato()) && a.getDato()> n) {
				resultado.agregarFinal(a.getDato());
			}
			if (a.tieneHijos())
				while(!hijos.fin())
					this.inOrden(hijos.proximo(), n, resultado);
		}
			
		
	}

	private void preOrden(ArbolGeneral<Integer> a, Integer n, ListaGenerica<Integer> resultado) {
		
		if (this.esImpar(a.getDato()) && a.getDato() > n)
			resultado.agregarFinal(a.getDato());
		if (a.tieneHijos()) {
			ListaGenerica<ArbolGeneral<Integer>>hijos = a.getHijos();
			hijos.comenzar();
			while(!hijos.fin())
				this.preOrden(hijos.proximo(), n, resultado);
			
		}
		
	}
	
	public ListaGenerica<Integer> porNiveles(ArbolGeneral<Integer> a, Integer n) {
		ListaGenerica<Integer> resultado = new ListaEnlazadaGenerica<Integer>();
		ColaGenerica<ArbolGeneral<Integer>> cola = new ColaGenerica<ArbolGeneral<Integer>>();
		ArbolGeneral<Integer> aux = null;
		
		cola.encolar(a);
		cola.encolar(null);
		
		while (!cola.esVacia()) {
			aux = cola.desencolar();
			if (aux != null) {
				if (this.esImpar(aux.getDato()) && aux.getDato() > n)
					resultado.agregarFinal(aux.getDato());
				if (aux.tieneHijos()) {
					ListaGenerica<ArbolGeneral<Integer>> hijos = aux.getHijos();
					hijos.comenzar();
					while (!hijos.fin())
						cola.encolar(hijos.proximo());
				}
			}
			else 
				if (!cola.esVacia())
					cola.encolar(null);
		}
		
		
		return resultado;
	}
	
}
