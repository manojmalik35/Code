package feb23;

public class pI {
	
	//pi(5)=> 1 2 3 4 5
	//pi(4)=> 1 2 3 4
	//pi(5)=> pi(4) 5
	public static void main(String[] args) {
		pi(5);

	}
	
	private static void pi(int n){
		if(n==0)
			return;
		
		pi(n-1);
		System.out.println(n);
	}

}
