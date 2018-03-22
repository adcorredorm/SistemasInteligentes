package Nreinas;

import busquedaLocal.AscensoColina;
import busquedaLocal.Main;

public class Aplicar {

    public static void main(String[] args) {
        new Main(new AscensoColina(), new Atacadas(), new Inicial(8), new VecinoInformado()).aplicar();
    }
}
