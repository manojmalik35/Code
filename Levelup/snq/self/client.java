public class client{

    static void simpleStack() throws Exception{
        // stack<Integer> st = new stack<>();
        // for(int i = 0; i < 10; i++)
        //     st.push((int)(Math.random() * 100));
        // System.out.println(st);
        // st.pop();
        // System.out.println(st.top());

        Integer[] arr = new Integer[10];
        for(int i = 0; i < 10; i++)
            arr[i] = (int)(Math.random() * 100);
        dynamicStack ds = new dynamicStack(arr);
        System.out.println(ds);
        ds.pop();
        System.out.println(ds.top());
    }

    static void queue() throws Exception{
        // queue q = new queue(3);
        // q.push(1);
        // q.push(2);
        // q.push(3);
        // q.push(4);
        // System.out.println(q.top());

        dynamicQueue qu = new dynamicQueue(3);
        qu.push(1);
        qu.push(2);
        qu.push(3);
        qu.push(4);
        while(!qu.isEmpty()){
            System.out.println(qu.top());
            qu.pop();
        }
    }
    public static void main(String[] args) throws Exception{
        simpleStack();
        // queue();
    }
}