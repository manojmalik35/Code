import java.util.Arrays;

public class tns3{

    static int[] countSort(int[] input){
        int n = input.length;
        int r = 10;//maan k chl re h
        int[] fm = new int[r];
        for(int val : input)
            fm[val]++;

        for(int i = 1; i < fm.length; i++)
            fm[i] += fm[i - 1];

        int[] output = new int[n];
        for(int i = input.length - 1; i >= 0; i--){
            int val = input[i];
            fm[val]--;
            output[fm[val]] = val;
        }
        return output;
    }

    static int[] countSortforRS(int[] input, int div){
        int n = input.length;
        int r = 10;//maan k chl re h
        int[] fm = new int[r];
        for(int val : input){
            val = val / div % 10;
            fm[val]++;
        }

        for(int i = 1; i < fm.length; i++)
            fm[i] += fm[i - 1];

        int[] output = new int[n];
        for(int i = input.length - 1; i >= 0; i--){
            int val = input[i];
            val = val / div % 10;
            fm[val]--;
            output[fm[val]] = input[i];
        }
        return output;
    }

    static int[] radixSort(int[] input){
        int max = 0;//assume all numbers positive
        for(int val : input)
            max = Math.max(max, val);

        int div = 1;
        while(max / div > 0){
            input = countSortforRS(input, div);
            div = div * 10;
        }
        return input;
    }

    public static void main(String[] args) {
        // int[] arr = {4, 1, 2, 7, 4, 9, 1, 8, 0, 2, 1, 5, 0, 8};
        // int[] sa = countSort(arr);
        int[] arr = {576, 282, 77, 34, 981, 62, 11, 348, 7, 412, 69, 438, 992, 324, 287, 76};
        int[] sa = radixSort(arr);
        System.out.println(Arrays.toString(sa));
    }
}