package Puzzle16;

import busqueda.*;

import java.util.Vector;

public class Sucesores implements Sucesor<NodoPuzzle>{

    //public static final Accion NO_OP = new Accion("NO_OP", 0);
    private static final Accion UP = new Accion("UP", 1);
    private static final Accion DOWN = new Accion("DOWN", 1);
    private static final Accion LEFT = new Accion("LEFT", 1);
    private static final Accion RIGHT = new Accion("RIGHT", 1);

    public static Vector<Accion> AccionesPuzzle(){
        Vector<Accion> acciones = new Vector<>(4);
        acciones.addElement(UP);
        acciones.addElement(DOWN);
        acciones.addElement(LEFT);
        acciones.addElement(RIGHT);
        return acciones;
    }

    public static NodoPuzzle sucesor(NodoPuzzle estado, Accion action) {

        int[][] M = estado.getPuzzle();
        int[] pos = estado.getPos();

        int aux = M[pos[0]][pos[1]];

        switch (action.getCode()){

            case "UP":
                if(pos[0] == 0)   return estado;
                else{
                    M[pos[0]][pos[1]] = M[--pos[0]][pos[1]];
                    M[pos[0]][pos[1]] = aux;
                }
                break;

            case "DOWN":
                if(pos[0] == 3)   return estado;
                else{
                    M[pos[0]][pos[1]] = M[++pos[0]][pos[1]];
                    M[pos[0]][pos[1]] = aux;
                }
                break;

            case "LEFT":
                if(pos[1] == 0)   return estado;
                else{
                    M[pos[0]][pos[1]] = M[pos[0]][--pos[1]];
                    M[pos[0]][pos[1]] = aux;
                }
                break;

            case "RIGHT":
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
    public Vector<Arco<NodoPuzzle>> obtener(Arco<NodoPuzzle> estado) {
        Vector<Arco<NodoPuzzle>> V = new Vector<>(4);

        NodoPuzzle e;

        for(Accion accion : AccionesPuzzle()){
            e = sucesor(estado.getEstado(), accion);
            if(!e.equals(estado.getEstado())){
                Vector<Accion> path = estado.getPath();
                path.addElement(accion);
                V.add(new Arco<>(e, path, estado.costoTotal() + accion.cost()));
            }
        }

        return V;
    }
}
