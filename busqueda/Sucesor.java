package busqueda;

import java.util.Vector;

public interface Sucesor<T> {
    Vector<Arco<T>> obtener( Arco<T> estado );
}
