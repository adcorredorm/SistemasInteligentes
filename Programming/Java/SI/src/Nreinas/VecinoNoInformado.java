package Nreinas;

import busquedaLocal.Vecino;

public class VecinoNoInformado implements Vecino<int[]> {
    @Override
    public int[] aplicar(int[] x) {
        int[] clon = x.clone();
        double p = 1.0/x.length;
        for(int i = 0; i < x.length; i++){
            if(Math.random() <= p) {
                clon[i] = (int)(Math.random()*x.length);
            }
        }
        return clon;
    }
}
