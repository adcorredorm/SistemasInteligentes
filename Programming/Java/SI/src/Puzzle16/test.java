package Puzzle16;

import busqueda.*;

public class test {

    public static void imprimir_Matriz(int[][] M){
        for(int[] x : M){
            for(int y: x) System.out.print(Integer.toString(y, x.length * x.length).toUpperCase() + " ");
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

    public static NodoPuzzle generarNodoPrueba(int n, int movimientos){
        int random;
        NodoPuzzle nodo = new NodoPuzzle(n);

        for(int i = 0; i < movimientos; i++){
            random = (int) Math.floor(Math.random()*4);
            nodo = Sucesores.sucesor(nodo, Sucesores.AccionesPuzzle[random]);
        }

        return nodo;
    }

    public static void main(String[] args) {

        Sucesores<NodoPuzzle> s = new Sucesores<>();
        int max_prof = 15, n = 4;
        Objetivo o = new Objetivo(n);

        for(int i = 0; i < 1; i++){
            NodoPuzzle nodo = generarNodoPrueba(n, 15);

            BFS<NodoPuzzle> bfs = new BFS<>(s, o, max_prof);
            DFS<NodoPuzzle> dfs = new DFS<>(s, o, max_prof);
            DFSIterado<NodoPuzzle> dfsi = new DFSIterado<>(s, o, max_prof);
            CostoUniforme<NodoPuzzle> cu = new CostoUniforme<>(s, o, max_prof);

            AStar<NodoPuzzle> mh = new AStar<>(s, o, max_prof, new Manhattan(n));
            AStar<NodoPuzzle> mp = new AStar<>(s, o, max_prof, new Misplaced(n));

            System.out.println("BFS: " + bfs.aplicar(nodo).costoTotal());
            System.out.println("DFS: " + dfs.aplicar(nodo).costoTotal());
            System.out.println("DFSI: " + dfsi.aplicar(nodo).costoTotal());
            System.out.println("CU: " + cu.aplicar(nodo).costoTotal());
            System.out.println("----");
            System.out.println("Manhattan: " + mh.aplicar(nodo).costoTotal());
            //System.out.println("Misplaced: " + mp.aplicar(nodo).costoTotal());

            System.out.println();

            imprimir_Rta(nodo, mh.aplicar(nodo));
        }



    }
}