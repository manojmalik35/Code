public class octalAddition{
    public static void main(String[] args) {
        int n1=777;
        int n2=1;
        int carry=0,sum=0,power=1;
        while(n1!=0 || n2!=0 || carry!=0){
            int d1=n1%10;
            int d2=n2%10;
            int rem=(d1+d2+carry)%8;
            carry=(d1+d2+carry)/8;
            sum+=rem*power;
            power*=10;
            n1=n1/10;
            n2=n2/10;
        }
        System.out.println(sum);
    }
}