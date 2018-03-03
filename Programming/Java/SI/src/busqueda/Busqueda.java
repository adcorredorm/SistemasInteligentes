package busqueda;

import java.util.Vector;

public abstract class Busqueda<T> {
    protected Sucesor<T> sucesor;
    protected Goal<T> objetivo;

    public Busqueda(Sucesor<T> _sucesor, Goal<T> _objetivo ){
        sucesor = _sucesor;
        objetivo = _objetivo;
    }

    protected abstract ColeccionBusqueda<T> coleccion();

    public Arco<T> aplicar( T inicial ){
        ColeccionBusqueda<T> c = coleccion();
        c.adicionar(new Arco<T>(inicial,null,-1));
        Arco<T> actual = c.obtener();
        while(!c.esvacia() && !objetivo.isGoal(actual.getEstado())){
            c.remover();
            Vector<EstAcc<T>> h = sucesor.obtener(actual.getEstado());
            for( EstAcc<T> e:h ){
                c.adicionar( new Arco<T>(e.getEstado(), actual.getPath(), e.getAccion()));
            }
            actual = c.obtener();
        }
        if( !c.esvacia() ) return c.obtener();
        return null;
    }
}
