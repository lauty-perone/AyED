package tp03.ejercicio1;

import tp02.ListaEnlazadaGenerica;

public class ArbolBinario<T> {
	private T dato;
	private ArbolBinario<T> hijoIzquierdo;   
	private ArbolBinario<T> hijoDerecho; 

	
	public ArbolBinario() {
		super();
	}

	public ArbolBinario(T dato) {
		this.dato = dato;
	}

	/*
	 * getters y setters
	 * 
	 */
	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}
	
	/**
	 * Preguntar antes de invocar si tieneHijoIzquierdo()
	 * @return
	 */
	public ArbolBinario<T> getHijoIzquierdo() {
		return this.hijoIzquierdo;
	}

	public ArbolBinario<T> getHijoDerecho() {
		return this.hijoDerecho;

	}

	public void agregarHijoIzquierdo(ArbolBinario<T> hijo) {
		this.hijoIzquierdo = hijo;
	}

	public void agregarHijoDerecho(ArbolBinario<T> hijo) {
		this.hijoDerecho = hijo;
	}

	public void eliminarHijoIzquierdo() {
		this.hijoIzquierdo = null;
	}

	public void eliminarHijoDerecho() {
		this.hijoDerecho = null;
	}

	public boolean esVacio() {
		return this.getDato() == null && !this.tieneHijoIzquierdo() && !this.tieneHijoDerecho();
	}

	public boolean esHoja() {
		return (!this.tieneHijoIzquierdo() && !this.tieneHijoDerecho());

	}

	@Override
	public String toString() {
		return this.getDato().toString();
	}

	 
	public boolean tieneHijoIzquierdo() {
		return this.hijoIzquierdo!=null;
	}

	 
	public boolean tieneHijoDerecho() {
		return this.hijoDerecho!=null;
	}

	public int contarHojas() {
		int cant = 0;
		if (this.tieneHijoIzquierdo())
			cant = cant + this.getHijoIzquierdo().contarHojas();
		if (this.esHoja())
			return 1;
		if (this.tieneHijoDerecho())
			cant = cant + this.getHijoDerecho().contarHojas();
		return cant;
	}
	

    public ArbolBinario<T> espejo() {
		ArbolBinario<T> espejo = new ArbolBinario<T>(this.getDato());
		if (this.tieneHijoIzquierdo())
			espejo.agregarHijoDerecho(this.getHijoIzquierdo().espejo());
		if (this.tieneHijoDerecho())
			espejo.agregarHijoIzquierdo(this.getHijoDerecho().espejo());
		return espejo;
	}


	public void entreNiveles(int n, int m){
		ArbolBinario<T> aux = null;
		ColaGenerica<ArbolBinario<T>> cola = new ColaGenerica<ArbolBinario<T>>();
		
		cola.encolar(this);
		cola.encolar(null);
		int nivel = 0;
		while (!cola.esVacia() && nivel<= m) {
			aux = cola.desencolar();
			if (aux != null) {
				if (nivel>=n) 
					System.out.println(this.dato);
				if (this.tieneHijoIzquierdo())
					cola.encolar(this.getHijoIzquierdo());
				if (this.tieneHijoDerecho())
					cola.encolar(this.getHijoDerecho());
			}else if (!cola.esVacia()) {
				nivel++;
				cola.encolar(null);
			}
		}
	}
	//camino que sea el mas largo y entre los mas largos tenga la mayor cantidad de numeros pares
	public ListaEnlazadaGenerica<T> caminoMasLargo(ArbolBinario<T> arbol){
		
		ListaEnlazadaGenerica<T> actual = new ListaEnlazadaGenerica<T> ();
		ListaEnlazadaGenerica<T> maximo = new ListaEnlazadaGenerica<T> ();
		Integer cantParesActual = 0;
		int [] cantParesMax = new int[0];
		if (!arbol.esVacio())
			this.recursion(actual,maximo,arbol, cantParesActual, cantParesMax);
		return maximo;
	}

	private void recursion(ListaEnlazadaGenerica<T> actual, ListaEnlazadaGenerica<T> maximo, 
			ArbolBinario<T> arbol, Integer cantA,int[] cantM) {
		
		actual.agregarFinal(arbol.getDato());
		if (!arbol.esHoja()) {
			if (arbol.tieneHijoIzquierdo())
				this.recursion(actual, maximo, arbol.getHijoIzquierdo(), cantA, cantM);
			if (arbol.tieneHijoDerecho())
				this.recursion(actual, maximo, arbol.getHijoDerecho(),cantA, cantM);
		}else {
			if (actual.tamanio()>maximo.tamanio()) {
				this.copiar(actual,maximo);
			}else
				if (actual.tamanio() == maximo.tamanio()) {
					if (cantA > cantM[0]) {
						this.copiar(actual, maximo);
						cantM[0] = cantA;
					}
				}
		}
		actual.eliminarEn(actual.tamanio());	
	}
	

	private void copiar(ListaEnlazadaGenerica<T> actual, ListaEnlazadaGenerica<T> maximo) {
		maximo.comenzar();
		while (!maximo.fin()) {
			maximo.eliminar(maximo.proximo());
		}
		
		actual.comenzar();
		while(!actual.fin())
			maximo.agregarFinal(actual.proximo());
		
	}

	     
	
	

	

}
