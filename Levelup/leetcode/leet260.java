public class leet260{
    public int[] singleNumber(int[] nums) {
        int[] arr = new int[2];
        int xor = 0;
        for(int no : nums)
            xor ^= no;
        
        int rmsb = 1; //right most set bitmask
        while((rmsb & xor) == 0)
            rmsb = (rmsb << 1);
        
        for(int no : nums){
            if((rmsb & no) == 0)
                arr[0] ^= no;
            else
                arr[1] ^= no;
        }
         
        return arr;
    }
}