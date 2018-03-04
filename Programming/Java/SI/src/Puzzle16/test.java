package Puzzle16;

import busqueda.*;

import java.util.Vector;

public class test {

    public static void imprimir_Matriz(int[][] M){
        for(int[] x : M){
            for(int y: x) System.out.print(Integer.toHexString(y).toUpperCase() + " ");
            System.out.println();
        }
    }

    private static void imprimir_Rta(NodoPuzzle inicial, Arco<NodoPuzzle> rta){

        imprimir_Matriz(inicial.getPuzzle());

        for(Accion a : rta.getPath()){
            System.out.println();
            inicial = Sucesores.sucesor(inicial, a);
            imprimir_Matriz(inicial.getPuzzle());
        }

        System.out.println("\nNumero de movimientos realizados: " + rta.costoTotal());
    }

    public static NodoPuzzle generarNodoPrueba(int movimientos){
        int random;
        NodoPuzzle nodo = new NodoPuzzle();

        for(int i = 0; i < movimientos; i++){
            random = (int) Math.floor(Math.random()*4);
            nodo = Sucesores.sucesor(nodo, Sucesores.AccionesPuzzle().get(random));
        }

        return nodo;
    }

    public static void main(String[] args) {

        DFSIterado<NodoPuzzle> b = new DFSIterado<>(new Sucesores<NodoPuzzle>(), new Objetivo(), 20);

        NodoPuzzle nodo = generarNodoPrueba(15);

        Arco<NodoPuzzle> rta = b.aplicar(nodo);

        imprimir_Rta(nodo, rta);

    }
}