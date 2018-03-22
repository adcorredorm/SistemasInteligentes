package BusquedaPoblacional;

import Nreinas.*;
import busquedaLocal.*;

public class AGgeneracional<T> {

    private int N;
    private Seleccion seleccion;
    private Cruce<T> cruce;
    private Vecino<T> mutacion;
    private Semilla<T> semilla;
    private Fitness<T> fitness;
    private final int iteraciones = 100;


    public AGgeneracional(
            int N, Seleccion seleccion,
            Cruce<T> cruce, Vecino<T> mutacion ,
            Semilla<T> semilla, Fitness<T> fitness){
        this.N = N;
        this.cruce = cruce;
        this.seleccion = seleccion;
        this.semilla = semilla;
        this.mutacion = mutacion;
        this.fitness = fitness;
    }

    @SuppressWarnings("unchecked")
    public void aplicar(){
        T[] poblacion = (T[]) new Object[N];
        double[] fitnessGlobal = new double[N];
        for(int i = 0; i < N; i++){
            poblacion[i] = semilla.aplicar();
            fitnessGlobal[i] = this.fitness.aplicar(poblacion[i]);
        }
        for(int i = 0; i < iteraciones; i++){
            T[] nuevaPoblacion = (T[]) new Object[N];
            for(int k = 0; k < N; k+=2){
                int p1 = seleccion.aplicar(fitnessGlobal);
                int p2 = seleccion.aplicar(fitnessGlobal);
                nuevaPoblacion[k] = poblacion[p1];
                nuevaPoblacion[k+1] = poblacion[p2];
                T[] hijos = cruce.aplicar(nuevaPoblacion[k], nuevaPoblacion[k+1]);
                hijos[0] = mutacion.aplicar(hijos[0]);
                hijos[1] = mutacion.aplicar(hijos[1]);
                if(this.fitness.aplicar(hijos[0]) <= fitnessGlobal[p1]) nuevaPoblacion[k] = hijos[0];
                if(this.fitness.aplicar(hijos[1]) <= fitnessGlobal[p2]) nuevaPoblacion[k+1] = hijos[1];
            }
            poblacion = nuevaPoblacion;
            System.out.print("Gen " + i + ": ");
            double min = 64;
            for(int j = 0; j < N; j++){
                fitnessGlobal[j] = this.fitness.aplicar(poblacion[j]);
                if(fitnessGlobal[j] < min) min = fitnessGlobal[j];
                System.out.print(fitnessGlobal[j] + " ");
            }
            System.out.println(min + "\n");
        }

    }

    public static void main(String[] args) {
        new AGgeneracional<int[]>(100, new Torneo(), new CruceDamas(), new VecinoInformado() ,new Inicial(8), new Atacadas()).aplicar();
    }
}
