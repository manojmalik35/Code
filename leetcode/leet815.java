import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class leet815 {

    public int numBusesToDestination(int[][] routes, int S, int T) {// leetcode 815
        if (S == T) return 0;
        HashMap<Integer, ArrayList<Integer>> sbmap = new HashMap<>();// station bus map
        for (int i = 0; i < routes.length; i++) {
            for (int station : routes[i]) {
                sbmap.putIfAbsent(station, new ArrayList<>());
                sbmap.get(station).add(i);
            }
        }

        boolean[] busVis = new boolean[routes.length];
        HashSet<Integer> stationVis = new HashSet<>();
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.addLast(S);
        stationVis.add(S);
        int count = 0;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int st = que.removeFirst();

                ArrayList<Integer> buses = sbmap.get(st);
                for (int busIdx : buses) {

                    if (busVis[busIdx]) continue;
                    busVis[busIdx] = true;
                    for (int bs : routes[busIdx]) {
                        if (!stationVis.contains(bs)) {
                            stationVis.add(bs);
                            que.addLast(bs);
                            if (bs == T)
                                return count + 1;
                        }
                    }

                }

            }
            count++;
        }

        return -1;
    }
}
