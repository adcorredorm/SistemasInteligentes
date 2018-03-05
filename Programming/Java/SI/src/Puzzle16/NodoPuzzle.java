package Puzzle16;

import java.util.Arrays;

public class NodoPuzzle {

    private int n;
    private int[][] puzzle;
    private int posx, posy;


    public int[][] getPuzzle() {
        int[][] M = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) M[i][j] = puzzle[i][j];
        }

        return M;
    }

    public int[] getPos() {
        return new int[]{posx, posy};
    }

    public int n(){ return n;}

    public NodoPuzzle(int n, int[][] M, int posx, int posy){
        this.n = n;
        puzzle = M;
        this.posx = posx;
        this.posy = posy;
    }

    public NodoPuzzle(int n){
        this(n, Objetivo.GoalPuzzle(n), n-1, n-1);
    }

}
