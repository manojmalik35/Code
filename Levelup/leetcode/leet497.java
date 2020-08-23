import java.util.*;
public class leet497 {

    class Solution {

        int[][] rects;
        Random r;
        TreeMap<Integer, Integer> map; //(no. of integral points, rectangle index)
        int sum = 0;
        public Solution(int[][] rects) {
            this.rects = rects;
            this.r = new Random();
            this.map = new TreeMap<>();
            for(int i = 0; i < rects.length; i++){
                int width = rects[i][2] - rects[i][0] + 1;//+1 bcoz we have to include edges
                int height = rects[i][3] - rects[i][1] + 1;

                int totalNoIntegralPoints = width * height;
                sum += totalNoIntegralPoints;
                map.put(sum, i);
            }
        }

        public int[] pick() {
            int rand = r.nextInt(sum) + 1;
            int cf = map.ceilingKey(rand);
            int rectIdx = map.get(cf);
            int[] rect = rects[rectIdx];
            int width = rect[2] - rect[0] + 1;//+1 bcoz we have to include edges
            int height = rect[3] - rect[1] + 1;

            int x = rect[0] + r.nextInt(width);
            int y = rect[1] + r.nextInt(height);

            return new int[]{x, y};
        }
    }

    class SolutionII {

        Random r;
        int[][] rects;
        int[] cf;
        int sum = 0;
        public SolutionII(int[][] rects) {
            this.rects = rects;
            this.cf = new int[rects.length];
            this.r = new Random();
            for(int i = 0; i < rects.length; i++){
                int width = rects[i][2] - rects[i][0] + 1;//+1 bcoz we have to include edges
                int height = rects[i][3] - rects[i][1] + 1;

                sum += width * height;
                cf[i] = sum;
            }
        }

        public int[] pick() {
            int rand = r.nextInt(sum) + 1;
            
            int rectIdx = 0;
            //Binary Search
            int lo = 0, hi = cf.length - 1;
            while(lo < hi){
                int mid = (lo + hi) >> 1;
                if(cf[mid] == rand){
                    lo = mid;
                    break;
                }else if(cf[mid] < rand)
                    lo = mid + 1;
                else
                    hi = mid;
            }
            rectIdx = lo;
            int[] rect = rects[rectIdx];
            int width = rect[2] - rect[0] + 1;//+1 bcoz we have to include edges
            int height = rect[3] - rect[1] + 1;

            int x = rect[0] + r.nextInt(width);
            int y = rect[1] + r.nextInt(height);

            return new int[]{x, y};
        }
    }

    class SolutionIII {

        Random r;
        TreeMap<Integer, int[]> map;
        int sum = 0;
        public SolutionIII(int[][] rects) {
            this.map = new TreeMap<>();
            this.r = new Random();
            for(int i = 0; i < rects.length; i++){
                int[] rect = rects[i];
                int width = rect[2] - rect[0] + 1;//+1 bcoz we have to include edges
                int height = rect[3] - rect[1] + 1;

                sum += width * height;
                map.put(sum, rect);
            }
        }

        public int[] pick() {
            int rand = r.nextInt(sum) + 1;
            
            int key = map.ceilingKey(rand);
            int[] rect = map.get(key);
            int width = rect[2] - rect[0] + 1;//+1 bcoz we have to include edges
            int height = rect[3] - rect[1] + 1;

            int x = rect[0] + r.nextInt(width);
            int y = rect[1] + r.nextInt(height);

            return new int[]{x, y};
        }
    }    

}