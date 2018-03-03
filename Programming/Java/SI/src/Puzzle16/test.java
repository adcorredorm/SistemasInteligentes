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

    private static void imprimir_Vector(Vector<NodoPuzzle> V){
        NodoPuzzle aux;
        for(int i = 0; i < V.size(); i++){
            aux = V.get(i);
            imprimir_Matriz(aux.getPuzzle());
            System.out.println("(" + aux.getPos()[0] + "," + aux.getPos()[1] + ")\n");
        }
    }

    public static NodoPuzzle generarNodoPrueba(int movimientos){
        int random;
        NodoPuzzle nodo = new NodoPuzzle();

        for(int i = 0; i < movimientos; i++){
            random = (int) Math.floor(Math.random()*4);
            nodo = Sucesores.sucesor(nodo, random);
        }

        return nodo;
    }

    public static void main(String[] args) {

        DFSIterado<NodoPuzzle> b = new DFSIterado<>(new Sucesores<NodoPuzzle>(), new Objetivo<NodoPuzzle>(), 30);

        Vector<NodoPuzzle> V = b.aplicar(generarNodoPrueba(15)).getPath();
        imprimir_Vector(V);
        System.out.println("Numero de movimientos realizados: " + V.size());

    }
}