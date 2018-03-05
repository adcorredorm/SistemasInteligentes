package busqueda;

import busqueda.DataStructures.AStarBusqueda;
import busqueda.DataStructures.ColeccionBusqueda;

public class AStar<T> extends Busqueda<T>{

    private Heuristica<T> heuristica;

    public AStar(Sucesor<T> _sucesor, Goal<T> _objetivo, int max_prof, Heuristica<T> heuristica) {
        super(_sucesor, _objetivo, max_prof);
        this.heuristica = heuristica;
    }

    @Override
    public ColeccionBusqueda<T> coleccion(){
        return new AStarBusqueda<T>(max_prof, heuristica);
    }

}
