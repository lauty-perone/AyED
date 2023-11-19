package tp03.ejercicio1;

import tp02.*;

public class ColaGenerica <T>{
      private ListaEnlazadaGenerica <T>lista =new ListaEnlazadaGenerica<T>();
      
      public void encolar(T elem) {
    	 lista.agregarFinal(elem);
      }
      
      public T desencolar () {
    	  T x = lista.elemento(1);
    	  lista.eliminarEn(1);
    	  return x;
      }
      
      public T tope () {
    	  
    	  return lista.elemento(lista.tamanio());
      }
      
      public boolean esVacia() {
    	  
    	  return lista.esVacia();
      }
      
}
