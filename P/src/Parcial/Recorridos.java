package Parcial;

import tp04.ejercicio1.ArbolGeneral;
import tp02.*;
import tp03.ejercicio1.*;

public class Recorridos<T> {
	
	//Recorridos ARBOL BINARIO
	
	public void preOrden(ArbolBinario<T> a) {
		
		System.out.println(a.getDato());
		if (a.tieneHijoIzquierdo())
			preOrden(a.getHijoIzquierdo());
		if (a.tieneHijoDerecho())
			preOrden(a.getHijoDerecho());
	}
	
	public void inOrden(ArbolBinario<T> a) {
		if (a.tieneHijoIzquierdo())
			this.preOrden(a.getHijoIzquierdo());
		System.out.println(a.getDato());
		if (a.tieneHijoDerecho())
			this.preOrden(a.getHijoDerecho());
	}
	
	public void postOrden (ArbolBinario<T> a) {
		if (a.tieneHijoIzquierdo())
			this.preOrden(a.getHijoIzquierdo());
		if (a.tieneHijoDerecho())
			this.preOrden(a.getHijoDerecho());
		System.out.println(a.getDato());
	}
	
	//Recorridos Árbol General
	
	public void preOrden( ArbolGeneral<T> a) {
		System.out.println(a.getDato());
		if (a.tieneHijos()) {
			ListaGenerica <ArbolGeneral<T>> hijos = a.getHijos();
			hijos.comenzar();
			while (!hijos.fin());
				this.preOrden(hijos.proximo());
		}	
	}
	
	public void inOrden(ArbolGeneral<T> a) {
		if (a.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> hijos = a.getHijos();
			hijos.comenzar();
			if(!hijos.esVacia())
				this.inOrden(hijos.proximo());
		System.out.println(a.getDato());
		if (a.tieneHijos()) 
			while (!hijos.fin())
				this.inOrden(hijos.proximo());
		}			
	}
	
	public void postOrden (ArbolGeneral<T> a) {
		if (a.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> hijos = a.getHijos();
			hijos.comenzar();
			while (!hijos.fin())
				this.postOrden(hijos.proximo());
		}			
		System.out.println(a.getDato());

	}
	
	//Retorna la cantidad de niveles del arbol
	public int porNiveles(ArbolGeneral<T> a) {
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		int nivel = 0;
		ArbolGeneral<T> aux = null;
		
		cola.encolar(a);
		cola.encolar(null);
		
		while (!cola.esVacia()) {
			aux = cola.desencolar();
			if (aux != null) {
				System.out.println(aux.getDato());
				if (aux.tieneHijos()) {
					ListaGenerica<ArbolGeneral<T>> hijos = aux.getHijos();
					hijos.comenzar();
					while (!hijos.fin())
						cola.encolar(hijos.proximo());
				}
			}
			else
				if (!cola.esVacia()) {
					nivel++;
					cola.encolar(null);
				}
		}
		return nivel;
		
	}
	
	//Camino más corto
	
	public ListaGenerica<T> caminoMasLargo(ArbolGeneral<T> a){
		ListaGenerica<T> camino = new ListaEnlazadaGenerica<T>();
		ListaGenerica<T> actual = new ListaEnlazadaGenerica<T>();
		if (!a.esVacio())
			caminoMasLargo(a,camino,actual);
		return camino;
	}

	private void caminoMasLargo(ArbolGeneral<T> a, ListaGenerica<T> camino, ListaGenerica<T> actual) {
		
		actual.agregarFinal(a.getDato());
		if (a.esHoja()) {
			if (actual.tamanio() > camino.tamanio()) {
				//Vaciamos el camino más largo que teniamos
				camino.comenzar();
				while (!camino.fin())
					camino.eliminar(camino.proximo());
			}
		}
		else {
			ListaGenerica<ArbolGeneral<T>> hijos = a.getHijos();
			hijos.comenzar();
			while (!hijos.fin())
				this.caminoMasLargo(hijos.proximo(), camino, actual);
			actual.eliminarEn(actual.tamanio());
		}
		
	}
	
}
