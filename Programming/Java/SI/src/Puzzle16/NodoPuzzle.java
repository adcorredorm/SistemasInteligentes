package Puzzle16;

import busqueda.Accion;
import busqueda.Arco;

public class NodoPuzzle {

    private int n;
    private long puzzle;
    private int posf, posc;

    public static String toBin(long num, int len){
        StringBuilder bin = new StringBuilder(Long.toUnsignedString(num, 2));
        while(bin.length() < len) bin.insert(0, '0');
        return bin.toString();
    }

    public static long castBin(String bin){
        return Long.parseUnsignedLong(bin, 2);
    }

    public static void imprimir_Path(NodoPuzzle inicial, Arco<NodoPuzzle> rta){

        for(byte[] f : inicial.getMatriz()){
            for(byte e: f) System.out.print(Integer.toHexString(e).toUpperCase() + " ");
            System.out.println();
        }
        System.out.println();

        for(Accion a : rta.getPath()){
            inicial = Sucesores.sucesor(inicial, a);
            for(byte[] f : inicial.getMatriz()){
                for(byte e: f) System.out.print(Integer.toHexString(e).toUpperCase() + " ");
                System.out.println();
            }
            System.out.println();
        }

        System.out.println("\nNumero de movimientos realizados: " + rta.costoTotal());
    }

    public byte[][] getMatriz(){
        byte[][] M = new byte[n][n];
        String p = toBin(puzzle, 4 * n*n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                M[i][j] = (byte)castBin(p.substring(4*i*n + j*n,4*i*n + j*n + n));
            }
        }
        return M;
    }

    public long getPuzzle() {
        return puzzle;
    }

    public int[] getPos() {
        return new int[]{posf, posc};
    }

    public int n(){ return n;}

    public NodoPuzzle(int n, long puzzle, int posf, int posc){
        assert(n <= 4);
        this.n = n;
        this.puzzle = puzzle;
        this.posf = posf;
        this.posc = posc;
    }

    public NodoPuzzle(int n){
        this(n, Objetivo.GoalPuzzle(n), n-1, n-1);
    }

}
