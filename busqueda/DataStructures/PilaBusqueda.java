package busqueda.DataStructures;

import busqueda.Arco;

public class PilaBusqueda<T> extends ListaBusqueda<T> {

    public void adicionar(Arco<T> a) {
        Nodo<T> aux = new Nodo<T>();
        aux.arco = a;
        if (esvacia()) {
            head = tail = aux;
        } else {
            aux.next = head;
            head = aux;
        }
        this.size++;
    }

    @Override
    public int size() {
        return this.size;
    }
}
