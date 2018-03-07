package Puzzle16;

import busqueda.*;

public class test{

    private static NodoPuzzle generarNodoPrueba(int n){
        int random;
        NodoPuzzle nodo = new NodoPuzzle(n);

        for(int i = 0; i < 15; i++){

            random = (int) Math.floor(Math.random()*4);
            //UP-0, DOWN-1, LEFT-2, RIGHT-3
            if(random == 0 && nodo.getPos()[0] == 0 ||
                    random == 1 && nodo.getPos()[0] == (n-1) ||
                    random == 2 && nodo.getPos()[1] == 0 ||
                    random == 3 && nodo.getPos()[1] == (n-1)){
                i--;
                continue;
            }
            nodo = Sucesores.sucesor(nodo, Sucesores.AccionesPuzzle[random]);
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

        int n = 4;
        int max_prof = 15;

        Sucesores s = new Sucesores();
        Objetivo o = new Objetivo(n);
        Manhattan m = new Manhattan(n);
        Misplaced p = new Misplaced(n);

        long[] bfsexpand = new long[30];
        long[] dfsexpand = new long[30];
        long[] dfsiexpand = new long[30];
        long[] cuexpand = new long[30];
        long[] mhexpand = new long[30];
        long[] mpexpand = new long[30];

        long[] bfslist = new long[30];
        long[] dfslist = new long[30];
        long[] dfsilist = new long[30];
        long[] culist = new long[30];
        long[] mhlist = new long[30];
        long[] mplist = new long[30];

        NodoPuzzle nodoTest;


        for(int i = 0; i < 30; i++) {

            nodoTest = generarNodoPrueba(n);

            BFS<NodoPuzzle> bfs = new BFS<>(s, o, max_prof);
            DFS<NodoPuzzle> dfs = new DFS<>(s, o, max_prof);
            DFSIterado<NodoPuzzle> dfsi = new DFSIterado<>(s, o, max_prof);
            CostoUniforme<NodoPuzzle> cu = new CostoUniforme<>(s, o, max_prof);
            AStar<NodoPuzzle> mh = new AStar<>(s, o, max_prof, m);
            AStar<NodoPuzzle> mp = new AStar<>(s, o, max_prof, p);


            System.out.println("\nIteracion " + (i+1));

            bfs.aplicar(nodoTest);
            System.gc();
            System.out.println("BFS Check");
            dfs.aplicar(nodoTest);
            System.out.println("DFS Check");
            dfsi.aplicar(nodoTest);
            System.out.println("DFSI Check");
            cu.aplicar(nodoTest);
            System.out.println("Costo Uniforme Check");
            mh.aplicar(nodoTest);
            System.out.println("Manhattan Check");
            mp.aplicar(nodoTest);
            System.out.println("Misposed Check");


            bfsexpand[i] = bfs.getExpanded_nodes();
            dfsexpand[i] = dfs.getExpanded_nodes();
            dfsiexpand[i] = dfsi.getExpanded_nodes();
            cuexpand[i] = cu.getExpanded_nodes();
            mhexpand[i] = mh.getExpanded_nodes();
            mpexpand[i] = mp.getExpanded_nodes();

            bfslist[i] = bfs.getMax_obj_in_list();
            dfslist[i] = dfs.getMax_obj_in_list();
            dfsilist[i] = dfsi.getMax_obj_in_list();
            culist[i] = cu.getMax_obj_in_list();
            mhlist[i] = mh.getMax_obj_in_list();
            mplist[i] = mp.getMax_obj_in_list();

            System.gc();
        }

        System.out.println("\nBreath First Search:");
        for(long b : bfsexpand){
            System.out.println(b);
        }
        System.out.println("---------");
        for(long b : bfslist){
            System.out.println(b);
        }

        System.out.println("\nDepth First Search:");
        for(long b : dfsexpand){
            System.out.println(b);
        }
        System.out.println("---------");
        for(long b : dfslist){
            System.out.println(b);
        }

        System.out.println("\nIterated Depth First Search:");
        for(long b : dfsiexpand){
            System.out.println(b);
        }
        System.out.println("---------");
        for(long b : dfsilist){
            System.out.println(b);
        }

        System.out.println("\nUniform Cost:");
        for(long b : cuexpand){
            System.out.println(b);
        }
        System.out.println("---------");
        for(long b : culist){
            System.out.println(b);
        }

        System.out.println("\nManhattan:");
        for(long b : mhexpand){
            System.out.println(b);
        }
        System.out.println("---------");
        for(long b : mhlist){
            System.out.println(b);
        }

        System.out.println("\nMisplaced Tiles:");
        for(long b : mpexpand){
            System.out.println(b);
        }
        System.out.println("---------");
        for(long b : mplist){
            System.out.println(b);
        }
    }
}