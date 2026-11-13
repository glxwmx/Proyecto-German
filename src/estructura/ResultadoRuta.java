
package estructura;

import java.util.ArrayList;
import modelo.Lugar;

public class ResultadoRuta {

    private ArrayList<Lugar> ruta;
    private double distancia;

    public ResultadoRuta(ArrayList<Lugar> ruta, double distancia) {
        this.ruta = ruta;
        this.distancia = distancia;
    }

    public ArrayList<Lugar> getRuta() {
        return ruta;
    }

    public double getDistancia() {
        return distancia;
    }
}

