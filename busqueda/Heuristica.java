package busqueda;

public interface Heuristica<T> {

    public double estimar(Arco<T> estado);

}
