package busqueda.DataStructures;

import busqueda.Arco;

public interface ColeccionBusqueda<T> {
    public void adicionar( Arco<T> a );
    public boolean esvacia();
    public Arco<T> obtener();
    public void remover();
}
