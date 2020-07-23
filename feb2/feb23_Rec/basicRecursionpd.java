package feb23;

public class basicRecursionpd {

	//Expectation - pd(5)=> 5 4 3 2 1
	//Faith - pd(4)=> 4 3 2 1
	//Expectaion <- Faith - pd(5) = 5 pd(4)
	public static void main(String[] args) {
		pd(5);

	}
	
	private static void pd(int n){
		if(n==0)
			return;
		
		System.out.println(n);
		pd(n-1);
	}
}
