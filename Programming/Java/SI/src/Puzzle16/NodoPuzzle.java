package Puzzle16;

import busqueda.Accion;
import busqueda.Arco;

public class NodoPuzzle {

    private int n;
    private int[][] puzzle;
    private int posx, posy;

    public static void imprimir_Path(NodoPuzzle inicial, Arco<NodoPuzzle> rta){

        imprimir_Matriz(inicial.getPuzzle());

        for(Accion a : rta.getPath()){
            System.out.println();
            inicial = Sucesores.sucesor(inicial, a);
            imprimir_Matriz(inicial.getPuzzle());
        }

        System.out.println("\nNumero de movimientos realizados: " + rta.costoTotal());
    }

    public static void imprimir_Matriz(int[][] M){
        for(int[] x : M){
            for(int y: x) System.out.print(Integer.toString(y, x.length * x.length).toUpperCase() + " ");
            System.out.println();
        }
    }

    public int[][] getPuzzle() {
        int[][] M = new int[n][n];
        System.arraycopy(puzzle, 0, M, 0, n);
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
