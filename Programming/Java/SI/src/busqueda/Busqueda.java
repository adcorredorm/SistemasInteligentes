package busqueda;

import busqueda.DataStructures.ColeccionBusqueda;

import java.util.Vector;

public abstract class Busqueda<T> {
    protected Sucesor<T> sucesor;
    protected Goal<T> objetivo;
    protected int max_prof;

    public Busqueda(Sucesor<T> sucesor, Goal<T> objetivo, int max_prof ){
        this.sucesor = sucesor;
        this.objetivo = objetivo;
        this.max_prof = max_prof;
    }

    protected abstract ColeccionBusqueda<T> coleccion();

    public Arco<T> aplicar( T inicial ){
        ColeccionBusqueda<T> c = coleccion();
        c.adicionar(new Arco<T>(inicial,new Vector<Accion>(),0.0));
        Arco<T> actual = c.obtener();

        while(!c.esvacia() && !objetivo.isGoal(actual.getEstado())){
            c.remover();

            if(actual.getPath().size() < max_prof){
                Vector<Arco<T>> h = sucesor.obtener(actual);
                for( Arco<T> a:h ){
                    c.adicionar(a);
                }
            }

            actual = c.obtener();
        }
        if( !c.esvacia() ) return c.obtener();
        return null;
    }
}
