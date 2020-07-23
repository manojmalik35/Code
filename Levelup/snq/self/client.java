public class client{

    static void simpleStack() throws Exception{
        stack st = new stack(3);
        st.push(10);

        dynamicStack ds = new dynamicStack(3);
        ds.push(10);
        ds.push(20);
        ds.push(30);
        ds.push(40);
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
        // simpleStack();
        queue();
    }
}