package feb23;

public class factorialRecursion {

	
	//Expectation fac(5)=5*4*3*2*1
	//Faith fac(4)=4*3*2*1
	// E <- F fac(5)=5* fac(4)
	private static int factorial(int n){
		if(n==0)
			return 1;
		int result=factorial(n-1);
		return result*n;
		
	}
	
	public static void main(String[] args) {
		int n=factorial(4);
		System.out.println(n);

	}

}
