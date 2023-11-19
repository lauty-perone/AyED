package Parcial;

import tp02.ejercicio2.ListaGenerica;
import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp06.Arista;
import tp06.Grafo;
import tp06.Vertice;


public class RecorridosGrafo <T>{
	
	//guardar un camino en una lista
	
	/*public ListaEnlazadaGenerica<Vertice<T>> dfs (Grafo<T> grafo){
		boolean [] marca = new boolean [grafo.listaDeVertices().tamanio()+1];
		ListaEnlazadaGenerica<Vertice<T>> lis = new ListaEnlazadaGenerica<Vertice<T>>();
		for (int i =1; i<= grafo.listaDeVertices().tamanio(); i++)
			if (!marca[i])
				this.dfs(i,grafo,lis,marca);
		return lis;
	}

	private void dfs( int i, Grafo<T> grafo, ListaEnlazadaGenerica<Vertice<T>> lis, boolean[] marca) {
		marca[i] = true;
		Vertice<T> v = grafo.listaDeVertices().elemento(i);
		lis.agregarEn(v, lis.tamanio());
		ListaGenerica<Arista<T>> ady = grafo.listaDeAdyacentes(v);
		ady.comenzar();
		while (!ady.fin()) {
			int j = ady.proximo().verticeDestino().getPosicion();
			if (!marca[j])
				this.dfs( j, grafo,lis, marca);
		}		
	}
	//ej1
	public ListaEnlazadaGenerica<Ciudad> resolver (Grafo<Ciudad> ciudades, String destino, String origen){
		boolean [] marca = new boolean[ciudades.listaDeVertices().tamanio()+1];
		ListaEnlazadaGenerica<Ciudad> camino = new ListaEnlazadaGenerica<Ciudad>();
		
		ListaGenerica<Vertice<Ciudad>> aux = ciudades.listaDeVertices();
		boolean ok = false;
		Vertice<Ciudad> v = null;
		
		aux.comenzar();
		while (!aux.fin() && !ok) {
			v = aux.proximo();
			if (v.dato().getNombre().equals(origen))
				ok = true;
		}
		if (ok && v.dato().getFase() != 1)
			this.recorrer(ciudades,camino,marca,destino,v.getPosicion());
		return camino;
	}

	private boolean recorrer(Grafo<Ciudad> ciudades, ListaEnlazadaGenerica<Ciudad> camino, boolean[] marca, String destino,
			int i) {
		marca[i] = true;
		boolean encontre = false;
		
		Vertice<Ciudad> v = ciudades.listaDeVertices().elemento(i);
		camino.agregarFinal(v.dato());
		if (v.dato().getNombre().equals(destino))
			encontre = true;
		else {
			ListaGenerica<Arista<Ciudad>> ady = ciudades.listaDeAdyacentes(v);
			
			ady.comenzar();
			while (!ady.fin()) {
				Arista<Ciudad> a = ady.proximo();
				int j = a.verticeDestino().getPosicion();
				if (!marca[j] && a.verticeDestino().dato().getFase() != 1)
					encontre = this.recorrer(ciudades, camino, marca, destino, j);
			}
		}
		if (!encontre) {
			marca[i] = false;
			camino.eliminarEn(camino.tamanio());
		}
		return encontre;
	}
	
	//ej2
	public ListaEnlazadaGenerica<String> resolver (Grafo<String> ciudades, String origen, String destino,
			                                       ListaEnlazadaGenerica<String> pasandoPor){
		boolean [] marca = new boolean [ciudades.listaDeVertices().tamanio()+1];
		ListaEnlazadaGenerica<String> camino = new ListaEnlazadaGenerica<String>();
		
		boolean ok = false;
		ListaGenerica<Vertice<String>> aux = ciudades.listaDeVertices();
		Vertice<String> v = null;
		aux.comenzar();
		while (!aux.fin() && !ok) {
			v = aux.proximo();
			if (v.dato().equals(origen))
				ok = true;
		}
		
		if (ok)
			this.recorrer(ciudades,camino,marca,destino,pasandoPor, v.getPosicion());
		return camino;
	}

	private boolean recorrer(Grafo<String> ciudades, ListaEnlazadaGenerica<String> camino, boolean[] marca, String destino,
			ListaEnlazadaGenerica<String> pasandoPor, int i) {
		marca[i] = true;
		Vertice<String> v = ciudades.listaDeVertices().elemento(i);
		camino.agregarEn(v.dato(), camino.tamanio());
		boolean encontre = false;
		
		if (v.dato().equals(destino)) {
			encontre = true;
			pasandoPor.comenzar();
			while (!pasandoPor.fin() && encontre)
				encontre = camino.incluye(pasandoPor.proximo());
		}
		else {
			ListaGenerica<Arista<String>> ady = ciudades.listaDeAdyacentes(v);
			ady.comenzar();
			while (!ady.fin() && !encontre) {
				Arista<String> a = ady.proximo();
				int j = a.verticeDestino().getPosicion();
				if (!marca[j]) {
					encontre = this.recorrer(ciudades, camino, marca, destino, pasandoPor, j);
					if (!encontre) {
						marca[j] = false;
						camino.eliminarEn(camino.tamanio());
					}
				}
			}
		}
		return encontre;
		
	}
	
	//ej3
	public ListaEnlazadaGenerica<ListaEnlazadaGenerica<String>> resolver3(Grafo<String> ciudades,
																		String origen, String destino,
																		String pasandoPor){
		boolean []marca= new boolean [ciudades.listaDeVertices().tamanio()+1];
		ListaEnlazadaGenerica<ListaEnlazadaGenerica<String>> caminos = new ListaEnlazadaGenerica<ListaEnlazadaGenerica<String>>();
		ListaEnlazadaGenerica<String> camino = new ListaEnlazadaGenerica<String>();
		
		ListaGenerica<Vertice<String>> aux = ciudades.listaDeVertices();
		boolean ok = false;
		Vertice<String> v = null;
		aux.comenzar();
		while (!ok && !aux.fin()) {
			v = aux.proximo();
			if (v.dato().equals(origen))
				ok = true;
		}
		
		if (ok)
			recorrer3(ciudades,camino,caminos,destino,pasandoPor,v.getPosicion(),marca);
		return caminos;
	}

	private void recorrer3(Grafo<String> ciudades, ListaEnlazadaGenerica<String> camino,
			ListaEnlazadaGenerica<ListaEnlazadaGenerica<String>> caminos, String destino,
			String pasandoPor, int i, boolean[]marca) {
		marca[i] = true;
		Vertice<String> v = ciudades.listaDeVertices().elemento(i);
		camino.agregarEn(v.dato(),camino.tamanio());
		if (v.dato().equals(destino) && camino.incluye(pasandoPor)) {
			caminos.agregarFinal(camino);
		}
		else {
			ListaGenerica<Arista<String>> ady = ciudades.listaDeAdyacentes(v);
			ady.comenzar();
			while (!ady.fin()) {
				Arista<String> a = ady.proximo();
				int j = a.verticeDestino().getPosicion();
				if (!marca[j] && a.peso() == 1) {
					this.recorrer3(ciudades, camino, caminos, destino, pasandoPor, j, marca);
					marca[j] = false;
					camino.eliminarEn(camino.tamanio());
				}
			}
		}
		
		
	}*/
	
