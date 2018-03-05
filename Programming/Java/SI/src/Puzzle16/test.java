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

    private static double[][] computeStatics(int[][] expanded, int[][] nodesInList){
        double[] average_expanded = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                average_maxN = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 30; j++) {
                average_expanded[i] += expanded[i][j];
                average_maxN[i] += nodesInList[i][j];
            }
            average_expanded[i] /= 30;
            average_maxN[i] /= 30;
        }
        double[] standD_expanded = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                standD_nodes = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 30; j++) {
                standD_expanded[i] += Math.abs(average_expanded[i] - (double)expanded[i][j]);
                standD_nodes[i] += Math.abs(average_maxN[i] - (double)nodesInList[i][j]);
            }
            standD_expanded[i] /= 30;
            standD_nodes[i] /= 30;
        }
        return new double[][] {average_expanded, average_maxN, standD_expanded, standD_nodes};
    }

    public static void main(String[] args) {
        System.out.println("Calculando, por favor espere...\n");
        Sucesores s = new Sucesores();
        Objetivo o = new Objetivo();
        int max_prof = 15;
        int[][] acum_expanded = new int[6][30], acum_nodesInList = new int[6][30];
        for(int i = 0; i < 30; i++) {

            NodoPuzzle nodo = generarNodoPrueba();

            BFS<NodoPuzzle> bfs = new BFS<>(s, o, max_prof);
            DFS<NodoPuzzle> dfs = new DFS<>(s, o, max_prof);
            DFSIterado<NodoPuzzle> dfsi = new DFSIterado<>(s, o, max_prof);
            CostoUniforme<NodoPuzzle> cu = new CostoUniforme<>(s, o, max_prof);

            AStar<NodoPuzzle> mh = new AStar<>(s, o, max_prof, new Manhattan());
            AStar<NodoPuzzle> chinoR = new AStar<>(s, o, max_prof, new ChinoR());

            bfs.aplicar(nodo).costoTotal();
            dfs.aplicar(nodo).costoTotal();
            dfsi.aplicar(nodo).costoTotal();
            cu.aplicar(nodo).costoTotal();
            mh.aplicar(nodo).costoTotal();
            chinoR.aplicar(nodo).costoTotal();

            System.out.println("\nCorrida #" + (i+1));
            System.out.println("Nodos expandidos en BFS: " + bfs.getExpanded_nodes());
            System.out.println("Máximo número de nodos en la lista para BFS: " + bfs.getMax_obj_in_list());
            System.out.println("Nodos expandidos en DFS: " + dfs.getExpanded_nodes());
            System.out.println("Máximo número de nodos en la lista para DFS: " + dfs.getMax_obj_in_list());
            System.out.println("Nodos expandidos en DFSI: " + dfsi.getExpanded_nodes());
            System.out.println("Máximo número de nodos en la lista para DFSI: " + dfsi.getMax_obj_in_list());
            System.out.println("Nodos expandidos en Costo unitario: " + cu.getExpanded_nodes());
            System.out.println("Máximo número de nodos en la lista para Costo unitario: " + cu.getMax_obj_in_list());
            System.out.println("Nodos expandidos en A* (Manhattan): " + mh.getExpanded_nodes());
            System.out.println("Máximo número de nodos en la lista para A* (Manhattan): " + mh.getMax_obj_in_list());
            System.out.println("Nodos expandidos en A* (Missplaced Tiles): " + chinoR.getExpanded_nodes());
            System.out.println("Máximo número de nodos en la lista para A* (Missplaced Tiles): " + chinoR.getMax_obj_in_list());
        }
    }
}