
public class ejercicio1 {
	
	public static void recorrerFor(int A, int B) {
		for(int i= A; i<=B; i++) {
			System.out.println(i);
		}
	}
	
	public static void recorrerWhile(int A, int B) {
		int i = A;
		while(i<=B) {
			i++;
			System.out.println(i);
		}
	}
	
	public static void recorrerRecursivo(int A, int B) {
		if (A<B)
			System.out.println(A);
		recorrerRecursivo(A++, B);
	}
	
	public static void main(String[] args) {
		int A = 1;
		int B = 10;
		recorrerFor(A,B);
		recorrerWhile(A,B);
		recorrerRecursivo(A,B);
	}

}
