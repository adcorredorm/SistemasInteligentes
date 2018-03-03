package Puzzle16;

import busqueda.*;

import java.util.Vector;

public class test {

    public static void imprimir_Matriz(int[][] M){
        for(int[] x : M){
            for(int y: x) System.out.print(Integer.toHexString(y).toUpperCase() + " ");
            System.out.println();
        }
    }

    public static void imprimir_Vector(Vector<NodoPuzzle> V){
        NodoPuzzle aux;
        for(int i = 0; i < V.size(); i++){
            aux = V.get(i);
            imprimir_Matriz(aux.getPuzzle());
            System.out.println("(" + aux.getPos()[0] + "," + aux.getPos()[1] + ")\n");
        }
    }

    public static int[] encontrar_zero(int[][] M){
        for(int i = 0; i < M.length; i++){
            for(int j = 0; j < M[0].length; j++) if(M[i][j] == 0) return new int[]{i,j};
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {

        BFS<NodoPuzzle> b = new BFS<NodoPuzzle>(new Sucesores<NodoPuzzle>(), new Objetivo<NodoPuzzle>());

        int [][] M = {{1,2,3,4},{5,6,7,8},{9,0,11,12},{13,10,14,15}};

        Vector<NodoPuzzle> V = b.aplicar(new NodoPuzzle(M, encontrar_zero(M)[0], encontrar_zero(M)[1])).getPath();

        imprimir_Vector(V);

    }

}
