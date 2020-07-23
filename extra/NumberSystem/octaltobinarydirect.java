public class octaltobinarydirect{
    public static void main(String[] args){
        int oct=77;
        int power=1;//1000^0
        int bin=0;
        while(oct!=0){
            int rem=oct%10;
            oct=oct/10;
            switch(rem){
                case 0:
                    rem=0;
                    break;
                case 1:
                    rem=1;
                    break;
                case 2:
                    rem=10;
                    break;
                case 3:
                    rem=11;
                    break;
                case 4:
                    rem=100;
                    break;
                case 5:
                    rem=101;
                    break;
                case 6:
                    rem=110;
                    break;
                case 7:
                    rem=111;
                    break;
            }
            bin+= rem*power;
            power=power*1000;
        }
        System.out.println(bin);
    }
}