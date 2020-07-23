package may10;
import java.util.*;
public class minStack {

	class minstack{
		private Stack<Integer> st;
		private int min;
		
		void push(int val){
			if(st.size()==0){
				st.push(val);
				min=val;
			}else if(val>=min)
				st.push(val);
			else{
				st.push(2*val-min);
				min=val;
			}
		}
		void pop(){
			if(st.peek()>=min)
				st.pop();
			else{
				int ctop=st.peek();
				st.pop();
				min=2*min-ctop;
			}
		}
		int top(){
			if(st.peek()>=min)
				return st.peek();
			else
				return min;
		}
		int getMin(){
			return min;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
