package busqueda;

import java.util.Vector;

public class Arco<T>{
    protected T estado;
    protected Vector<Accion> path;
    protected double costoTotal;

    public Arco( T estado, Vector<Accion> path, double costoTotal ){
        this.estado = estado;
        this.path = path;
        this.costoTotal = costoTotal;
    }

    public T getEstado(){ return estado; }

    public Vector<Accion> getPath(){
        return (Vector<Accion>)path.clone();
    }
    public double costoTotal(){ return costoTotal; }

}
