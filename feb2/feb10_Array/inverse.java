package feb10;

public class inverse {

	public static void main(String[] args) {
		int[] arr={4,1,2,0,3};
		int n=arr.length;
		int[] inv=new int[n];
		
		for(int i=0;i<n;i++){
			inv[arr[i]]=i;
		}
		for(int i=0;i<n;i++){
			System.out.println(inv[i]);
		}
		

	}

}
