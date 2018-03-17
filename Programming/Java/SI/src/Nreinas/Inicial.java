package Nreinas;

import busquedaLocal.Semilla;

public class Inicial implements Semilla<int[]> {

    private int n;

    public Inicial(int n){
        this.n = n;
    }

    @Override
    public int[] aplicar() {
        int[] tablero = new int[n];
        for(int i = 0; i < n; i++) tablero[i] = (int)(Math.random()*n);
        return tablero;
    }
}
