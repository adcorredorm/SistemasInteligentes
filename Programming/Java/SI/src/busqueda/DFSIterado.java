package busqueda;

import java.util.Vector;

public class DFSIterado<T> extends Busqueda<T>{

    private DFS<T> dfs;

    @Override
    protected ColeccionBusqueda<T> coleccion() {
        return null;
    }

    @Override
    public Arco<T> aplicar( T inicial ){
        Arco<T> actual = null;
        int prof = 0;
        while (actual == null && prof < max_prof){
            prof++;
            dfs = new DFS<>(sucesor, objetivo, prof);
            actual = dfs.aplicar(inicial);
        }

        return actual;
    }

    public DFSIterado(Sucesor<T> sucesor, Goal<T> objetivo, int max_prof) {
        super(sucesor, objetivo, max_prof);
        dfs = null;
    }
}
