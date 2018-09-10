package busquedaLocal;

public class Esfera implements Fitness<double[]> {
    @Override
    public double aplicar(double[] x) {
        double resultado = 0.0;
        for(double d : x){
            resultado += d*d;
        }
        return resultado;
    }
}
