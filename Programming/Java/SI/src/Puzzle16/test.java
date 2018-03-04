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

    private static void imprimir_Rta(NodoPuzzle inicial, Arco<NodoPuzzle> rta){

        imprimir_Matriz(inicial.getPuzzle());

        for(Accion a : rta.getPath()){
            System.out.println();
            inicial = Sucesores.sucesor(inicial, a);
            imprimir_Matriz(inicial.getPuzzle());
        }

        System.out.println("\nNumero de movimientos realizados: " + rta.costoTotal());
    }

    public static NodoPuzzle generarNodoPrueba(int movimientos){
        int random;
        NodoPuzzle nodo = new NodoPuzzle();

        for(int i = 0; i < movimientos; i++){
            random = (int) Math.floor(Math.random()*4);
            nodo = Sucesores.sucesor(nodo, Sucesores.AccionesPuzzle().get(random));
        }

        return nodo;
    }

    public static void main(String[] args) {

        Sucesores<NodoPuzzle> s = new Sucesores<>();
        Objetivo o = new Objetivo();
        int max_prof = 15;

        NodoPuzzle nodo = generarNodoPrueba(max_prof);

        BFS<NodoPuzzle> bfs = new BFS<>(s, o, max_prof);
        DFS<NodoPuzzle> dfs = new DFS<>(s, o, max_prof);
        DFSIterado<NodoPuzzle> dfsi = new DFSIterado<>(s, o, max_prof);
        CostoUniforme<NodoPuzzle> cu = new CostoUniforme<>(s, o, max_prof);

        System.out.println(bfs.aplicar(nodo).costoTotal());
        System.out.println(dfs.aplicar(nodo).costoTotal());
        System.out.println(dfsi.aplicar(nodo).costoTotal());
        System.out.println(cu.aplicar(nodo).costoTotal());

        System.out.println();

        //imprimir_Rta(nodo, bfs.aplicar(nodo));
        //imprimir_Rta(nodo, cu.aplicar(nodo));

    }
}