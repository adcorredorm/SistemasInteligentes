package Puzzle16;

import busqueda.Goal;

public class Objetivo implements Goal<NodoPuzzle>{

    public static final int[][] Goal_puzzle = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};

    @Override
    public boolean isGoal(NodoPuzzle estado) {

        int[][] M = estado.getPuzzle();

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) if(M[i][j] != Goal_puzzle[i][j]) return false;
        }
        return true;
    }
}
