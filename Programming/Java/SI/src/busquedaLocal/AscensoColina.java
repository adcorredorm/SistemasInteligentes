package busquedaLocal;

public class AscensoColina<T> implements Reemplazo<T>{

    @Override
    public boolean reemplazar(T A, double fitnessA, T B, double fitnessB) {
        return fitnessA >= fitnessB;
    }
}