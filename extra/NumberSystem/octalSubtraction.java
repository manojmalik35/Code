public class octalSubtraction{
    public static void main(String[] args) {
        int n1=1000;
        int n2=1;
        int b=0,power=1,sum=0;
        while(n1!=0 || n2!=0){
            int d1=n1%10;
            int d2=n2%10;
            int rem=0;
            if(d1>d2){
                rem=d1-d2-b;
                b=0;
            }else{
                rem=d1+8-b-d2;
                b=1;
            }
            sum+=rem*power;
            power*=10;
            n1=n1/10;
            n2=n2/10;
        }
        System.out.println(sum);
    }
}