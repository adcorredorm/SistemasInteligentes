package busquedaLocal;

import java.util.Random;

public class VecinoGaussiano implements Vecino<double[]>{

    private double sigma;

    public VecinoGaussiano(double sigma){
        this.sigma = sigma;
    }

    public double[] normal(int n, double sigma){
        double[] X = new double[n];
        Random r = new Random();
        for(int i = 0; i < n; i++){
            X[i] = r.nextGaussian()*sigma;
        }
        return X;
    }

    @Override
    public double[] aplicar(double[] x) {
        int n = x.length;
        double[] y = x.clone();
        double[] z = normal(n, sigma);
        for(int j = 0; j < n; j++) y[j] += z[j];
        return y;
    }
}
