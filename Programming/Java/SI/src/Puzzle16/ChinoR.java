package Puzzle16;

import busqueda.Arco;
import busqueda.Heuristica;

public class ChinoR implements Heuristica<NodoPuzzle> {

    @Override
    public double estimar(Arco<NodoPuzzle> estado) {

        double costo = 0;
        int flag = 1;

        for(int i = 0; i < 3; i++){
            for(int j= 0; j < 3; j++){
                int x = estado.getEstado().getPuzzle()[i][j];
                if(x != flag%16) costo++;
                flag++;
            }
        }

        return costo;
    }
}
