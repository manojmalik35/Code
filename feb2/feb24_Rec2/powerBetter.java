package feb24;

public class powerBetter {

	// Expectation power(5,6) = 5*5*5*5*5*5
	// Faith power(5,3)= 5*5*5
	// E<-F power(5,6)= power(5,3)*power(5,3)
	private static int power(int x,int n){
		if(n==0)
			return 1;
		int xnby2=power(x,n/2);
		int xn=1;
		if(n%2==0)
			xn=xnby2*xnby2;
		else
			xn=xnby2*xnby2*x;
		return xn;
	}
	public static void main(String[] args) {
		System.out.println(power(2,11));

	}

}
