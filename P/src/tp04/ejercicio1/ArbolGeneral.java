package tp04.ejercicio1;

import tp02.ListaEnlazadaGenerica;
import tp02.ListaGenerica;
import tp03.ejercicio1.ColaGenerica;

public class ArbolGeneral<T> {

	private T dato;

	private ListaGenerica<ArbolGeneral<T>> hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public void setHijos(ListaGenerica<ArbolGeneral<T>> hijos) {
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ArbolGeneral(T dato) {
		this.dato = dato;
	}

	public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> hijos) {
		this(dato);
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ListaGenerica<ArbolGeneral<T>> getHijos() {
		return this.hijos;
	}

	public void agregarHijo(ArbolGeneral<T> unHijo) {

		this.getHijos().agregarFinal(unHijo);
	}

	public boolean esHoja() {

		return !this.tieneHijos();
	}
	
	public boolean tieneHijos() {
		return !this.hijos.esVacia();
	}
	
	public boolean esVacio() {

		return this.dato == null && !this.tieneHijos();
	}

	

	public void eliminarHijo(ArbolGeneral<T> hijo) {
		if (this.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
			if (hijos.incluye(hijo)) 
				hijos.eliminar(hijo);
		}
	}
	
	public ListaEnlazadaGenerica<T> preOrden() {
		return null;
	}
	
	public Integer altura() {
		int altura =-1;
		if (this.esHoja())
			return 0;
		if (this.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>>lHijos = this.getHijos();
			lHijos.comenzar();
			while(!lHijos.fin())
				altura = Math.max(altura, lHijos.proximo().altura());
			
		}
		return altura;
	}

	/*public Integer nivel(T dato) {
		ColaGenerica<ArbolGeneral<T>> cola= new ColaGenerica<ArbolGeneral<T>>();
		ArbolGeneral<T> arbol_aux ;
		
		cola.encolar(this);
		cola.encolar(null);
		
		int nivel=0;
		
		if (!cola.esVacia()) {
			arbol_aux = cola.desencolar();
			if (arbol_aux != null) {
				if (arbol_aux.getDato() ==dato)
					return nivel;
			if (arbol_aux.tieneHijos()) {
				ListaGenerica<ArbolGeneral<T>>lHijos= this.getHijos();
				lHijos.comenzar();
				while(!lHijos.fin())
					cola.encolar(lHijos.proximo());
			}
			
		}
		else
			if(!cola.esVacia()) {
				cola.encolar(null);
				nivel++;
			}
		return nivel;
	}*/

	public Integer ancho() {
		ColaGenerica<ArbolGeneral<T>>cola =new  ColaGenerica<ArbolGeneral<T>>();
	    ArbolGeneral<T> arbol_aux;
	    
	    cola.encolar(this);
	    cola.encolar(null);
	    
	    int cant=0, max =-1;
	    if (!cola.esVacia()) {
	    	arbol_aux= cola.desencolar();
	    	if (arbol_aux !=null) {
	    		cant++;
	    		if (arbol_aux.tieneHijos()) {
	    			ListaGenerica<ArbolGeneral<T>>lHijos = arbol_aux.getHijos();
	    			lHijos.comenzar();
	    			while(!lHijos.fin())
	    				cola.encolar(lHijos.proximo());
	    		}
	    	}
	    	else {
	    		if (cant>max) 
	    			max=cant;
	    	    cant=0;
	    		if(!cola.esVacia()) {
	    			cola.encolar(null);	    		
	           }
		
	        }
        
        }
	    return max;
    }
	
	/*public Boolean esAncestro(T a, T b) {
		ListaGenerica<T> lista = new ListaEnlazadaGenerica<T>();
		ListaGenerica<T>camino = new ListaEnlazadaGenerica<T>();
	    lista.agregarFinal(this.getDato());
		esAncestro(a,b,lista,camino);
		if (camino.incluye(a)&&(camino.incluye(b)))
			return true;
		else
			return false;
	}
	
	private void esAncestro(T a, T b, ListaGenerica<T>lista, 
			                     ListaGenerica<T>camino) {
	    if (this.getDato()==a) {
		   lista.comenzar();
		   while(!lista.fin())
		     	camino.agregarFinal(lista.proximo());
	    }
	    if (camino.esVacia()) {
		   ListaGenerica<ArbolGeneral<T>> l = this.getHijos();
		   l.comenzar();
		   while(!l.fin()&&(camino.esVacia())) {
			   ArbolGeneral<T>aux =l.proximo();
			   lista.agregarFinal(aux.getDato());
			   aux.esAncestro(a, b,lista,camino);
			   lista.eliminarEn(lista.tamanio());
			   
		   }
			
	     }
	
	}*/
	
	public Boolean esAncestro(T a, T b) {
		ListaGenerica<T> actual = new ListaEnlazadaGenerica<T>();
		ListaGenerica<T> camino = new ListaEnlazadaGenerica<T>();
		esAncestro(actual,camino,a,b);
		if (camino.incluye(a)&& camino.incluye(b))
			return true;
		return false;
	}

	private void esAncestro(ListaGenerica<T> actual, ListaGenerica<T> camino, T a, T b) {
		
		if (this.getDato()== a) {
			actual.comenzar();
			while (!actual.fin())
				camino.agregarFinal(actual.proximo());
		}
		if (camino.esVacia()) {
			ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
			hijos.comenzar();
			while (!hijos.fin() && !camino.esVacia()) {
				ArbolGeneral<T> aux =hijos.proximo();
				actual.agregarFinal(aux.getDato());
				aux.esAncestro(actual, camino, a, b);
				actual.eliminarEn(actual.tamanio());
			}
		}
		
	}
}
