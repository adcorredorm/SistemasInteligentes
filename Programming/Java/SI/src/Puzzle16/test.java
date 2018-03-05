package Puzzle16;

import busqueda.*;

public class test{

    private static NodoPuzzle generarNodoPrueba(){
        int random;
        NodoPuzzle nodo = new NodoPuzzle();

        for(int i = 0; i < 15; i++){
            random = (int) Math.floor(Math.random()*4);
            nodo = Sucesores.sucesor(nodo, Sucesores.AccionesPuzzle().get(random));
        }

        return nodo;
    }

    public static void main(String[] args) {

        Sucesores s = new Sucesores();
        Objetivo o = new Objetivo();
        int max_prof = 15;
        for(int i = 0; i < 30; i++){

            NodoPuzzle nodo = generarNodoPrueba();

            BFS<NodoPuzzle> bfs = new BFS<>(s, o, max_prof);
            DFS<NodoPuzzle> dfs = new DFS<>(s, o, max_prof);
            DFSIterado<NodoPuzzle> dfsi = new DFSIterado<>(s, o, max_prof);
            CostoUniforme<NodoPuzzle> cu = new CostoUniforme<>(s, o, max_prof);

            AStar<NodoPuzzle> mh = new AStar<>(s, o, max_prof, new Manhattan());
            AStar<NodoPuzzle> chinoR = new AStar<>(s, o, max_prof, new ChinoR());

            System.out.println("BFS: " + bfs.aplicar(nodo).costoTotal());
            System.out.println("DFS: " + dfs.aplicar(nodo).costoTotal());
            System.out.println("DFSI: " + dfsi.aplicar(nodo).costoTotal());
            System.out.println("CU: " + cu.aplicar(nodo).costoTotal());
            System.out.println("Heurísticas:");
            System.out.println("Manhattan: " + mh.aplicar(nodo).costoTotal());
            System.out.println("Chino Rabon: " + chinoR.aplicar(nodo).costoTotal());
            System.out.println("-----------------------------------------------------------------------");
            System.out.println();
        }

    }
}