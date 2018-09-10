package busqueda.DataStructures;

import busqueda.Arco;
import busqueda.Heuristica;

public class AStarBusqueda<T> extends PriorityBusqueda<T> {

    private Heuristica<T> heuristica;

    public AStarBusqueda(int capacidad, Heuristica<T> heuristica){
        super(capacidad);
        this.heuristica = heuristica;
    }

    @Override
    protected double evaluar(Arco<T> arco){
        return arco.costoTotal() + heuristica.estimar(arco);
    }
}
