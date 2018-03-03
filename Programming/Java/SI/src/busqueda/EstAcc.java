package busqueda;

public class EstAcc<T> {
    protected T estado;
    protected int accion;

    public EstAcc(T estado, int accion){
        this.estado = estado;
        this.accion = accion;
    }

    public T getEstado(){ return estado; }
    public int getAccion(){ return accion; }
}