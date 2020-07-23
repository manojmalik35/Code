package may7;

public class ownStack {

	class Stack{
		private int[] data;
		private int tos;
		private int capacity;
		Stack(int cap){
			this.data=new int[cap];
			this.tos=-1;
			this.capacity=cap;
		}
		
		void push(int val){
			if(tos==capacity)
				System.out.println("Overflow");
			else{
				tos++;
				data[tos]=val;
			}
		}
		
		int top(){
			if(tos==-1){
				System.out.println("Underflow");
				return -1;
			}
			else
				return data[tos];
		}
		
		void pop(){
			if(tos==-1)
				System.out.println("Underflow");
			else{
				data[tos]=0;
				tos--;
			}
		}
		
		int size(){
			return tos+1;
		}
	}

	public static void main(String[] args) {
		

	}

}
