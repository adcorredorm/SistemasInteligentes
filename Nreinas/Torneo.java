package Nreinas;

import busquedaLocal.Seleccion;

public class Torneo implements Seleccion {

    private Uniforme uniforme = new Uniforme();

    private int aplicar( double f1, double f2 ){
        double ft = f1+f2;
        return (Math.random()<f1/ft)?1:0;
    }

    private int aplicar( int[] c, double[] f ){
       if(c.length == 1) return c[0];
        int[] nc = new int[c.length/2];
       for( int i=0; i<c.length; i+=2){
           nc[i>>1]=c[i+aplicar(f[c[i]],f[c[i+1]])];
       }
       return aplicar( nc, f );
    }

    @Override
    public int aplicar(double[] fitness) {
        int[] candidatos = new int[4];
        for(int i = 0; i < 4; i++){
            candidatos[i] = uniforme.aplicar(fitness);
        }

        return aplicar(candidatos, fitness);
    }
}
