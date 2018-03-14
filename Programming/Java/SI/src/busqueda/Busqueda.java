package busqueda;

import busqueda.DataStructures.ColeccionBusqueda;
import java.util.Vector;

public abstract class Busqueda<T> {
    protected Sucesor<T> sucesor;
    protected Goal<T> objetivo;
    protected int max_prof;
    protected int max_obj_in_list;
    protected int expanded_nodes;

    public Busqueda(Sucesor<T> sucesor, Goal<T> objetivo, int max_prof ){
        this.sucesor = sucesor;
        this.objetivo = objetivo;
        this.max_prof = max_prof;
        this.max_obj_in_list = 1;
        this.expanded_nodes = 0;
    }

    protected abstract ColeccionBusqueda<T> coleccion();

    public Arco<T> aplicar( T inicial ){
        ColeccionBusqueda<T> c = coleccion();
        c.adicionar(new Arco<T>(inicial, new Vector<Accion>(),0.0));
        Arco<T> actual = c.obtener();

        Vector<Arco<T>> h;

        while(!c.esvacia() && !objetivo.isGoal(actual.getEstado())){
            c.remover();

            if(actual.getPath().size() < max_prof){
                h = sucesor.obtener(actual);
                this.expanded_nodes++;
                for( Arco<T> a:h ){
                    c.adicionar(a);
                }
            }
            max_obj_in_list = Math.max(max_obj_in_list, c.size());
            actual = c.obtener();
        }
        if( !c.esvacia() ) return c.obtener();
        return null;
    }

    public int getMax_obj_in_list() {
        return max_obj_in_list;
    }

    public int getExpanded_nodes() {
        return expanded_nodes;
    }
}
