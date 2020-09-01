public class dynamicStack<T> extends stack<T>{

    public dynamicStack(){
        super();
    }

    public dynamicStack(int size){
        super(size);
    }
    
    public dynamicStack(Object[] arr){
        super(arr.length * 2);
        for(int i = 0; i < arr.length; i++)
            push((T)arr[i]);
    }

    @Override
    public void push(T val){
        if(super.size() == super.maxSize()){
            Object[] temp = new Object[super.size()];
            for(int i = temp.length - 1; i >= 0; i--){
                temp[i] = top_();
                pop_();
            }

            resize(2 * temp.length);
            for(int i = 0; i < temp.length; i++)
                push_((T)temp[i]);
        }

        push_(val);// super.push(val);
    }
}