package feb23;

public class pdis {

	//pdis(6) =>5 3 1 2 4 6
	//pdis(5) =>5 3 1 2 4
	//pdis(6) =>pdis(5) 6
	public static void main(String[] args) {
		pdis(6);

	}
	
	private static void pdis(int n){
		if(n==0)
			return;
		if(n%2!=0)
			System.out.print(n+" ");
		pdis(n-1);
		if(n%2==0)
			System.out.print(n+" ");
	}

}
