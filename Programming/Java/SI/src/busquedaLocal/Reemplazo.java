package busquedaLocal;

public interface Reemplazo<T>{
    public boolean reemplazar(T A, double fitnessA,T B, double fitnessB);
}