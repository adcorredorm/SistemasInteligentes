package Puzzle16;

import busqueda.Accion;
import busqueda.Arco;

public class NodoPuzzle {

    private int n;
    private int[] puzzle;
    private int posf, posc;

    public static String toBin(int num, int len){
        StringBuilder bin = new StringBuilder(Integer.toString(num, 2));
        while(bin.length() < len) bin.insert(0, '0');
        return bin.toString();
    }

    public static int castBin(String bin){
        int cant = 0;
        for(int i = 0; i < bin.length(); i++){
            if(bin.charAt(i) == '1') cant += Math.pow(2, bin.length()-i-1);
        }
        return cant;
    }

    public static void imprimir_Path(NodoPuzzle inicial, Arco<NodoPuzzle> rta){

        //imprimir_Matriz(inicial.getPuzzle());

        for(Accion a : rta.getPath()){
            System.out.println();
            inicial = Sucesores.sucesor(inicial, a);
          //  imprimir_Matriz(inicial.getPuzzle());
        }

        System.out.println("\nNumero de movimientos realizados: " + rta.costoTotal());
    }

    public byte[][] getMatriz(){
        byte[][] M = new byte[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                M[i][j] = (byte)castBin(toBin(puzzle[i],16).substring(j*n,j*n +n));
            }
        }
        return M;
    }

    public int[] getPuzzle() {
        int[] V = new int[n];
        for(int i = 0; i < n; i++)V[i] = puzzle[i];
        return V;
    }

    public int[] getPos() {
        return new int[]{posf, posc};
    }

    public int n(){ return n;}

    public NodoPuzzle(int n, int[] V, int posf, int posc){
        this.n = n;
        this.puzzle = V;
        this.posf = posf;
        this.posc = posc;
    }

    public NodoPuzzle(int n){
        this(n, Objetivo.GoalPuzzle(n), n-1, n-1);
    }

}
