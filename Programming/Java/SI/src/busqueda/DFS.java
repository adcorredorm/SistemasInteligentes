package busqueda;

public class DFS<T> extends Busqueda<T> {
    public DFS(Sucesor<T> _sucesor, Goal<T> _objetivo ){
        super( _sucesor, _objetivo);
    }

    public ColeccionBusqueda<T> coleccion(){
        return new PilaBusqueda<T>();
    }
}
