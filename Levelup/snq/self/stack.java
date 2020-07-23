public class stack{

    private int[] arr;
    private int tos;
    private int size;

    protected void resize(int size){
        this.arr = new int[size];
        this.size = 0;
        this.tos = -1;
    }

    protected int maxSize(){
        return this.arr.length;
    }

    public stack(){
        resize(10);
    }

    public stack(int size){
        resize(size);
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    protected void push_(int val){
        this.arr[++tos] = val;
        this.size++;
    }

    public void push(int val) throws Exception{
        if(this.size == maxSize())
            throw new Exception("StackOverflow");

        push_(val);
    }

    protected int top_(){
        return this.arr[this.tos];
    }

    public int top() throws Exception{
        if(this.size == 0)
            throw new Exception("StackIsEmpty: " + -1);

        return top_();
    }

    protected void pop_(){
        this.arr[this.tos] = 0;
        this.tos--;
        this.size--;
    }

    public void pop() throws Exception{
        if(this.size == 0)
            throw new Exception("StackIsEmpty: " + -1);

        pop_();
    }
}