	//ej6
	
	public ListaGenerica<ListaGenerica<String>>resolver (Grafo<String> ciudades){
		boolean[] marca = new boolean [ciudades.listaDeVertices().tamanio()+1];
		ListaGenerica<ListaGenerica<String>> caminos = new ListaEnlazadaGenerica<ListaGenerica<String>>();
		ListaGenerica<String> camino = new ListaEnlazadaGenerica<String>();
		int costo;
		for (int i=1; i< ciudades.listaDeVertices().tamanio();i++) {
			costo = 0;
			resolver(i,camino,caminos, marca,ciudades,costo);
			caminos.eliminarEn(caminos.tamanio());
			marca[i]= false;
		}
		return caminos;
	}

	private void resolver(int i, ListaGenerica<String> camino, ListaGenerica<ListaGenerica<String>> caminos,
			boolean[] marca, Grafo<String> ciudades, int costo) {
		marca[i] = true;
		Vertice<String> v = ciudades.listaDeVertices().elemento(i);
		//camino.agregarFinal(v.dato());
		if (costo == 10)
			caminos.agregarFinal(camino.clonar());
		else {
			ListaGenerica<Arista<String>> ady = ciudades.listaDeAdyacentes(v);
			ady.comenzar();
			while (!ady.fin()) {
				int j = ady.proximo().verticeDestino().getPosicion();
				if (!marca[j]) {
					this.resolver(j, camino, caminos, marca, ciudades, costo + ady.proximo().peso());
					marca[j] =false;
					camino.eliminarEn(camino.tamanio());
				}
			}
		}
		
	}
	
