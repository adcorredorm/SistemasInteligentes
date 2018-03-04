package Puzzle16;

public class NodoPuzzle {

    private int[][] puzzle;
    private int posx, posy;


    public int[][] getPuzzle() {
        int[][] M = new int[4][4];

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) M[i][j] = puzzle[i][j];
        }

        return M;
    }

    public int[] getPos() {
        return new int[]{posx, posy};
    }

    public NodoPuzzle(int[][] M, int posx, int posy){
        puzzle = M;
        this.posx = posx;
        this.posy = posy;
    }

    public NodoPuzzle(){
        this(Objetivo.Goal_puzzle, 3, 3);
    }

}
