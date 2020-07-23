public class ownstack{
    
    private int tos;
    private int[] arr;

    public ownstack(int cap){
        tos = -1;
        arr = new int[cap];
    }

    public void push(int val){
        if(tos == arr.length - 1){
            System.out.println("Stack is full");
            return;
        }

        tos++;
        arr[tos] = val;
    }

    public void pop(){
        if(tos == -1){
            System.out.println("Stack is empty");
            return;
        }

        arr[tos] = 0;
        tos--;
    }

    public int top(){
        if(tos == -1){
            System.out.println("Stack is empty");
            return -1;
        }

        return arr[tos];
    }

    public int size(){
        return tos + 1;
    }

    public static void main(String[] args){
        ownstack st = new ownstack(5);

        st.push(50);
        st.push(30);
        st.push(57);
        st.push(5);
        st.push(500);
        st.push(50);

        while(st.size() > 0){
            System.out.println(st.top());
            st.pop();
        }
    }
}