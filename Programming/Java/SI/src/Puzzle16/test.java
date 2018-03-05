package Puzzle16;

import busqueda.*;

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

    public static final int[][] Test_puzzle = {{5,1,3,4},{2,11,6,8},{9,10,7,12},{13,0,14,15}};

    public static void main(String[] args) {

        Sucesores<NodoPuzzle> s = new Sucesores<>();
        Objetivo o = new Objetivo();
        int max_prof = 15;

        for(int i = 0; i < 1; i++){
            NodoPuzzle nodo = new NodoPuzzle(Test_puzzle, 3, 1);//generarNodoPrueba(15);

            BFS<NodoPuzzle> bfs = new BFS<>(s, o, max_prof);
            //DFS<NodoPuzzle> dfs = new DFS<>(s, o, max_prof);
            //DFSIterado<NodoPuzzle> dfsi = new DFSIterado<>(s, o, max_prof);
            //CostoUniforme<NodoPuzzle> cu = new CostoUniforme<>(s, o, max_prof);

            AStar<NodoPuzzle> mh = new AStar<>(s, o, max_prof, new Manhattan());
            //AStar<NodoPuzzle> chinoR = new AStar<>(s, o, max_prof, new ChinoR());

            System.out.println("Manhattan: " + mh.aplicar(nodo).costoTotal());
            System.out.println("BFS: " + bfs.aplicar(nodo).costoTotal());
            //System.out.println(dfs.aplicar(nodo).costoTotal());
            //System.out.println(dfsi.aplicar(nodo).costoTotal());
            //System.out.println(cu.aplicar(nodo).costoTotal());


            //System.out.println(chinoR.aplicar(nodo).costoTotal());

            System.out.println();

            //imprimir_Rta(nodo, mh.aplicar(nodo));
            //imprimir_Rta(nodo, bfs.aplicar(nodo));
        }



    }
}