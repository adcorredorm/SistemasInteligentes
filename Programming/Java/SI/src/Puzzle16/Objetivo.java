package Puzzle16;

import busqueda.Goal;

public class Objetivo implements Goal<NodoPuzzle>{

    private int[] Goal_puzzle;
    private int n;

    public static int[] GoalPuzzle(int n){

        byte[] obj = new byte[16];
        for(byte i = 1; i < 16; i++) obj[i-1] = i;
        int[] goal = new int[4];

        StringBuilder S;
        for(int i = 0; i < 4; i++){
            S = new StringBuilder();
            for(int j = 0; j < 4; j++){
                S.append(NodoPuzzle.toBin(obj[4*i + j], 4));
            }
            goal[i] = NodoPuzzle.castBin(S.toString());
        }
        return goal;
    }

    @Override
    public boolean isGoal(NodoPuzzle estado) {

        int[] V = estado.getPuzzle();
        for(int i = 0; i < n; i++) if(V[i] != Goal_puzzle[i]) return false;
        return true;
    }

    public Objetivo(int n){
        this.n = n;
        Goal_puzzle = GoalPuzzle(n);
    }
}
