package busqueda;

import java.util.Vector;

public interface Sucesor<T> {
    Vector<EstAcc<T>> obtener( T estado );
}
