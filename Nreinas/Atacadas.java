package Nreinas;

import busquedaLocal.Fitness;

public class Atacadas implements Fitness<int[]> {

    public static int comprobar(int[] x, int pos){
        int count = 0, i;
        for(i = 0; i < pos; i++){
            if(x[i] == x[pos]) count++;
            if(i + x[i] == pos + x[pos]) count++;
            if(i - x[i] == pos - x[pos]) count++;
        }
        for(i = pos + 1; i < x.length; i++){
            if(x[i] == x[pos]) count++;
            if(i + x[i] == pos + x[pos]) count++;
            if(i - x[i] == pos - x[pos]) count++;
        }
        return count;
    }

    @Override
    public double aplicar(int[] x) {
        int res = 0;
        for(int i = 0; i < x.length; i++){
            res += comprobar(x, i);
        }
        return res;
    }
}
