package busqueda;

import busqueda.DataStructures.ColeccionBusqueda;
import busqueda.DataStructures.PilaBusqueda;

public class DFS<T> extends Busqueda<T> {
    public DFS(Sucesor<T> _sucesor, Goal<T> _objetivo, int max_prof ){
        super( _sucesor, _objetivo, max_prof);
    }

    public ColeccionBusqueda<T> coleccion(){
        return new PilaBusqueda<T>();
    }
}
