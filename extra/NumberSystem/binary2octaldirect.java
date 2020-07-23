public class binary2octaldirect{
    public static void main(String[] args){
        int bin=10011111;
        int power=1;//10^0
        int oct=0;
        while(bin!=0){
            int rem=bin%1000;
            bin=bin/1000;
            switch(rem){
                case 0:
                    rem=0;
                    break;
                case 1:
                    rem=1;
                    break;
                case 10:
                    rem=2;
                    break;
                case 11:
                    rem=3;
                    break;
                case 100:
                    rem=4;
                    break;
                case 101:
                    rem=5;
                    break;
                case 110:
                    rem=6;
                    break;
                case 111:
                    rem=7;
                    break;
            }
            oct+= rem*power;
            power=power*10;
        }
        System.out.println(oct);
    }
}