package Puzzle16;

import busqueda.Arco;
import busqueda.Heuristica;

public class Manhattan implements Heuristica<NodoPuzzle> {
    @Override
    public double estimar(Arco<NodoPuzzle> estado) {

        double costo = 0.0;

        int[][] M = estado.getEstado().getPuzzle();

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                int x = M[i][j];
                if(x != 0){
                    costo += Math.abs(i - Math.floor((x-1)/4));
                    costo += Math.abs(j - (x-1)%4);
                }
            }
        }

        return costo;
    }
}
