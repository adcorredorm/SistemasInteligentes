package Puzzle16;

import busqueda.*;

import java.util.Vector;

public class Sucesores implements Sucesor<NodoPuzzle>{

    //public static final Accion NO_OP = new Accion("NO_OP", 0);
    public static final Accion UP = new Accion("UP", 1);
    public static final Accion DOWN = new Accion("DOWN", 1);
    public static final Accion LEFT = new Accion("LEFT", 1);
    public static final Accion RIGHT = new Accion("RIGHT", 1);

    public static final Accion[] AccionesPuzzle = new Accion[]{UP, DOWN, LEFT, RIGHT};

    public static NodoPuzzle sucesor(NodoPuzzle estado, Accion action) {

        long puzzle = estado.getPuzzle();
        int n = estado.n();
        int[] pos = estado.getPos();
        int zeroPos = 4*pos[0]*n + pos[1]*n;

        String auxS;

        switch (action.getCode()){

            case "UP":
                if(pos[0] == 0)   return estado;
                else{
                    auxS = NodoPuzzle.toBin(puzzle, 64).substring(4*(pos[0]-1)*n + pos[1]*n, 4*(pos[0]-1)*n + pos[1]*n + 4);
                    puzzle = moveValue(puzzle, auxS, 4*(pos[0]-1)*n + pos[1]*n, zeroPos);
                    pos[0]--;
                }
                break;

            case "DOWN":
                if(pos[0] == n-1)   return estado;
                else{
                    auxS = NodoPuzzle.toBin(puzzle, 64).substring(4*(pos[0]+1)*n + pos[1]*n, 4*(pos[0]+1)*n + pos[1]*n + 4);
                    puzzle = moveValue(puzzle, auxS, 4*(pos[0]+1)*n + pos[1]*n, zeroPos);
                    pos[0]++;
                }
                break;

            case "LEFT":
                if(pos[1] == 0)   return estado;
                else{
                    auxS = NodoPuzzle.toBin(puzzle, 64).substring(zeroPos - 4, zeroPos);
                    puzzle = moveValue(puzzle, auxS, zeroPos-4, zeroPos);
                    pos[1]--;
                }
                break;

            case "RIGHT":
                if(pos[1] == n-1)   return estado;
                else{
                    auxS = NodoPuzzle.toBin(puzzle, 64).substring(zeroPos + 4, zeroPos + 8);
                    puzzle = moveValue(puzzle, auxS, zeroPos+4, zeroPos);
                    pos[1]++;
                }
                break;

        }
        return new NodoPuzzle(n, puzzle, pos[0], pos[1]);
    }

    private static long moveValue(long puzzle, String bin, int pos_ini, int zeroPos){
        StringBuilder ini = new StringBuilder(bin), f = new StringBuilder(bin);
        while(ini.length() < 64-pos_ini) ini.append("0");
        while(f.length() < 64-zeroPos) f.append("0");
        return puzzle - NodoPuzzle.castBin(ini.toString()) + NodoPuzzle.castBin(f.toString());
    }

    private static Accion contraria(Accion accion){
        switch (accion.getCode()){
            case "UP":
                return DOWN;

            case "DOWN":
                return UP;

            case "LEFT":
                return RIGHT;

            case "RIGHT":
                return LEFT;

        }
        return null;
    }

    @Override
    public Vector<Arco<NodoPuzzle>> obtener(Arco<NodoPuzzle> estado) {
        Vector<Arco<NodoPuzzle>> V = new Vector<>(AccionesPuzzle.length);

        NodoPuzzle e;
        Vector<Accion> path;
        Accion last;
        try{
            last = estado.getPath().lastElement();
        }catch (Exception ex){
            last = null;
        }

        for(Accion accion : AccionesPuzzle){
            e = sucesor(estado.getEstado(), accion);
            if(!e.equals(estado.getEstado()) && !contraria(accion).equals(last)){
                path = estado.getPath();
                path.addElement(accion);
                V.add(new Arco<>(e, path, estado.costoTotal() + accion.cost()));
            }
        }

        return V;
    }
}
