public class kstacks{

    private int[] da;
    private int[] na;
    private int[] topa;
    private int free;

    public kstacks(int k, int n){
        da = new int[n];
        na = new int[n];
        topa = new int[k];
        free = 0;

        na[n - 1] = -1;
        for(int i = 0; i < n - 1; i++)
            na[i] = i + 1;

        for(int i = 0; i < topa.length; i++)
            topa[i] = -1;
    }


    private boolean isFull(){
        return free == -1;
    }

    private boolean isEmpty(int k){
        return topa[k] == -1;
    }

    public void push(int k, int val){
        if(isFull()){
            System.out.println("Stack is full");
            return;
        }

        //removeFirst from free
        int temp = free;
        free = na[free];
        //addFirst to stack
        da[temp] = val;
        na[temp] = topa[k];
        topa[k] = temp;
    }

    public void pop(int k){
        if(isEmpty(k)){
            System.out.println("Stack is empty");
            return;
        }

        //removeFirst from stack
        int t = topa[k];
        topa[k] = na[t];
        //addFirst to free
        na[t] = free;
        free = t;
        da[free] = 0;
    }

    public int top(int k){
        if(isEmpty(k)){
            System.out.println("Stack is empty");
            return -1;
        }

        int t = topa[k];
        return da[t];
    }

    public static void main(String[] args) {
        kstacks st=new kstacks(4, 10);
		st.push(0,10);
		st.push(1,20);
		st.push(0,30);
		st.push(0,40);
		st.push(2,50);
		st.push(2,60);
		st.push(0,70);
		st.push(3,80);
		st.push(0,90);
		st.push(3,100);
		st.push(1,110);
		for(int i=0;i<4;i++){
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