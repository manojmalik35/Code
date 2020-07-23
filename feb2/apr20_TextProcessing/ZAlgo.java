package apr20;

public class ZAlgo {
//Pattern Matching
	private static void populateZArray(String cat,int[] z){
		int i=1;
		int l=0,r=0;
		while(i<z.length){
			if(i>r){
				l=r=i;
				while(r<z.length && cat.charAt(r) == cat.charAt(r-l))
					r++;
				r--;
				z[l]=r-l+1;
			}else{
				int k=i-l;
				if(z[k]<r-i+1)
					z[i]=z[k];
				else{
					l=i;
					while(r<z.length && cat.charAt(r) == cat.charAt(r-l))
						r++;
					r--;
					z[l]=r-l+1;
				}
			}
			i++;
		}
	}
	public static void patternMatching(String src,String pat){
		String cat=pat+"$"+src;
		int[] z=new int[cat.length()];
		populateZArray(cat, z);
		for(int i=0;i<z.length;i++){
			if(z[i]==pat.length())
				System.out.print(i+" ");
		}
	}
	public static void main(String[] args) {
		String pat="abb";
		String src="abbcdffdkabfdfjdkfabbadfrjabbarijfjabbaaabbabbabb";
		patternMatching(src, pat);
	}

}
