package tp05.anotaciones;


/*UNA UNIDAD DE TIEMPO : ASIGNACIONES, RETURN 
 *TRES UNIDADES DE TIEMPO : SUMA : SUMA + 1 (LECTURA, ESCRITURA, MODIFICACIÓN)
 **/
public class TiempoDeEjecucion {
	
	//UT = UNIDAD DE TIEMPO (TIEMPO CONSTANTE)
	public void algo(int n) {
		int suma = 0; //1 UT
		int prod = 1;// 1 UT

		for (int i=1 ; i<=n; i++) { //SE EJECUTA N VECES. FOR O WHILE COMO UNA SUMATORIA
			suma = suma + 1; //3 UT
			prod = prod + prod * suma; //6 UT
									   //LA REPRESENTACION DE CONSTANTES SE PUEDE PONER COMO CTE1 (UNA SOLA)
									   //T(N) = CT0 + N * CT1 (FUNCION LINEAL)
									   // T(n) = CT0 + Σ CT1
		}
	}
	
	//SE CALCULA DE LA SUMATORIA QUE ESTA MÁS ADENTRO HACIA AFUERA
	public void algo2(int n) {
		int suma = 0; //1 UT
		int prod = 1;// 1 UT
		
		for (int i=1 ; i<=n; i++) {
			for(int j =1; j<=10; j++) { //SE EJECUTA 10 VECES
				suma++;	//3 UT
			}
			suma = suma + 1; //3 UT
			prod = prod + prod * suma; //6 UT
									   //T(n) = CT0 +Σ + Σ CT1
									   //T(n) = CT0 + N * N *CT1
									   //T(n) = CT0 + N^2 * CT1
									   // FUNCIÓN CUADRÁTICA
									   
		}
	}
	
	public void algo3(int n) {
		int suma = 0; //1 UT
		int prod = 1;// 1 UT
		
		for (int i=1 ; i<=n; i++) {
			for(int j =1; j<=n; j= j*2) { //SE EJECUTA n VECES
				suma++;	//3 UT
			}
									   //T(n) = CT0 +Σ(de 1 a n) + Σ(de 1 a log(2)n)
									   //T(n) = CT0 + N(de 1 a n) + log2(n)*CT1
									   //T(n) = CT0 + N *CT1 * log2(n) + N*CT1
									   //FUNCIÓN LOGARITMICA
		}
	}
	
	public void algo4(int n) {
		int suma = 0; //1 UT
		int prod = 1;// 1 UT
		
		for (int i=1 ; i<=n; i++) {
			for(int j =1; j<=n; j= j+2) { //SE EJECUTA n VECES
				suma++;	//3 UT
			}
									   //T(n) = CT0 +Σ(de 1 a n) + Σ(de 1 a n/2 O n/3 si es j = j+3 )
									   //T(n) = CT0 + N * n/2*CT1
									   //FUNCIÓN LINEAL
		}
	}
	
	public void algo5(int n) {
		int suma = 0; //1 UT
		int prod = 1;// 1 UT
		
		for (int i=1 ; i<=n; i++) {
			for(int j =i; j<=n; j++) { //SE EJECUTA n VECES
				suma++;	//3 UT
			}
									   //T(n) = CT0 +Σ(de 1 a n) + Σ(de j=i a n) CT1
									   //T(n) = CT0 + Σ(de i=1 a n) Σ (de j=1 a i-1) CT1
									   //T(n) = CT0 + Σ(de i=1 a n) (N*CT1 - i-1)*CT1)
									   //T(n) = CT0 + Σ(de i=1 a n) (N *CT1 -i*ct1 + ct1)
									   //T(n) = CT0 + Σ(de i=1 a n)n*CT1 - Σ(de i=1 a n) i *CT1 + Σ(de i=1 a n)CT1
			                           //T(n) = CT0 + n^2 * CT1 - CT1 * (n* (n+1))/2 - CT1 * Σ(de i=1 a n) 
									   //						    FÓRMULA
									   //T(n) = CT0 + n^2 * CT1 - CT1 * N^2 + CT1 * N 
		}
	}
	
	//ALGORTIMOS RECURSIVOS
	
