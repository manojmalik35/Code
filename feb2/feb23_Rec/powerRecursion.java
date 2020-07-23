package feb23;

public class powerRecursion {

	// Expectation power(5,3) = 5*5*5
	// Faith power(5,2)= 5*5
	// E<-F power(5,3)= 5*power(5,2)
	private static int power(int x, int n) {
		if (n == 0)
			return 1;
		int xnm1 = power(x,n-1);
		int xn=x*xnm1;

		return xn;
	}

	public static void main(String[] args) {
		int n = power(2, 10);
		System.out.println(n);

	}

}
