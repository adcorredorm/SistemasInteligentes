package Puzzle16;

import busqueda.*;

public class test {

    public static void main(String[] args) {

        int n = 4, max_prof = 15;

        Sucesores s = new Sucesores();
        Objetivo o = new Objetivo(n);
        Manhattan mh = new Manhattan(n);
        Misplaced mp = new Misplaced(n);

        NodoPuzzle nodo = Stats.generarNodoPrueba(n, max_prof);

        BFS<NodoPuzzle> bfs = new BFS<>(s, o, max_prof);
        DFS<NodoPuzzle> dfs = new DFS<>(s, o, max_prof);
        DFSIterado<NodoPuzzle> dfsi = new DFSIterado<>(s, o, max_prof);
        CostoUniforme<NodoPuzzle> cu = new CostoUniforme<>(s, o, max_prof);
        AStar<NodoPuzzle> Mh = new AStar<>(s, o, max_prof, mh);
        AStar<NodoPuzzle> Mp = new AStar<>(s, o, max_prof, mp);

        Arco<NodoPuzzle> rta = Mh.aplicar(nodo);
        NodoPuzzle.imprimir_Path(nodo, rta);

    }
}
