package busqueda;

import busqueda.DataStructures.ColeccionBusqueda;

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
            this.expanded_nodes += dfs.getExpanded_nodes();
            this.max_obj_in_list += dfs.getMax_obj_in_list();
        }

        return actual;
    }

    public DFSIterado(Sucesor<T> sucesor, Goal<T> objetivo, int max_prof) {
        super(sucesor, objetivo, max_prof);
        dfs = null;
    }
}
