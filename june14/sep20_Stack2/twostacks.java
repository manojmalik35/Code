public class twostacks{

    private int tos1;
    private int tos2;
    private int[] arr;

    public twostacks(int cap){
        arr = new int[cap];
        tos1 = -1;
        tos2 = cap;
    }

    private boolean isFull(){
        return tos1 + 1 == tos2;
    }

    private boolean isEmpty1(){
        return tos1 == -1;
    }

    private boolean isEmpty2(){
        return tos2 == arr.length;
    }

    public void push1(int val){
        if(isFull()){
            System.out.println("Stack is full");
            return;
        }

        tos1++;
        arr[tos1] = val;
    }

    public void push2(int val){
        if(isFull()){
            System.out.println("Stack is full");
            return;
        }

        tos2--;
        arr[tos2] = val;
    }

    public void pop1(){
        if(isEmpty1()){
            System.out.println("Stack is empty");
            return;
        }

        arr[tos1] = 0;
        tos1--;
    }

    public void pop2(){
        if(isEmpty2()){
            System.out.println("Stack is empty");
            return;
        }

        arr[tos2] = 0;
        tos2++;
    }

    public int top1(){
        if(isEmpty1()){
            System.out.println("Stack is empty");
            return -1;
        }

        return arr[tos1];
    }

    public int top2(){
        if(isEmpty2()){
            System.out.println("Stack is empty");
            return -1;
        }

        return arr[tos2];
    }

    public static void main(String[] args){
        twostacks st = new twostacks(5);
        st.push1(15);
        st.push2(3);
        st.push1(2);
        st.push2(35);
        st.push1(4);

        while(!st.isEmpty1()){
            System.out.print(st.top1() + " ");
            st.pop1();
        }

        System.out.println();   
        while(!st.isEmpty2()){
            System.out.print(st.top2() + " ");
            st.pop2();
        }

        System.out.println();       
    }
}