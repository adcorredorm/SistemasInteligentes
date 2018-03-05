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
        for(int i = 0; i < 30; i++){

            NodoPuzzle nodo = generarNodoPrueba();

            BFS<NodoPuzzle> bfs = new BFS<>(s, o, max_prof);
            DFS<NodoPuzzle> dfs = new DFS<>(s, o, max_prof);
            DFSIterado<NodoPuzzle> dfsi = new DFSIterado<>(s, o, max_prof);
            CostoUniforme<NodoPuzzle> cu = new CostoUniforme<>(s, o, max_prof);

            AStar<NodoPuzzle> mh = new AStar<>(s, o, max_prof, new Manhattan());
            AStar<NodoPuzzle> chinoR = new AStar<>(s, o, max_prof, new ChinoR());

            /*System.out.println("BFS: " + */bfs.aplicar(nodo).costoTotal();
            /*System.out.println("DFS: " + */dfs.aplicar(nodo).costoTotal();
            /*System.out.println("DFSI: " + */dfsi.aplicar(nodo).costoTotal();
            /*System.out.println("CU: " + */cu.aplicar(nodo).costoTotal();
            /*System.out.println("Heurísticas:");
            /*System.out.println("Manhattan: " + */mh.aplicar(nodo).costoTotal();
            /*System.out.println("Chino Rabon: " + */chinoR.aplicar(nodo).costoTotal();
            //System.out.println("-----------------------------------------------------------------------");
            //System.out.println();
            acum_expanded[0][i] = bfs.getExpanded_nodes();
            acum_expanded[1][i] = dfs.getExpanded_nodes();
            acum_expanded[2][i] = dfsi.getExpanded_nodes();
            acum_expanded[3][i] = cu.getExpanded_nodes();
            acum_expanded[4][i] = mh.getExpanded_nodes();
            acum_expanded[5][i] = chinoR.getExpanded_nodes();
            acum_nodesInList[0][i] = bfs.getMax_obj_in_list();
            acum_nodesInList[1][i] = dfs.getMax_obj_in_list();
            acum_nodesInList[2][i] = dfsi.getMax_obj_in_list();
            acum_nodesInList[3][i] = cu.getMax_obj_in_list();
            acum_nodesInList[4][i] = mh.getMax_obj_in_list();
            acum_nodesInList[5][i] = chinoR.getMax_obj_in_list();
        }
        double[][] statics = computeStatics(acum_expanded,acum_nodesInList);
        System.out.printf("El promedio de máximo número de nodos en la lista para BFS es: %.4f \n", statics[0][0]);
        System.out.printf("La desviación estarndar de máximo número de nodos en la lista para BFS es: %.4f \n", statics[1][0]);
        System.out.printf("El promedio nodos expandidos para BFS es: %.4f \n", statics[2][0]);
        System.out.printf("La desviación estandar de nodos expandidios para BFS es: %.4f \n", statics[3][0]);
        System.out.printf("\n");
        System.out.printf("El promedio de máximo número de nodos en la lista para DFS es: %.4f \n", statics[0][1]);
        System.out.printf("La desviación estarndar de máximo número de nodos en la lista para DFS es: %.4f \n", statics[1][1]);
        System.out.printf("El promedio nodos expandidos para DFS es: %.4f \n", statics[2][1]);
        System.out.printf("La desviación estandar de nodos expandidios para DFS es: %.4f \n", statics[3][1]);
        System.out.printf("\n");
        System.out.printf("El promedio de máximo número de nodos en la lista para DFSI es: %.4f \n", statics[0][2]);
        System.out.printf("La desviación estarndar de máximo número de nodos en la lista para DFSI es: %.4f \n", statics[1][2]);
        System.out.printf("El promedio nodos expandidos para DFSI es: %.4f \n", statics[2][2]);
        System.out.printf("La desviación estandar de nodos expandidios para DFSI es: %.4f \n", statics[3][2]);
        System.out.printf("\n");
        System.out.printf("El promedio de máximo número de nodos en la lista para Costo Uniforme es: %.4f \n", statics[0][3]);
        System.out.printf("La desviación estarndar de máximo número de nodos en la lista para Costo Uniforme es: %.4f \n", statics[1][3]);
        System.out.printf("El promedio nodos expandidos para Costo Uniforme es: %.4f \n", statics[2][3]);
        System.out.printf("La desviación estandar de nodos expandidios para Costo Uniforme es: %.4f \n", statics[3][3]);
        System.out.printf("\n");
        System.out.printf("El promedio de máximo número de nodos en la lista para A* (Manhattan) es: %.4f \n", statics[0][4]);
        System.out.printf("La desviación estarndar de máximo número de nodos en la lista para A* (Manhattan) es: %.4f \n", statics[1][4]);
        System.out.printf("El promedio nodos expandidos para A* (Manhattan) es: %.4f \n", statics[2][4]);
        System.out.printf("La desviación estandar de nodos expandidios para A* (Manhattan) es: %.4f \n", statics[3][4]);
        System.out.printf("\n");
        System.out.printf("El promedio de máximo número de nodos en la lista para A* (Missplaced Tiles) es: %.4f \n", statics[0][5]);
        System.out.printf("La desviación estarndar de máximo número de nodos en la lista para A* (Missplaced Tiles) es: %.4f \n", statics[1][5]);
        System.out.printf("El promedio nodos expandidos para A* (Missplaced Tiles) es: %.4f \n", statics[2][5]);
        System.out.printf("La desviación estandar de nodos expandidios para A* (Missplaced Tiles) es: %.4f \n", statics[3][5]);
    }
}