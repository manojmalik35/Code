public class dynamicQueue extends queue{

    public dynamicQueue(){
        resize(5);
    }

    public dynamicQueue(int size){
        resize(size);
    }

    @Override
    public void push(int val){
        if(this.size() == maxSize()){
            int[] temp = new int[this.size()];
            for(int i = 0; i < temp.length; i++){
                temp[i] = top_();
                pop_();
            }

            resize(2 * temp.length);
            for(int i = 0; i < temp.length; i++)
                push_(temp[i]);
        }

        push_(val);
    }
}