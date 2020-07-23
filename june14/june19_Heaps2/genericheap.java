import java.util.*;
public class genericheap<T>{//this way is done in java priority queue
    //public class genericheap<T extends Comparable<T>>{
    private ArrayList<T> list=new ArrayList<>();
    private Comparator<T> comptr=null;
    genericheap(){
    }
    genericheap(Comparator<T> comptr){
        this.comptr=comptr;
    }
    genericheap(T[] arr){
        for(int i=0;i<arr.length;i++)
            list.add(arr[i]);
        for(int i=list.size()-1;i>=0;i--)
            downheapify(i);
    }
    genericheap(T[] arr,Comparator<T> o){
        for(int i=0;i<arr.length;i++)
            list.add(arr[i]);
        for(int i=list.size()-1;i>=0;i--)
            downheapify(i);
    }
    private void swap(int i,int j){
        T ith=list.get(i);
        T jth=list.get(j);
        list.set(i,jth);
        list.set(j,ith);
    }
    //for comparator
    private boolean ishp(int i,int j){//if i has higher priority return true
        if(comptr!=null){
            T ith=list.get(i);
            T jth=list.get(j);
            return comptr.compare(ith, jth)<0;
        }else{
            Comparable<T> ith=(Comparable<T>)list.get(i);
            T jth=list.get(j);
            return ith.compareTo(jth)<0;
        }
    }
    //for comparable
    // private boolean ishp(int i,int j){//if i has higher priority return true
    //     Comparable<T> ith=(Comparable<T>)list.get(i);
    //     //T ith=list.get(i);
    //     T jth=list.get(j);
    //     if(type==true)
    //         return ith.compareTo(jth)<0;
    //     else
    //         return ith.compareTo(jth)>0;
    // }
    private void upheapify(int i){
        if(i==0)
            return;
        int pi=(i-1)/2;
        if(ishp(i,pi)){
            swap(i,pi);
            upheapify(pi);
        }
    }
    private void downheapify(int i){
        int li=2*i+1;
        int ri=2*i+2;
        
        int hpi=i;//highest priority index
        if(li<list.size() && ishp(li,hpi))
            hpi=li;
        if(ri<list.size() && ishp(ri,hpi))
            hpi=ri;
        
        if(hpi!=i){
            swap(i,hpi);
            downheapify(hpi);
        }
    }
    public void add(T val){
        list.add(val);
        upheapify(list.size()-1);
    }
    public T peek(){
        return list.get(0);
    }
    public T remove(){
        T rv=peek();
        swap(0,list.size()-1);
        list.remove(list.size()-1);
        downheapify(0);
        return rv;
    }
    public int size(){
        return list.size();
    }
    static class Student implements Comparable<Student>{
        String name;
        int marks;
        int extra;
        Student(String name,int marks,int extra){
            this.name=name;
            this.marks=marks;
            this.extra=extra;
        }
        void display(){
            System.out.println("["+this.name+" ,"+this.marks+" ,"+this.extra+" ]");
        }
        public int compareTo(Student o){
            return this.name.compareTo(o.name);
        }
    }
    static class StudentMarksComparator implements Comparator<Student>{
        public int compare(Student s1,Student s2){
            return s1.marks-s2.marks;
        }
    }
    static class StudentExtraComparator implements Comparator<Student>{
        public int compare(Student s1,Student s2){
            return s1.extra-s2.extra;
        }
    }
    static class StudentMarksExtraComparator implements Comparator<Student>{
        public int compare(Student s1,Student s2){
            if(s1.marks!=s2.marks)
                return s1.marks-s2.marks;
            else
                return s1.extra-s2.extra;
        }
    }
    static class StudentExtraMarksComparator implements Comparator<Student>{
        public int compare(Student s1,Student s2){
            if(s1.extra!=s2.extra)
                return s1.extra-s2.extra;
            else
                return s1.marks-s2.marks;
        }
    }
    public static void main(String[] args){
        // genericheap<Student> pq=new genericheap<>(new StudentMarksExtraComparator());
        // pq.add(new Student("D",10,2));
        // pq.add(new Student("C",5,5));
        // pq.add(new Student("E",8,8));
        // pq.add(new Student("A",2,10));
        // pq.add(new Student("B",5,2));

        // while(pq.size()>0){
        //     Student val=pq.remove();
        //     val.display();
        // }
        Integer[] arr={2,3,6,8,4,5,1,20,8};
        genericheap<Integer> pq=new genericheap<>(arr);
        while(pq.size()>0){
            int val=pq.remove();
            System.out.print(val+" ");
        }
    }
}