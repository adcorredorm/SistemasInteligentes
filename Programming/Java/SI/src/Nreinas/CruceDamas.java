package Nreinas;

import busquedaLocal.Cruce;

public class CruceDamas implements Cruce<int[]> {
    @Override
    public int[][] aplicar(int[] padre1, int[] padre2) {
        int[] hijo1 = padre1.clone();
        int[] hijo2 = padre2.clone();
        int punto = (int)(Math.random()*(padre1.length-1))+1;

        System.arraycopy(hijo2, punto, padre1, punto, padre1.length-punto);
        System.arraycopy(hijo1, punto, padre2, punto, padre1.length-punto);

        return new int[][]{hijo1, hijo2};
    }
}
