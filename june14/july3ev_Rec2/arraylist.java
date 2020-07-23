import java.util.*;
public class arraylist{
    static void printRotations(ArrayList<Integer> list,int k,int pti){
        if(k==0)
            return;
        int t=list.remove(list.size()-1);
        list.add(pti,t);
        System.out.println(list);
        printRotations(list, k-1,pti+1);
    }
    static void removeDuplicates(ArrayList<Integer> list,int ep){
        // int i=list.size()-2;
        // while(i>=0){
        //     if(list.get(i)==list.get(i+1))
        //         list.remove(i+1);
        //     i--;
        // }
        if(ep<0)
            return;
        if(list.get(ep)==list.get(ep+1))
            list.remove(ep+1);
        removeDuplicates(list, ep-1);
    }
    static void sumofDuplicates(ArrayList<Integer> list){
        int i=list.size()-2,sum=0;
        while(i>=0){
            if(list.get(i)==list.get(i+1)){
                sum += list.remove(i+1);
            }else{
                int val=list.get(i+1);
                val+=sum;
                list.set(i+1,val);
                sum=0;
            }
            i--;
        }
        int val=list.get(0);
        val+=sum;
        list.set(0,val);
    }
    public static void main(String[] args) {
        // ArrayList<Integer> list=new ArrayList<>(Arrays.asList(10,20,30,40,50,60,70,80));
        // printRotations(list, 3,0);
        ArrayList<Integer> list=new ArrayList<>(Arrays.asList(2,2,2,3,3,2,2,2,5,6,6,7,7,7,8,8));
        // removeDuplicates(list,list.size()-2);
        sumofDuplicates(list);
        System.out.println(list);
    }
}