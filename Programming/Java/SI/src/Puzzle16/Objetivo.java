package Puzzle16;

import busqueda.Goal;

public class Objetivo implements Goal<NodoPuzzle>{

    private int[][] Goal_puzzle;
    private int n;

    public static int[][] GoalPuzzle(int n){

        int[][] M = new int[n][n];
        int count = 1;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                M[i][j] = count%(n * n);
                count ++;
            }
        }

        return M;
    }

    @Override
    public boolean isGoal(NodoPuzzle estado) {

        int[][] M = estado.getPuzzle();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) if(M[i][j] != Goal_puzzle[i][j]) return false;
        }
        return true;
    }

    public Objetivo(int n){
        this.n = n;
        Goal_puzzle = GoalPuzzle(n);
    }
}
