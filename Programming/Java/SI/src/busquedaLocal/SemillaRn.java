package busquedaLocal;

public class SemillaRn implements Semilla<double[]>{

    protected int n;

    public SemillaRn(int n){
        this.n = n;
    }

    @Override
    public double[] aplicar() {
        double[] X = new double[n];
        for(int i = 0; i < n; i++){
            X[i] = Math.random();
        }
        return X;
    }
}
