package busqueda;

import java.util.Vector;

public class Arco<T> {
    protected T estado;
    protected Vector<T> path;
    protected int accion;

    public Arco( T estado, Vector<T> path, int accion ){
        this.estado = estado;
        this.path = path;
        this.accion = accion;
    }

    public T getEstado(){ return estado; }
    public Vector<T> getPath(){
        if(path == null) path = new Vector<>();

        Vector<T> p = new Vector<>(path.size());
        for(T e: path) p.addElement(e);
        p.addElement(estado);

        return p;
    }
    public int getAccion(){ return accion; }
}
