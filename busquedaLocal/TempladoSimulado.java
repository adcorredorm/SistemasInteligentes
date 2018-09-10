package busquedaLocal;

public class TempladoSimulado<T> implements Reemplazo<T> {

    private int t = 200;
    private int i = 0;

    @Override
    public boolean reemplazar(T A, double fitnessA, T B, double fitnessB) {
        double e = Math.exp((-i% t)/ t);
        double random = Math.random();
        i++;
        return fitnessA >= fitnessB || random <= e;
    }
}
