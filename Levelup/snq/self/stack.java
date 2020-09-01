// import java.lang.ArrayIndexOutOfBoundsException;
public class stack<T>{

    private Object[] arr;
    private int tos;
    private int size;

    protected void resize(int size){
        this.arr = new Object[size];
        this.size = 0;
        this.tos = -1;
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

    protected int maxSize(){
        return this.arr.length;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    protected void push_(T val){
        this.arr[++tos] = val;
        this.size++;
    }

    public void push(T val) throws Exception{
        if(this.size == this.maxSize())
            throw new Exception("Stack is full");
            // throw new java.lang.ArrayIndexOutOfBoundsException("Stack is full");

        push_(val);
    }

    protected T top_(){
        return (T)this.arr[this.tos];
    }

    public T top() throws Exception{
        if(this.size == 0)
            throw new Exception("StackIsEmpty: " + -1);

        return top_();
    }

    protected T pop_(){
        T rval = (T)this.arr[this.tos];
        this.arr[this.tos] = null;
        this.tos--;
        this.size--;
        return rval;
    }

    public T pop() throws Exception{
        if(this.size == 0)
            throw new Exception("StackIsEmpty: " + -1);

        return pop_();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = tos; i >= 0; i--){
            sb.append(this.arr[i]);
            if(i != 0) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}