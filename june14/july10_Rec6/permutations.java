import java.util.*;
public class permutations{

    static void printPerm(boolean[] boxes,int co,int to,String ans)//current object and total object
    {
        if(co == to)
        {
            System.out.println(ans);
            return;
        }
        for(int i=0;i<boxes.length;i++)
        {
            if(boxes[i] == false)
            {
                boxes[i] = true;
                printPerm(boxes,co+1,to,ans+"O"+(co+1)+"B"+i+" ");
                boxes[i] = false;
            }
        }
    }
    public static void main(String[] args) {
        boolean[] boxes = new boolean[4];
        printPerm(boxes, 0, 2, "");
    }
}