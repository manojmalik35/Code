package feb23;

public class pdi {

	//pdi(5) =>5 4 3 2 1 1 2 3 4 5
	//pdi(4) =>4 3 2 1 1 2 3 4
	//pdi(5) =>5 pdi(4) 5
	public static void main(String[] args) {
		pdi(5);

	}
	
	private static void pdi(int n){
		if(n==0)
			return;
		System.out.print(n+" ");
		pdi(n-1);
		System.out.print(n+" ");
	}

}
