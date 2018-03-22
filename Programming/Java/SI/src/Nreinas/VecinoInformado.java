package Nreinas;

import busquedaLocal.Vecino;

import java.util.Vector;

public class VecinoInformado implements Vecino<int[]> {

    @Override
    public int[] aplicar(int[] x) {
        int maximo = 0, ataquei;
        Vector<Integer> v = new Vector<Integer>();
        v.add(0);
        for(int i = 0; i < x.length; i++) {
            ataquei = Atacadas.comprobar(x,i);
            if(maximo < ataquei) {
                v.clear();
                v.add(i);
                maximo = ataquei;
            }else if(maximo == ataquei) v.add(i);
        }
        int[] nuevo = x.clone();
        nuevo[v.get((int) (Math.random()*v.size()))] = (int) (Math.random()*x.length);
        return nuevo;
    }
}
