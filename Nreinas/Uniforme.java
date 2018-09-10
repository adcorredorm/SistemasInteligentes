package Nreinas;

import busquedaLocal.Seleccion;

public class Uniforme implements Seleccion {
    @Override
    public int aplicar(double[] fitness) {
        return (int)(Math.random()*fitness.length);
    }
}
