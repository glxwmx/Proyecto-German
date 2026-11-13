package estructura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import modelo.Lugar;
import modelo.Arista;

public class Grafo {

    private HashMap<String, Lugar> lugares;

    private HashMap<String, ArrayList<Arista>> adyacencia;

    public Grafo() {
        lugares = new HashMap<>();
        adyacencia = new HashMap<>();
    }

    public void agregarLugar(Lugar lugar) {

        String id = lugar.getId();

        if (!lugares.containsKey(id)) {
            lugares.put(id, lugar);
            adyacencia.put(id, new ArrayList<>());
        }
    }

    public void conectar(String origenId, String destinoId,
                         double distancia) {

        if (!lugares.containsKey(origenId)
                || !lugares.containsKey(destinoId)) {
            throw new IllegalArgumentException(
                    "Uno de los lugares no existe."
            );
        }

        if (distancia <= 0) {
            throw new IllegalArgumentException(
                    "La distancia debe ser mayor que cero."
            );
        }

        Lugar destino = lugares.get(destinoId);
        Lugar origen = lugares.get(origenId);

        // Conexión de origen hacia destino.
        adyacencia.get(origenId).add(
                new Arista(destino, distancia)
        );

        // Conexión de destino hacia origen.
        adyacencia.get(destinoId).add(
                new Arista(origen, distancia)
        );
    }

    public void mostrarGrafo() {

        for (Map.Entry<String, ArrayList<Arista>> entrada
                : adyacencia.entrySet()) {

            String id = entrada.getKey();
            ArrayList<Arista> conexiones = entrada.getValue();

            System.out.println(
                    lugares.get(id).getNombre() + ":"
            );

            for (Arista arista : conexiones) {
                System.out.println("   " + arista);
            }

            System.out.println();
        }
    }

    public Lugar obtenerLugar(String id) {
        return lugares.get(id);
    }

    public ArrayList<Arista> obtenerAdyacentes(String id) {
        return adyacencia.get(id);
    }
}

