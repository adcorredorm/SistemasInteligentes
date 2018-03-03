package Puzzle16;

import busqueda.*;

import java.util.Vector;

public class Sucesores<T> implements Sucesor<NodoPuzzle>{

    public static final int NO_OP = -1;
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public NodoPuzzle sucesor(NodoPuzzle estado, int action) {

        int[][] M = estado.getPuzzle();
        int[] pos = estado.getPos();

        int aux = M[pos[0]][pos[1]];

        switch (action){

            case UP:
                if(pos[0] == 0)   return estado;
                else{
                    M[pos[0]][pos[1]] = M[--pos[0]][pos[1]];
                    M[pos[0]][pos[1]] = aux;
                }
                break;

            case DOWN:
                if(pos[0] == 3)   return estado;
                else{
                    M[pos[0]][pos[1]] = M[++pos[0]][pos[1]];
                    M[pos[0]][pos[1]] = aux;
                }
                break;

            case LEFT:
                if(pos[1] == 0)   return estado;
                else{
                    M[pos[0]][pos[1]] = M[pos[0]][--pos[1]];
                    M[pos[0]][pos[1]] = aux;
                }
                break;

            case RIGHT:
                if(pos[1] == 3)   return estado;
                else{
                    M[pos[0]][pos[1]] = M[pos[0]][++pos[1]];
                    M[pos[0]][pos[1]] = aux;
                }
                break;

        }
        return new NodoPuzzle(M, pos[0], pos[1]);
    }

    @Override
    public Vector<EstAcc<NodoPuzzle>> obtener(NodoPuzzle estado) {
        Vector<EstAcc<NodoPuzzle>> V = new Vector<>(4);

        NodoPuzzle e;

        for(int i = 0; i < 4; i++){
            e = sucesor(estado, i);

            //test.imprimir_Matriz(e.getPuzzle());
            //System.out.println(e.getPos()[0] + " " + e.getPos()[1] + "\n");

            if(!e.equals(estado)) V.add(new EstAcc<>(e, i));
        }

        return V;
    }
}
