public class leet1007 {
    
    public int minDominoRotations(int[] A, int[] B) {//leetcode 1007
        int AA0 = 0, AB0 = 0, BA0 = 0, BB0 = 0;
        int n = A.length;
        for(int i = 0; i < n; i++){
            //1st variable
            if(AA0 != (int)1e8){
                if(A[i] == A[0]){}
                else if(B[i] == A[0])
                    AA0++;
                else
                    AA0 = (int)1e8;
            }

            //2nd variable
            if(AB0 != (int)1e8){
                if(A[i] == B[0]){}
                else if(B[i] == B[0])
                    AB0++;
                else
                    AB0 = (int)1e8;
            }

            //3rd variable
            if(BA0 != (int)1e8){
                if(B[i] == A[0]){}
                else if(A[i] == A[0])
                    BA0++;
                else
                    BA0 = (int)1e8;
            }

            //4th variable
            if(BB0 != (int)1e8){
                if(B[i] == B[0]){}
                else if(A[i] == B[0])
                    BB0++;
                else
                    BB0 = (int)1e8;
            }
        }

        int ans = Math.min(Math.min(AA0, AB0), Math.min(BA0, BB0));
        return ans == (int)1e8 ? -1 : ans;
    }
}
