package Puzzle16;

import busqueda.Arco;
import busqueda.Heuristica;

public class Misplaced implements Heuristica<NodoPuzzle> {

    private int n;

    @Override
    public double estimar(Arco<NodoPuzzle> estado) {

        double costo = 0;
        int flag = 1;

        for(int i = 0; i < n; i++){
            for(int j= 0; j < n; j++){
                int x = estado.getEstado().getPuzzle()[i][j];
                if(x != flag%(n*n)) costo++;
                flag++;
            }
        }

        return costo;
    }

    public Misplaced(int n){
        this.n = n;
    }
}
