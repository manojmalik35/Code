package may10;

public class kstacks {

	static class kstack{
		private int[] da;
		private int[] sa;
		private int[] pa;
		private int free;
		kstack(int k,int n){
			da=new int[n];
			sa=new int[k+1];
			pa=new int[n];
			free=0;
			for(int i=0;i<pa.length-1;i++)
				pa[i]=i+1;
			pa[pa.length-1]=-1;
			for(int i=0;i<sa.length;i++)
				sa[i]=-1;
		}
		
		void push(int ano,int val){
			if(free==-1)
				System.out.println("Overflow");
			else{
				int ofree=free;
				int nfree=pa[free];
				free=nfree;
				pa[ofree] = sa[ano];
				sa[ano] = ofree;
				da[ofree] = val;
			}
		}
		void pop(int ano){
			if(sa[ano]==-1)
				System.out.println("Underflow");
			int top=sa[ano];
			sa[ano]=pa[top];
			pa[top] = free;
			free=top;
			da[free]=0;
		}
		int top(int ano){
			if(sa[ano]==-1){
				System.out.println("Underflow");
				return 0;
			}
			else
				return da[sa[ano]];
		}
	
		boolean isEmpty(int ano){
			return sa[ano]==-1;
		}
		boolean ifFull(){
			return free==-1;
		}
	}
	public static void main(String[] args) {
		kstack st=new kstack(4, 10);
		st.push(1,10);
		st.push(2,20);
		st.push(1,30);
		st.push(1,40);
		st.push(3,50);
		st.push(3,60);
		st.push(1,70);
		st.push(4,80);
		st.push(1,90);
		st.push(4,100);
		st.push(2,110);
		for(int i=1;i<=4;i++){
			System.out.print("s"+i+" -> ");
			while(st.isEmpty(i) == false){
				int val=st.top(i);
				st.pop(i);
				System.out.print(val+" ");
			}
			System.out.println();
		}
		

	}

}
