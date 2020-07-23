package apr24;

public class twoHeapSort {

	private static int get(int[] a,int[] b,int i){
		if(i<a.length)
			return a[i];
		else
			return b[i-a.length];
	}
	private static void set(int[] a,int[] b,int i,int val){
		if(i<a.length)
			a[i]=val;
		else
			b[i-a.length]=val;
	}
	private static void swap(int[] a,int[] b,int i,int j){
			int ith=get(a, b, i);
			int jth=get(a, b, j);
			set(a, b, i, jth);
			set(a, b, j, ith);
	}
	private static void downheapify(int[] a,int[] b,int index,int sz){//sz represents kitne size ko downheapify krna h or index is kaha se krna h
		int maxind=index;
		int lci=2*index+1;
		int rci=2*index+2;
		if(lci<sz && get(a, b, lci)>get(a,b,maxind))
			maxind=lci;
		if(rci<sz && get(a, b, rci)>get(a,b,maxind))
			maxind=rci;
		if(maxind!=index){
			swap(a,b,maxind,index);
			downheapify(a,b,maxind,sz);
		}
	}
	public static void sortTwoArrays(int[] a,int[] b){
		int n=a.length+b.length;
		for(int i=(n/2)-1;i>=0;i--)
			downheapify(a, b, i, n);
		
		int cse=0;
		while(cse<n-1){
			swap(a,b,0,n-1-cse);
			cse++;
			downheapify(a, b, 0, n-cse);
		}
	}
	public static void main(String[] args) {
		int[] a={5,19,3};
		int[] b={0,16,12,4,-4};
		sortTwoArrays(a, b);
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		for(int i=0;i<b.length;i++)
			System.out.print(b[i]+" ");

	}

}
