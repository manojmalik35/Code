package feb23;

public class toh {
	//Expectation toh(s,d,h,3)= move 3 disks from s to d with the help of h
	//Faith toh(s,d,h,2) = move 2 disks 
	private static void toh(char s,char d,char h,int n){
		if(n==0)
			return;
		toh(s,h,d,n-1);
		System.out.println(n+""+s+""+d);
		toh(h,d,s,n-1);
	}

	public static void main(String[] args) {
		toh('s','d','h',3);

	}

}
