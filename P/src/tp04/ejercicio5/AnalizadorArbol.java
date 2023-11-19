package tp04.ejercicio5;

import tp04.ejercicio1.ArbolGeneral;
import tp03.ejercicio1.ColaGenerica;
import tp02.ListaGenerica;

public class AnalizadorArbol {
	
	public int devolverMaximoPromedio (ArbolGeneral<AreaEmpresa>arbol) {
		
		int promedio  =-1;
		int suma = 0;
		int cant = 0;
		
		ColaGenerica<ArbolGeneral<AreaEmpresa>> cola = new ColaGenerica<ArbolGeneral<AreaEmpresa>>();
		ArbolGeneral<AreaEmpresa> aux = null;
		cola.encolar(arbol);
		cola.encolar(null);
		
		while (!cola.esVacia()) {
			aux = cola.desencolar();
			cant ++;
			if (aux != null) {
				suma += aux.getDato().getTardanza();
				ListaGenerica<ArbolGeneral<AreaEmpresa>> hijos = aux.getHijos();
				hijos.comenzar();
				while (!hijos.fin())
					cola.encolar(hijos.proximo());
			}
		if (!cola.esVacia()) {
			suma = suma/cant;
			if(suma > promedio)
				promedio = suma;
			cant = 0;
			suma = 0;
			cola.encolar(null);
		}
	}
			
		
		return promedio;
	}
}
