public class bubblesort{
    private static void swap(int[] arr,int i,int j) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public static void main(String[] args) {
        int[] arr={10,2,-1,7,5};

        int jc=1;//journey count
        while(jc<=arr.length-1){
            for(int i=0;i<arr.length-jc;i++){
                if(arr[i] > arr[i+1])
                    swap(arr,i,i+1);
            }

            jc++;
        }
        for(int val:arr)
            System.out.print(val+" ");
    }
}