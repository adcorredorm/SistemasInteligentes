package busqueda;

public class BFS<T> extends Busqueda<T> {
    public BFS(Sucesor<T> _sucesor, Goal<T> _objetivo ){
        super( _sucesor, _objetivo);
    }

    public ColeccionBusqueda<T> coleccion(){
        return new ColaBusqueda<T>();
    }
}