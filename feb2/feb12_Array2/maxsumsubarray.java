package feb12;

public class maxsumsubarray {
//Kadanes Algo
	public static void main(String[] args) {
		int[] ar = { 2, 3, 6, -1, -12, 3, -4, 5, 19 };
		//ye h madam vli bakchodi
		int n = ar.length;
		int sum = ar[0];
		int max = ar[0];

		int cstart = 0;
		int cend = 0;
		int mstart = 0;
		int mend = 0;

		for (int i = 1; i < n; i++) {
			if (sum>=0) {
				sum += ar[i];
				cend = i;
			} else {
				sum = ar[i];
				cstart = i;
				cend = i;
			}

			if (sum > max) {
				max = sum;
				mstart = cstart;
				mend = cend;
			}
		}
		System.out.println(sum);
		for(int i=mstart;i<=mend;i++)
			System.out.print(ar[i]+" ");
		
		
//		int csum=ar[0];
//        int osum=ar[0];
//        for(int i=1;i<ar.length;i++){
//        	if(csum<0)
//        		csum=ar[i];
//        	else
//        		csum+=ar[i];
//        	
//        	if(csum>osum)
//        		osum=csum;
//        }
//        System.out.println(osum);
	}

}
