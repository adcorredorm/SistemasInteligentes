package Puzzle16;

import busqueda.Arco;
import busqueda.Heuristica;

public class Manhattan implements Heuristica<NodoPuzzle> {

    private int n;

    @Override
    public double estimar(Arco<NodoPuzzle> estado) {

        double costo = 0.0;

        int[][] M = estado.getEstado().getPuzzle();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int x = M[i][j];
                if(x != 0){
                    costo += Math.abs(i - Math.floor((x-1)/n));
                    costo += Math.abs(j - (x-1)%n);
                }
            }
        }

        return costo;
    }

    public Manhattan(int n){
        this.n = n;
    }
}