	//ej9
	public ListaEnlazadaGenerica<String> buscarCaminoEntreOrigenYDestino(String origen, String destino, Grafo<String> ciudad){
		
		boolean [] marca = new boolean[ciudad.listaDeVertices().tamanio()+1];
		ListaEnlazadaGenerica <String> camino = new ListaEnlazadaGenerica<String>();
		Vertice<String> v = buscar(ciudad,origen);
		if (v != null && buscar(ciudad,destino)!= null) {
			resolver (v.getPosicion(),camino,marca,destino,ciudad);
		}
		return camino;
		
	}

	private Vertice<String> buscar(Grafo<String> ciudad, String estacion) {
		Vertice<String> v  =null;
		ListaGenerica<Vertice<String>> vertices = ciudad.listaDeVertices();
		vertices.comenzar();
		while (!vertices.fin()) {
			v = vertices.proximo();
			if (v.dato().equals(estacion))
				return v;
		}
		return null;
	}
	
	
	
	private boolean resolver(int posicion, ListaEnlazadaGenerica<String> camino, boolean[] marca, String destino,
			Grafo<String> ciudad) {
		
		boolean encontre = false;
		marca[posicion] = true;
		Vertice<String> v = ciudad.listaDeVertices().elemento(posicion);
		//camino.agregarFinal(v.dato());
		
		if (v.dato().equals(destino))
			encontre =true;
		else
		{
			ListaGenerica<Arista<String>> ady = ciudad.listaDeAdyacentes(v);
			ady.comenzar();
			while (!ady.fin()&& !encontre) {
				int j = ady.proximo().verticeDestino().getPosicion();
				if (!marca[j])
					encontre = this.resolver(j, camino, marca, destino, ciudad);
			}
			if (!encontre) {
				marca[posicion] = false;
				camino.eliminarEn(camino.tamanio());
			}
		}
		return encontre;
	}
	
	//ej10
	//si en la firma del metodo no tengo el grafo uso una variable de instancia con un getter
	public ListaGenerica<ListaGenerica<String>> recorridosMasSeguro(String casaCaperucita, String casaAbuelita, Grafo<String> bosque){
		boolean[] marca = new boolean[bosque.listaDeVertices().tamanio()+1];
		ListaGenerica<String> camino = new ListaEnlazadaGenerica<String>();
		ListaGenerica<ListaGenerica<String>> caminos = new ListaEnlazadaGenerica<ListaGenerica<String>>();
		Vertice<String> v = this.buscar2(bosque, casaCaperucita);
		if (v !=null && this.buscar2(bosque, casaAbuelita)!= null) 
			recorrer(v.getPosicion(),bosque,camino,caminos,marca,casaAbuelita);
		return caminos;
	}
	
	private void recorrer(int posicion, Grafo<String> bosque, ListaGenerica<String> camino,
			ListaGenerica<ListaGenerica<String>> caminos, boolean[] marca,String casaAbuelita) {
		marca[posicion]= true;
		Vertice<String> v = bosque.listaDeVertices().elemento(posicion);
		//camino.agregarFinal(v.dato());
		if (v.dato().equals(casaAbuelita)) {
			caminos.agregarFinal(camino.clonar());
		}
		else {
			ListaGenerica<Arista<String>>ady = bosque.listaDeAdyacentes(v);
			ady.comenzar();
			while (!ady.fin()) {
				Arista<String> a =ady.proximo();
				int j =a.verticeDestino().getPosicion();
				if (!marca[j] && a.peso()< 5) {
					this.recorrer(j, bosque, camino, caminos, marca, casaAbuelita);
					marca[j]=false;
					camino.eliminarEn(camino.tamanio());
				}
			}
		}
		
	}

