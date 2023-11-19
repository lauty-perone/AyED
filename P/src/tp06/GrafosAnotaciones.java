package tp06;

import tp02.ejercicio2.*;
import tp03.ejercicio1.*;

public class GrafosAnotaciones <T> {
	
	public void dfs(Grafo<T> grafo, T dato) {
		//buscar el vertice que tiene el dato
		Vertice<T> vertice = null;
		
		//Java inicializa por defecto en false todo el arreglo
		boolean [] visitados = new boolean [grafo.listaDeVertices().tamanio()+1];
		ListaGenerica<Vertice<T>> vertices = grafo.listaDeVertices();
		vertices.comenzar();
		while(!vertices.fin()) {
			vertice = vertices.proximo();
			if (vertice.dato().equals(dato))
				break;
		}
		if (vertice != null)
			this.dfs_private(grafo,vertice,visitados);
	}

	private void dfs_private(Grafo<T> grafo, Vertice<T> vertice, boolean[]visitados) {
		
		visitados[vertice.getPosicion()] = true;
		// procesar el dato
		System.out.println(vertice);
		
		//llamar recursivaente
		ListaGenerica<Arista<T>> aristas = grafo.listaDeAdyacentes(vertice);
		aristas.comenzar();
		while (!aristas.fin()) {
			Arista<T> arista = aristas.proximo();
			Vertice<T> vDestino = arista.verticeDestino();
			if (visitados[vDestino.getPosicion()])
				this.dfs_private(grafo, vDestino,visitados);
		}
		
		visitados[vertice.getPosicion()] = false;
	}
	
	public void bfs(Grafo<T> grafo, T dato) {
		//buscar el vertice que tiene el dato
		Vertice<T> vertice = null;
				
		//Java inicializa por defecto en false todo el arreglo
		boolean [] visitados = new boolean [grafo.listaDeVertices().tamanio()+1];
		ListaGenerica<Vertice<T>> vertices = grafo.listaDeVertices();
		vertices.comenzar();
		while(!vertices.fin()) {
			vertice = vertices.proximo();
			if (vertice.dato().equals(dato))
				break;
			}
			if (vertice != null)
				this.bfs_private(grafo,vertice,visitados);
			}

	private void bfs_private(Grafo<T> grafo, Vertice<T> vertice, boolean[] visitados) {
		
		visitados [vertice.getPosicion()] = true;
		ColaGenerica<Vertice<T>> cola = new ColaGenerica<Vertice<T>>();
		cola.encolar(vertice);
		cola.encolar(null);
		
		while (!cola.esVacia()) {
			Vertice<T> vAux = cola.desencolar();
			if (vAux != null) {
				System.out.println(vAux.dato());
				
				//llamar recursivaente
				ListaGenerica<Arista<T>> aristas = grafo.listaDeAdyacentes(vAux);
				aristas.comenzar();
				while (!aristas.fin()) {
					Arista<T> arista = aristas.proximo();
					Vertice<T> vDestino = arista.verticeDestino();
					
					if (!visitados[vDestino.getPosicion()]) {
						visitados[vDestino.getPosicion()] =  true;
						cola.encolar(vAux);
					}
				}
			}else {
				if (!cola.esVacia())
					cola.encolar(null);
			}
		}
	}
}