	public int algo6 (int n) {
		if (n<= 1)
			return 1;
		return algo6(n/2) * algo6(n/2);
	}
	//FUNCION DE RECURRENCIA 
	// T(n)= {cte1 si n<=1
	//       {Caso BASE =  2T(n/2) + cte2 --> La T(n) se escribe con n según cómo varia en el llamado
	/*1) 2T(n/2) +cte2
	 *2) 2[2T(n/2/2)+ cte2] + cte2 = 2^2 . T(n/2^2)+ 3cte2
	 *3) 2^2 . [2T(n/2^2/2) + cte2] + 3cte2 = 2^3T(n/2^3) + 7cte2 
	 *   PASO i = 2^i. T(n/2^i) + (2^i-1)cte2
	 * 	 Ahora hay que deshacerse de la i porque la función T(n) no admite dos variables
	 *   Se busca el valor de i = n/2^i = 1 --> n= 2^i --> log2n = i
	 * 	 Se reemplaza i = 2^ log2n T(n/2^log2n) + (2^log2n - 1)cte2
	 *   Y se resuelve = T(n) = nT(1) + (n-1).cte2
	 * 					 T(n) = n.cte1 + cte2-n - cte2
	 * */
	
	public int algo7 (int n) {
		int suma = 0;
		if (n >1) {
			for (int i=1; i<=n; i++)
				suma++;
			return algo7(n/2) * algo7(n/2);
		}
		return 1;
	}
	//ACLARACION  --> n^2 tiene que modificarse siempre la n --> (n/2)² o √n --> √(n/2) 
	/*Caso BASE = n<= 1
	 * T(n) ={cte1, n<=1
	 * 		 {Σ de i=1 a n cte2 + 2T(n/2) + cte3
	 * Se resuelve la sumatoria --> T(n)= {cte1, n<=1 
	 * 									  { n.cte2 + 2T(n/2) + cte3
	 * PASO 1)Se ejecuta una vez -->  n.cte2 + 2T(n/2) + cte3
	 * PASO 2) 2[n/2.cte2 + 2T(n/2/2) + cte3] + n.cte2+ cte3 --> 2²T(n/2²)+2cte3 + 2n.cte2
	 * PASO 3) 2² [n/2² + 2T(n/2²/2) + cte3] + 3.cte3 +2.n.cte2
	 *       --> 2³ T(n/2³)+ 7.cte3 + 3.n.cte2
	 * PASO i --> 2^i.T(n/2^i) + (2^i-1).cte3 + i.n.cte2
	 * */
	public int algo8 (int n) {
		int suma = 0;
		if (n >1) {
			for (int i=1; i<=n; i=i*2)
				suma++;
			return algo8(n/2) * algo8(n/2);
		}
		return 1;
	}
	/*T(n) ={cte1, n<=1
	 * 		 {Σ de i=log2 a n cte2 + 2T(n/2) + cte3
	 * PASO 1)Se ejecuta una vez -->  log2.n.cte2 + 2T(n/2) + cte3
	 * 
	 * */
	
	public int algo9 (int n) {
		int suma = 0;
		if (n >1) {
			int j = n;
			while (j>=1) {
				suma++;
				j--;
			}
			return algo9(n/2) * algo9(n/2);
		}
		return 1;
	}
	/* Σ de j =1 j hasta n
	 * */
	
	/*				BigOh = ORDEN
	 * Ejemplo = T(n) = 2.n.cte1 + 5n² - 3n³
	 * Si T(n) = O(f(n)) siempre que cumpla que T(n) <= C f(n) donde C > 0 
	 * Cuando se calcula el Orden los terminos negativos no se toman en cuenta
	 * Entonces --> 2n.cte1 + 5² <= C.n²
	 * Ahora hay que encontrar C que sea constante y mayor a 0.
	 *  --> (2n.cte1 + 5²)/2 = C
	 *  --> (2n.cte1)/n² + (5n²)/n² --> (2cte1)/n + 5 <= C     Si n es grande la funcion se achica.
	 *  Ahora se verifica C si cumple con = C > 0 
	 *  								    n0 = 1 n>=n0 
	 * 										T(n) = O(n²)
	 * BigOh --> T(n) <=C1 f(n) C>0
	 * Entonces--> 2.n.cte2+5.n² <= C.n
	 * 
	 * Regla de la multiplicación --> O(f(x). g(x)) --> El ORDEN es la multiplicación de 
	 * 																		las dos funciones
	 * 								Ej= O(log2n . n²)
	 * 
	 * Regla de la suma --> O(max(f(x). g(x)))
	 * */	
}
