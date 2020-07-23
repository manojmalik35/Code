package mar2;

public class printStairPath {

	public static void psp(int n,String psf){
		if(n==0){
			System.out.print(psf+" ");
			return;
		}
		
		psp(n-1,psf+'1');
		if(n>=2)
			psp(n-2,psf+'2');
		if(n>=3)
			psp(n-3,psf+'3');
	}
	
	public static void main(String[] args) {
		psp(4,"");

	}

}
