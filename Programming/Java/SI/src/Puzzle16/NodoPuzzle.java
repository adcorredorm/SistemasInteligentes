package Puzzle16;

public class NodoPuzzle {

    private int[][] puzzle;
    private int posx, posy;


    public int[][] getPuzzle() {
        int[][] M = new int[4][4];

        for(int i = 0; i < 4; i++){
            System.arraycopy(puzzle[i], 0, M[i], 0, 4);
        }

        return M;
    }

    public int[] getPos() {
        return new int[]{posx, posy};
    }

    NodoPuzzle(int[][] M, int posx, int posy){
        puzzle = M;
        this.posx = posx;
        this.posy = posy;
    }

    NodoPuzzle(){
        this(Objetivo.Goal_puzzle, 3, 3);
    }

}
