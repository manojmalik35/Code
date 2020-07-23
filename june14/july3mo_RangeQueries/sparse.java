public class sparse{

    static int[][] sparse;
    static int[] logs;
    static int getLog(int n){
        int x=0;
        while((1<<x)<=n)
            x++;
        return x-1;
    }
    static void build(int[] arr){
        logs=new int[arr.length+1];
        for(int i=1;i<logs.length;i++)
            logs[i]=getLog(i);
        sparse=new int[arr.length][logs[arr.length]+1];
        for(int j=0;j<sparse[0].length;j++){
            for(int i=0;i<sparse.length;i++){
                if(j==0)
                    sparse[i][j] = arr[i];
                else{
                    if(i+(1<<j-1)<arr.length)
                        sparse[i][j]=Math.min(sparse[i][j-1],sparse[i+(1<<j-1)][j-1]);
                }
            }
        }
    }
    static int query(int l,int r){
        int n=r-l+1;
        int log=logs[n];
        int segment=(1<<log);
        return Math.min(sparse[l][log],sparse[r-segment+1][log]);
    }
    static int trivialQuery(int[] arr,int l,int r){
        int min=Integer.MAX_VALUE;
        for(int i=l;i<=r;i++)
            min=Math.min(min,arr[i]);
        return min;
    }
    public static void main(String[] args) {
        int[] arr={2,4,-1,6,8,-4,7,12,5,4,-4,3,20,-16,4,11};
        build(arr);
        System.out.println(query(6,9) + " " + trivialQuery(arr,6,9));
        System.out.println(query(2,9) + " " + trivialQuery(arr,2,9));
        System.out.println(query(7,15) + " " + trivialQuery(arr,7,15));
        System.out.println(query(3,12) + " " + trivialQuery(arr,3,12));
    }
}