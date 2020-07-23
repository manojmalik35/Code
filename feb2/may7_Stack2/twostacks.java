package may7;

public class twostacks {

	static class stack12{
		private int[] data;
		private int tos1;
		private int tos2;
		private int capacity;
		stack12(int cap){
			this.data=new int[cap];
			this.tos1=-1;
			this.tos2=cap;
			this.capacity=cap;
		}
		
		void push1(int val){
			if(tos2-tos1==1)
				System.out.println("Overflow");
			else{
				tos1++;
				data[tos1]=val;
			}
		}
		
		void push2(int val){
			if(tos2-tos1==1)
				System.out.println("Underflow");
			else{
				tos2--;
				data[tos2]=val;
			}
		}
		
		int top1(){
			if(tos1==-1){
				System.out.println("Underflow");
				return -1;
			}else
				return data[tos1];
		}
		
		int top2(){
			if(tos2==capacity){
				System.out.println("Underflow");
				return -1;
			}else
				return data[tos2];
		}
		
		void pop1(){
			if(tos1==-1)
				System.out.println("Underflow");
			else{
				data[tos1]=0;
				tos1--;
			}
		}
		
		void pop2(){
			if(tos2==capacity)
				System.out.println("Underflow");
			else{
				data[tos2]=0;
				tos2++;
			}
		}
		
		int size1(){
			return tos1+1;
		}
		int size2(){
			return capacity-tos2;
		}
	}
	public static void main(String[] args) {
		stack12 st=new stack12(10);
		st.push1(5);
		st.push2(78);
		st.push1(6);
		st.push1(23);
		st.push2(32);
		while(st.size1()>0){
			System.out.println(st.top1());
			st.pop1();
		}
		System.out.println("==============");
		while(st.size2()>0){
			System.out.println(st.top2());
			st.pop2();
		}
		
		
			
	}

}
