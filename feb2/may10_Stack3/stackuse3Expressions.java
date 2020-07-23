package may10;
import java.util.*;
public class stackuse3Expressions {

	public static void infixToPostfix(String exp){
		Stack<Character> st=new Stack<>();
		HashMap<Character, Integer> pmap=new HashMap<>();
		pmap.put('+', 0);
		pmap.put('-', 0);
		pmap.put('*', 1);
		pmap.put('/', 1);
		pmap.put('^', 2);
		String ans="";
		for(int i=0;i<exp.length();i++){
			char c=exp.charAt(i);
			if(c>='a' && c<='z')
				ans+=c;
			else if(c=='(')
				st.push(c);
			else if(c==')'){
				while(st.peek()!='('){
					char val=st.pop();
					ans+=val;
				}
				st.pop();
			}
			else{
				while(!(st.size()==0 || st.peek()=='(' || pmap.get(c)>pmap.get(st.peek()))){
					char val=st.pop();
					ans+=val;
				}
				st.push(c);
			}
			
		}
		while(st.size()>0){
			char t=st.pop();
			ans+=t;
		}
		System.out.println(ans);
	}
	public static int evaluatePostfix(String exp){
		Stack<Integer> st=new Stack<>();
		for(int i=0;i<exp.length();i++){
			char c=exp.charAt(i);
			if(c>='0' && c<='9')
				st.push(c-'0');
			else{
				int val1=st.pop();
				int val2=st.pop();
				
				st.push(eval(val2,val1,c));
			}
		}
		return st.peek();
	}
	public static void postfixToInfix(String exp){
		Stack<String> st=new Stack<>();
		for(int i=0;i<exp.length();i++){
			char c=exp.charAt(i);
			if(c>='a' && c<='z')
				st.push(c+"");
			else{
				String val1=st.pop();
				String val2=st.pop();
				
				String val="("+val2+c+val1+")";
				st.push(val);
			}
		}
		System.out.println(st.peek());
	}
	public static void postfixToPrefix(String exp){
		Stack<String> st=new Stack<>();
		for(int i=0;i<exp.length();i++){
			char c=exp.charAt(i);
			if(c>='a' && c<='z')
				st.push(c+"");
			else{
				String val1=st.pop();
				String val2=st.pop();
				
				String val=c+val2+val1;
				st.push(val);
			}
		}
		System.out.println(st.peek());
	}
	public static void prefixToAll(String exp){
		Stack<Integer> st=new Stack<>();
		Stack<String> ist=new Stack<>();
		Stack<String> pst=new Stack<>();
		for(int i=exp.length()-1;i>=0;i--){
			char c=exp.charAt(i);
			if(c>='0' && c<='9'){
				st.push(c-'0');
				ist.push(c+"");
				pst.push(c+"");
			}
			else{
				int val1=st.pop();
				int val2=st.pop();
				String ival1=ist.pop();
				String ival2=ist.pop();
				String pval1=pst.pop();
				String pval2=pst.pop();
				
				st.push(eval(val1,val2,c));
				String ival="("+ival1+c+ival2+")";
				String pval=pval1+pval2+c;
				ist.push(ival);
				pst.push(pval);
			}
		}
		System.out.println(st.peek());
		System.out.println(ist.peek());
		System.out.println(pst.peek());
	
	}
	private static int eval(int v1,int v2,char op){
//		if(op=='+')
//			return v1+v2;
//		else if(op=='-')
//			return v1-v2;
//		else if(op=='*')
//			return v1*v2;
//		else if(op=='/')
//			return v1/v2;
//		else if(op=='^'){
//			int t=(int)Math.pow(v1, v2);
//			return t;
//		}
		int v=0;
		switch(op){
		case '+' :
			v=v1+v2;
			break;
		case '-' :
			v=v1-v2;
			break;
		case '*' :
			v=v1*v2;
			break;
		case '/' :
			v=v1/v2;
			break;
		case '^' :
			v = (int)Math.pow(v1, v2);
			break;
		}
		return v;
	}
	public static void infixEval(String exp){
		Stack<Integer> vs=new Stack<>();
		Stack<Character> os=new Stack<>();
		HashMap<Character, Integer> pmap=new HashMap<>();
		pmap.put('+', 0);
		pmap.put('-', 0);
		pmap.put('*', 1);
		pmap.put('/', 1);
		pmap.put('^', 2);
		for(int i=0;i<exp.length();i++){
			char c=exp.charAt(i);
			if(c>='0' && c<='9')
				vs.push(c-'0');
			else if(c=='(')
				os.push(c);
			else if(c==')'){
				while(os.peek()!='('){
					char op=os.pop();
					int v1=vs.pop();
					int v2=vs.pop();
					vs.push(eval(v2,v1,op));
				}
				os.pop();
			}
			else{
				while(!(os.size()==0 || os.peek()=='(' || pmap.get(c)>pmap.get(os.peek()))){
					char op=os.pop();
					int v1=vs.pop();
					int v2=vs.pop();
					vs.push(eval(v2,v1,op));
				}
				os.push(c);
			}
		}
		while(os.size()>0){
			char t=os.pop();
			int v1=vs.pop();
			int v2=vs.pop();
			vs.push(eval(v2,v1,t));
		}
		System.out.println(vs.peek());
	}
	public static void infixToPrefix(String exp){
		//Reverse the inorder
		
	}
	public static void main(String[] args) {
//		String exp="a+b*c-d/e+(f*(g*h-i+j))";
//		infixToPostfix(exp);
//		String exp="82-3/2131+*^+";
//		System.out.println(evaluatePostfix(exp));
//		postfixToInfix("ab-c/defg+*^+");
//		postfixToPrefix("ab-c/defg+*^+");
//		prefixToAll("+/-823^2*1+31");
		infixEval("8-2/3+2^1*(1+3)");
//		infixEval("(((8-2)/3)+(3^(1*(2+4))))");
	}

}
