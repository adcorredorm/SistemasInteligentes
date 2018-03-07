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

        int[] V = estado.getPuzzle();
        int[] pos = estado.getPos();
        int n = estado.n();

        String auxS;
        int auxI;


        switch (action.getCode()){

            case "UP":
                if(pos[0] == 0)   return estado;
                else{
                    auxS = NodoPuzzle.toBin(V[pos[0]-1], 16).substring(pos[1]*4, pos[1]*4 + 4);
                    auxI = relativeValue(auxS, ((4*4)-1)-(pos[1]*4)); //Estas formulitas salieron de prueba y error
                    V[pos[0]-1] -= auxI;
                    V[pos[0]] += auxI;
                    pos[0]--;
                }
                break;

            case "DOWN":
                if(pos[0] == n-1)   return estado;
                else{
                    auxS = NodoPuzzle.toBin(V[pos[0]+1], 16).substring(pos[1]*4, pos[1]*4 + 4);
                    auxI = relativeValue(auxS, ((4*4)-1)-(pos[1]*4));
                    V[pos[0]+1] -= auxI;
                    V[pos[0]] += auxI;
                    pos[0]++;
                }
                break;

            case "LEFT":
                if(pos[1] == 0)   return estado;
                else{
                    auxS = NodoPuzzle.toBin(V[pos[0]], 16).substring(pos[1]*4 -4, pos[1]*4);
                    V[pos[0]] -= relativeValue(auxS, ((4*4)-1)-((4*pos[1])-4));
                    V[pos[0]] += relativeValue(auxS, ((4*4)-1)-(4*pos[1]));
                    pos[1]--;
                }
                break;

            case "RIGHT":
                if(pos[1] == n-1)   return estado;
                else{
                    auxS = NodoPuzzle.toBin(V[pos[0]], 16).substring(pos[1]*4 +4, pos[1]*4 +(2*4));
                    V[pos[0]] -= relativeValue(auxS, ((4*4)-1)-((4*pos[1])+4));
                    V[pos[0]] += relativeValue(auxS, ((4*4)-1)-(4*pos[1]));
                    pos[1]++;
                }
                break;

        }
        return new NodoPuzzle(n, V, pos[0], pos[1]);
    }

    private static int relativeValue(String bin, int pot_inicial){
        int value = 0;
        for(char v: bin.toCharArray()){
            if(v == '1') value += Math.pow(2, pot_inicial);
            pot_inicial--;
        }
        return value;
    }

    @Override
    public Vector<Arco<NodoPuzzle>> obtener(Arco<NodoPuzzle> estado) {
        Vector<Arco<NodoPuzzle>> V = new Vector<>(AccionesPuzzle.length);

        NodoPuzzle e;
        Vector<Accion> path;

        for(Accion accion : AccionesPuzzle){
            e = sucesor(estado.getEstado(), accion);
            if(!e.equals(estado.getEstado())){
                path = estado.getPath();
                path.addElement(accion);
                V.add(new Arco<>(e, path, estado.costoTotal() + accion.cost()));
            }
        }

        return V;
    }
}
