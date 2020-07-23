public class insertionsort{
    private static void swap(int[] arr,int i,int j) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public static void main(String[] args) {
        int[] arr={10,2,17,-1,5,3};
        int jc=1;
        while(jc<=arr.length-1){
            for(int i=jc;i>0;i--){
                if(arr[i]<arr[i-1])
                    swap(arr, i, i-1);
                else
                    break;
            }
            jc++;
        }
        for(int val:arr)
            System.out.print(val+" ");
    }
}