	private Vertice<String> buscar2(Grafo<String> bosque, String casa){
		Vertice<String> v =null;
		ListaGenerica<Vertice<String>> vertices = bosque.listaDeVertices();
		vertices.comenzar();
		while (!vertices.fin()) {
			v = vertices.proximo();
			if (v.dato().equals(casa))
				return v;
		}	
		return null;
	}
	
	//ej11
	public ListaGenerica<ListaGenerica<String>>devolverCaminos(String origen, String destino, Grafo<String> mapa){
		boolean[] marca = new boolean[mapa.listaDeVertices().tamanio()+1];
		ListaGenerica<String> lista = new ListaEnlazadaGenerica<String>();
		ListaGenerica<ListaGenerica<String>> caminos = new ListaEnlazadaGenerica<ListaGenerica<String>>();
		
		ListaGenerica<Vertice<String>> aux = mapa.listaDeVertices();
		Vertice<String>v = null;
		boolean ok=false;
		aux.comenzar();
		while (!aux.fin() && !ok) {
			v = aux.proximo();
			if (v.dato().equals(origen))
				ok = true;
		}
		if (ok)
			recorrerMapa(v.getPosicion(), destino, mapa,caminos,lista,marca);
		return caminos;
	}

	private void recorrerMapa(int posicion, String destino, Grafo<String> mapa,
			ListaGenerica<ListaGenerica<String>> caminos, ListaGenerica<String> lista,boolean[]marca) {
		marca[posicion] =true;
		Vertice<String> v =mapa.listaDeVertices().elemento(posicion);
		//lista.agregarFinal(v.dato());
		
		if (v.dato().equals(destino))
			caminos.agregarFinal(lista.clonar());
		else {
			ListaGenerica<Arista<String>> ady = mapa.listaDeAdyacentes(v);
			ady.comenzar();
			while(!ady.fin()) {
				Arista<String>a = ady.proximo();
				int j= a.verticeDestino().getPosicion();
				if (!marca[j] && a.peso()== 0) {
					this.recorrerMapa(j, destino, mapa, caminos, lista, marca);
					marca[j]=false;
					lista.eliminarEn(lista.tamanio());
				}
			}
		}
	}
	
	//ej12
	public int resolverCiudad(Grafo<Ciudad> ciudades,String origen, String destino, int maxControles){
		boolean[] marca = new boolean[ciudades.listaDeVertices().tamanio()+1];
		int [] total = new int [2]; //contadores, uno actual y otro total
		for (int i=0; i< total.length; i++)
			total[i]=0;
		Vertice<Ciudad> v = buscar3(ciudades,origen);
		if (v != null && buscar3(ciudades,destino)!= null) {
			this.recorrerCiudades(v.getPosicion(),marca,ciudades,destino,maxControles,total);
		}
		return total[1];
	}

	private void recorrerCiudades(int posicion, boolean[] marca, Grafo<Ciudad> ciudades, String destino,
			int maxControles, int[] total) {
		marca[posicion]= true;
		Vertice<Ciudad> v= ciudades.listaDeVertices().elemento(posicion);
		
		if (v.dato().getNombre().equals(destino))
			if (total[1]> total[0])
				total[0]+= total[1]; // total[0] es el total y total[1] es el total del camino actual,
									//								se puede hacer con un objeto
														
		else {
			ListaGenerica<Arista<Ciudad>> ady = ciudades.listaDeAdyacentes(v);
			ady.comenzar();
			while (!ady.fin()) {
				Arista<Ciudad> a = ady.proximo();
				int j = a.verticeDestino().getPosicion();
				if (!marca[j] && a.peso() <= maxControles) {
					total[1] += a.verticeDestino().dato().getFase(); //getDias()
					this.recorrerCiudades(j, marca, ciudades, destino, maxControles, total);
					marca[j]=false;
				}
				
			}
		
		}
	}

