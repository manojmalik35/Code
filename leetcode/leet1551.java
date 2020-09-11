public class leet1551{

    public int minOperations(int n) {//Brute Force
        int rightM = (n >>> 1);
        int leftM = (n & 1) == 0 ? (n >>> 1) - 1 : rightM;
        
        int i = 0, j = n - 1;
        int eli = (i << 1) + 1;
        int elj = (j << 1) + 1;
        int leftEle = (leftM << 1) + 1;
        int rightEle = (rightM << 1) + 1;
        // System.out.println(leftEle + " " + rightEle + " " + eli + " " + elj);
        int count = 0;
        while(i < leftM && j > rightM){
            while(eli != leftEle && elj != rightEle){
                eli += 1;
                elj -= 1;
                count++;
            }
            i++;
            j--;
            eli = (i << 1) + 1;
            elj = (j << 1) + 1;
        }
        
        if((n & 1) != 0) return count;
        
        return count + (n >>> 1);
    }

    public int minOperationsII(int n) { //O(n)
        
        int goal = n; //jo n given h voi array ka average aayga har element ko usi me convert krna h
        int ops = 0;
        //n/2 tk isliye chalaya kyuki uske baad k elements symmetrically convert ho jate h....
        //mtlb jo aadhe elements humne left half me convert kre vo right half valo me se minus kr k hi kre
        //to unke operations left half valo me hi count ho jayenge
        for(int i = 0; i < n / 2; i++){
            int curr = 2 * i + 1;
            ops += Math.abs(curr - goal);
        }

        return ops;
    }

    public int minOperationsIII(int n) { //O(1)
        
        //Explanation:-
        // if n is odd, suppose n=5.
        // The array is :
        // 1 3 5 7 9.
        // Here, we will have the middle element as 5.
        // We take 2 from 7 and add to 3 to make each one 5.
        // We take 4 from 9 and add to 1 to make each one 5.
        // Total steps: 2+4=6. (sum of first n/2 even numbers)
        // Sum of first k EVEN numbers = AP se nikal skte h
        // = k*(k+1)
        // if n is even, suppose n=6.
        // The array is :
        // 1 3 5 7 9 11.
        // Here, we will have the middle element as (5+7)/2=6.
        // We take 1 from 7 and add to 5 to make each one 6.
        // We take 3 from 9 and add to 3 to make each one 6.
        // We take 5 from 11 and add to 1 to make each one 6.
        // Total steps: 1+3+5=9. (sum of first n/2 odd numbers)
        // Sum of first k ODD numbers = k * k.
        if((n & 1) != 0){
            n >>>= 1;
            return n * (n + 1);
        }

        n >>>= 1;
        return n * n;
    }
}