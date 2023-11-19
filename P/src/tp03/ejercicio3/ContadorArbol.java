package tp03.ejercicio3;

import tp03.ejercicio1.ArbolBinario;
import tp02.*;

public class ContadorArbol {
	
	private ArbolBinario<Integer> arbol;
	
	public ListaGenerica<Integer> numerosPares() {
		ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		if (!arbol.esVacio()) {
			this.InOrden(lista, arbol);
			this.PostOrden(lista, arbol);
		}
		return lista;
		
	}
	
	private void InOrden(ListaGenerica<Integer> lista, ArbolBinario<Integer> arbol) {
			if(arbol.tieneHijoIzquierdo())
				this.InOrden(lista, arbol.getHijoIzquierdo());
			if (arbol.getDato() % 2 == 0)
				lista.agregarFinal(arbol.getDato());
			if (arbol.tieneHijoDerecho())
				this.InOrden(lista, arbol.getHijoDerecho());
		
	}
	
	private void PostOrden(ListaGenerica<Integer> lista, ArbolBinario<Integer> arbol) {
		if (arbol.tieneHijoIzquierdo())
			this.PostOrden(lista, arbol.getHijoIzquierdo());
		if (arbol.tieneHijoDerecho())
			this.PostOrden(lista, arbol.getHijoIzquierdo());
		if (arbol.getDato() % 2 == 0)
			lista.agregarFinal(arbol.getDato());
	}
	
}
