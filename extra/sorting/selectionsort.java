public class selectionsort{
    private static void swap(int[] arr,int i,int j) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public static void main(String[] args) {
        int[] arr={10,2,-1,7,5};

        int jc=1;//journey count
        while(jc<=arr.length-1){
            for(int i=jc;i<arr.length;i++){
                if(arr[i]<arr[jc-1])
                    swap(arr,i,jc-1);
            }
            jc++;
        }
        for(int val:arr)
            System.out.print(val+" ");
    }
}