	private Vertice<Ciudad> buscar3(Grafo<Ciudad> ciudades, String ciudad) {
		ListaGenerica<Vertice<Ciudad>> aux = ciudades.listaDeVertices();
		Vertice<Ciudad> v= null;
		aux.comenzar();
		
		while (!aux.fin()) {
			v = aux.proximo();
			if(v.dato().getNombre().equals(ciudad))
					return v;
		}
		return null;
	}
	
	//ej13
	public ListaGenerica<String> caminoDistanciaMaxima(Grafo<String> ciudades, String origen, String destino,
														int distanciaMaxima){
		boolean[]marca = new boolean[ciudades.listaDeVertices().tamanio()+1];
		ListaGenerica<String> camino = new ListaEnlazadaGenerica<String>();
		
		boolean ok = false;
		Vertice<String> v = null;
		ListaGenerica<Vertice<String>> aux = ciudades.listaDeVertices();
		aux.comenzar();
		while (!aux.fin() && !ok) {
			v = aux.proximo();
			if (v.dato().equals(origen))
				ok=true;
		}
		if (ok)
			recorrerCiudades2(v.getPosicion(), ciudades,destino,distanciaMaxima,marca,camino);
		return camino;
		
	}

	private boolean recorrerCiudades2(int posicion, Grafo<String> ciudades, String destino, int distanciaMaxima,
			boolean[] marca, ListaGenerica<String> camino) {
		boolean encontre = false;
		marca[posicion] = true;
		Vertice<String> v = ciudades.listaDeVertices().elemento(posicion);
		//camino.agregarFinal(v.dato());
		if (v.dato().equals(destino))
			encontre= true;
		else {
			ListaGenerica<Arista<String>> ady = ciudades.listaDeAdyacentes(v);
			ady.comenzar();
			while (!ady.fin() && !encontre) {
				Arista<String> a = ady.proximo();
				int j= a.verticeDestino().getPosicion();
				if (!marca[j] && a.peso() <= distanciaMaxima) {
					encontre= this.recorrerCiudades2(j, ciudades, destino, distanciaMaxima, marca, camino); 
				}
			}
			if (!encontre) {
				marca[posicion] =false;
				camino.eliminarEn(camino.tamanio());
			}
				
		}
		return encontre;
	}
	
	//ejWPP
	
	public ListaGenerica<String> estadios (Grafo<Estadio>mapaEstadios, String estadioOrigen, int cantKm){
		
		boolean [] marca = new boolean [mapaEstadios.listaDeVertices().tamanio()+1];
		ListaGenerica<String> lista = new ListaEnlazadaGenerica<String>();
		ListaGenerica<String> actual = new ListaEnlazadaGenerica<String>();
		
		Vertice<Estadio> v = null;
		boolean ok= false;
		ListaGenerica<Vertice<Estadio>> estadios = mapaEstadios.listaDeVertices();
		estadios.comenzar();
		while (!estadios.fin() && !ok) {
			v = estadios.proximo();
			if (v.dato().getNombreEstadio().equals(estadioOrigen))
				ok= true;
		}
		if (ok)
			recorrerEstadios (v.getPosicion(), mapaEstadios,marca, lista,actual, cantKm);
		return lista;
	}
	
	private void recorrerEstadios(int i, Grafo<Estadio>mapa, boolean[]marca, ListaGenerica<String>lista,
									ListaGenerica<String>actual, int cantKm) {
		marca[i]  =true;
		Vertice<Estadio> v = mapa.listaDeVertices().elemento(i);
		//actual.agregarFinal(v.dato().getNombreEstadio());
		
		if (actual.tamanio() > lista.tamanio()) {
			actual.comenzar();
			while (!actual.fin()) {
				//lista.agregarFinal(actual.proximo()); //clono el camino actual con la lista
			}
		}
		else {
			ListaGenerica<Arista<Estadio>> ady = mapa.listaDeAdyacentes(v);
			ady.comenzar();
			while (!ady.fin()) {
				Arista<Estadio> a = ady.proximo();
				int j = a.verticeDestino().getPosicion();
				if (!marca[j] && a.peso()< cantKm) {
					this.recorrerEstadios(j, mapa, marca, lista, actual, cantKm - a.peso());
					marca[j] = false;
					actual.eliminarEn(actual.tamanio());
				}
			}	
		}
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
