package busquedaLocal;

public class Main<T> {

    private Reemplazo<T> reemplazo;
    private Fitness<T> f;
    private Semilla<T> semilla;
    private Vecino<T> vecino;

    public Main(Reemplazo<T> r, Fitness<T> f, Semilla<T> semilla, Vecino<T> vecino){
        this.reemplazo = r;
        this.f = f;
        this.semilla = semilla;
        this.vecino = vecino;
    }

    public static String array_print(int[] X){
        StringBuilder print = new StringBuilder("[");
        for(int i = 0; i < X.length-1; i++){
            print.append(X[i]);
            print.append(',');
        }
        print.append(X[X.length-1]);
        print.append(']');
        return print.toString();
    }

    public void aplicar(){
        T x = semilla.aplicar();
        double fx = f.aplicar(x);
        int max_iter = 1000;
        for(int i = 0; i < max_iter; i++){
            T y = vecino.aplicar(x);
            double fy = f.aplicar(y);
            if(reemplazo.reemplazar(x, fx, y, fy)){
                x = y;
                fx = fy;
            }
            System.out.print(i);
            System.out.print(' ');
            System.out.println(fx);
            //System.out.print(' ');
            //System.out.println(array_print((int[]) x));
        }
    }

    public static void main(String[] args) {
        new Main(new TempladoSimulado<double[]>(), new Esfera(), new SemillaRn(10), new VecinoGaussiano(0.02)).aplicar();
    }

}
