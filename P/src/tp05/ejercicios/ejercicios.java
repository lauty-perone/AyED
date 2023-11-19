package tp05.ejercicios;

public class ejercicios {
	/*Ejercicio4
	 * A) 3^n es de O(2^n)
	 * 
	 * Falso
	 * 
	 * Primer término:
	 * c^n crece igual que c^n. Entonces c^<= c^n
	 * Si se multiplica por 3 a ambis miebros 3^n<= 3^n
	 * Si c1= 3 es posible acotar la función 
	 * 
	 * Buscar n0: 
	 * 		Si tiene que 3^n<= 3^n entonces si n0=1
	 * 		3^1 <= 3^1 ----> vale la desigualdad.
	 * 		Como es un solo termino T(n) <= O(3^n) con c=3 y n0 = 2
	 * 
	 * B) n+ log2(n)
	 * 
	 * Verdadero, el orden es del término que crece mas rapido, en este caso n.
	 * Regla de la suma --> O(max(f(x).g(x)))
	 * Primer término
	 * 		n crece igual que n. Entonces n<= n.
	 * 		Si se multiplica por 1 ambos terminos n<= n
	 * 		En el lado izquierdo se obtiene el T(n)y del lado derecho se obtiene un valor para c1. 
	 * 														Si c1=1 es posible acotar la función
	 * Buscar n0
	 * 		Se tiene que n<= n. Entonces si n0=0
	 * 		0<= 0 vale la desigualdad.
	 * 
	 * Segundo término: log2(n)<= n
	 * 		log2(n) crece mas lento que n.Entonces log2(n)<= n
	 * 		Si se multiplica por 1 a ambos miebros: log2(n)<= n
	 * 		En el lado izquierdo se obtiene el primer termino de T(n) y del lado derecho se encuentra un valor para c2.
	 * 		Si c2= 1 es posible acotar la funcion.
	 * 		n0=1 --> log2(1)<=1 vale la desigualdad. Con n0 = 0 no vale
	 * Obtengo c y n0 para todo T(n)
	 * 		n+log2(n)<= c1n + c2log2(n)
	 * 		n+ log2 (n)<= (c1+c2)n+log2(n)
	 * 		n+ log2(n) <= (1+1)n + log2(n)
	 * 		
	 * 		c=2 n0 mas restrictivo -> n0=1
	 * Por lo tanto 
	 * 		T(n)<= O(n) con c=2 y con n0 =1
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
