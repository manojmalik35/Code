public class leet949 {
    
    boolean isValid(StringBuilder sb){
        if(sb.charAt(0) > '2') return false;
        if(sb.charAt(0) == '2' && sb.charAt(1) > '3') return false;
        if(sb.charAt(2) > '5') return false;

        return true;
    }

    void permutations(int[] A, int count, StringBuilder sb){//yha pe List<Integer> lene se jyada fayda h
        if(count == A.length){
            if(isValid(sb)){
                int h = (sb.charAt(0) - '0') * 10 + (sb.charAt(1) - '0');
                int min = (sb.charAt(2) - '0') * 10 + (sb.charAt(3) - '0');
                if(h > hour){
                    hour = h;
                    minutes = min;
                }else if(h == hour && min > minutes)
                    minutes = min;
            }
            return;
        }
        
        for(int i = 0; i < A.length; i++){
            if(A[i] != -1){
                int val = A[i];
                A[i] = -1;
                sb.append(val);
                permutations(A, count + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
                A[i] = val;
            }
        }    
    }

    boolean isValid(int[] arr){
        if(arr[0] > 2) return false;
        if(arr[0] == 2 && arr[1] > 3) return false;
        if(arr[2] > 5) return false;

        return true;
    }

    void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    void permutations(int[] arr, int idx){
        if(idx == arr.length - 1){
            if(isValid(arr)){
                int h = arr[0] * 10 + arr[1];
                int min = arr[2] * 10 + arr[3];
                if(h > hour){
                    hour = h;
                    minutes = min;
                }else if(h == hour && min > minutes)
                    minutes = min;
            }
        }
        
        for(int i = idx; i < arr.length; i++){
            swap(arr, idx, i);
            permutations(arr, idx + 1);
            swap(arr, idx, i);
        }
    }
    
    int hour = -1, minutes = -1;
    public String largestTimeFromDigits(int[] A) {
        StringBuilder sb = new StringBuilder();
        permutations(A, 0, sb);
        if(hour == - 1) return "";
        StringBuilder res = new StringBuilder();
        if(hour < 10) res.append("0");
        res.append(hour);
        res.append(":");
        if(minutes < 10) res.append("0");
        res.append(minutes);
        return res.toString();
    }

    boolean isBetter(int[] result, int[] nums){
        if(result == null)
            return true;
        
        for(int i = 0; i < 4; i++){
            if(nums[i] == result[i])
                continue;
            
            if(nums[i] > result[i])
                return true;
            else 
                return false;
        }
        
        return false;
    }

    void permutationsBetter(int[] arr, int idx){
        if(idx == arr.length - 1){
            if(isValid(arr) && isBetter(res, arr)){
                if(res == null) res = new int[4];
                for(int i = 0; i < 4; i++) res[i] = arr[i];
            }
            return;
        }
        
        for(int i = idx; i < arr.length; i++){
            swap(arr, idx, i);
            permutationsBetter(arr, idx + 1);
            swap(arr, idx, i);
        }
    }
    
    int[] res;
    public String largestTimeFromDigitsII(int[] A) {
        permutations(A, 0);
        if(res == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(res[0]).append(res[1]).append(":").append(res[2]).append(res[3]);
        return sb.toString();
    }
}