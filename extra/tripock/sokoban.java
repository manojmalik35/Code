import java.util.*;
public class sokoban{



    static void findBox(){
        
    }


    public static void main(String[] args) {
        char[][] game = {"###########".toCharArray(),
                         "#T##......#".toCharArray(),
                         "#.#.#..####".toCharArray(),
                         "#....B....#".toCharArray(),
                         "#.######..#".toCharArray(),
                         "#.....S...#".toCharArray(),
                         "###########".toCharArray()};

        for(int i = 0; i < game.length; i++){
            for(int j = 0; j < game[0].length; j++)
                System.out.print(game[i][j]);
            System.out.println();
        }
    }
}