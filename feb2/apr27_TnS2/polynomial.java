package apr27;

public class polynomial {

	//1.x^n + 2.x^n-1 + 3.x^n-2 +..............+(n-1).x^2 + n.x^1
	public static int solvePolynomial(int x,int n){
		//piche se dekho problem ko
		int sum=0;
		int coff=n;
		int pow=x;
		while(coff>=1){
			sum+=coff*pow;
			coff--;
			pow *= x;
		}
		return sum;
	}
	public static void main(String[] args) {
		System.out.println(solvePolynomial(2, 3));

	}

}
