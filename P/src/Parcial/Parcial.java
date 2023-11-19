package Parcial;

import tp03.ejercicio1.*;

public class Parcial {
	
	public boolean esPar(Integer num) {
		if (num % 2 == 0)
			return true;
		else
			return false;
	}
	
	public Integer resolver (ArbolBinario<Integer> ab) {
		Integer[]resultado = new Integer[1];
		resultado[1] = 0;
		if (!ab.esVacio())
			postOrden(ab,resultado);
		return resultado[1];
	}
	
	public Integer resolver1(ArbolBinario<Integer> ab) {
		Integer resultado = 0;
		if (!ab.esVacio()) {
			if (ab.tieneHijoDerecho())
				resultado += this.resolver1(ab.getHijoIzquierdo());
			if (ab.tieneHijoDerecho())
				resultado += this.resolver1(ab.getHijoDerecho());
		if (ab.esHoja()) { 
			if (!esPar(ab.getDato())) 
				resultado -= ab.getDato(); 
		}else
			if (esPar(ab.getDato()))
				resultado += ab.getDato();
		}
		return resultado;
	}
	
	private void postOrden(ArbolBinario<Integer> ab, Integer[]resultado) {
		
		if (ab.tieneHijoIzquierdo())
			this.postOrden(ab.getHijoIzquierdo(),resultado);
		
		if (ab.tieneHijoDerecho())
			this.postOrden(ab.getHijoDerecho(), resultado);
		
		if (ab.esHoja()) { 
			if (!esPar(ab.getDato())) 
				resultado[1] -= ab.getDato(); 
		}else
			if (esPar(ab.getDato()))
				resultado[1] += ab.getDato();	
		}
	
	
}
