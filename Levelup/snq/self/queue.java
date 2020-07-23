public class queue{

    protected int[] arr;
    protected int front = 0;
    protected int end = 0;
    protected int size = 0;

    protected void resize(int size){
        this.arr = new int[size];
        this.front = 0;
        this.end = 0;
        this.size = 0;
    }

    protected int maxSize() {
        return this.arr.length;
    }

    queue(){
        resize(10);
    }

    queue(int size){
        resize(size);
    }

    protected void push_(int val){
        this.arr[this.end] = val;
        this.end = (this.end + 1) % this.maxSize();
        this.size++;
    }

    public void push(int val) throws Exception{
        if(this.size == maxSize())
            throw new Exception("QueueIsFull");

        push_(val);
    }

    protected void pop_() {
        arr[front] = 0;
        front = (front + 1) % maxSize();
        size--;        
    }

    public void pop() throws Exception{
        if(size == 0)
            throw new Exception("QueueIsEmpty");
        
        pop_();
    }

    protected int top_(){
        return arr[front];
    }

    public int top() throws Exception{
        if(size == 0)
            throw new Exception("QueueIsEmpty");

        return top_();
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");

        for(int i = 0; i < this.size; i++){
            int idx = (this.front + i) % maxSize();
            sb.append(arr[idx] + ", ");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }
}