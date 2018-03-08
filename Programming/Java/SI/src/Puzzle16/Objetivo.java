package Puzzle16;

import busqueda.Goal;

public class Objetivo implements Goal<NodoPuzzle>{

    private long Goal_Puzzle;

    public static long GoalPuzzle(int n){

        byte[] obj = new byte[n * n];
        for(byte i = 1; i < n * n; i++) obj[i-1] = i;

        StringBuilder S = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                S.append(NodoPuzzle.toBin(obj[n*i + j], 4));
            }
        }
        return NodoPuzzle.castBin(S.toString());
    }

    @Override
    public boolean isGoal(NodoPuzzle estado) {
        return estado.getPuzzle() == Goal_Puzzle;
    }

    public Objetivo(int n){
        Goal_Puzzle = GoalPuzzle(n);
    }
}
