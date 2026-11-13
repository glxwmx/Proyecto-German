
package estructura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import modelo.Lugar;
import modelo.Arista;

public class Dijkstra {

    public void mostrarRutaMasCorta(
            Grafo grafo, String origenId, String destinoId) {

        // Verificar que los lugares existan.
        if (grafo.obtenerLugar(origenId) == null
                || grafo.obtenerLugar(destinoId) == null) {

            System.out.println("Error: uno de los lugares no existe.");
            return;
        }

        // Distancia mínima conocida hasta cada lugar.
        HashMap<String, Double> distancias = new HashMap<>();

        // Lugar anterior en la ruta más corta.
        HashMap<String, String> anteriores = new HashMap<>();

        // Lugares que ya fueron procesados.
        Set<String> visitados = new HashSet<>();

        // La distancia del origen hacia sí mismo es cero.
        distancias.put(origenId, 0.0);

        // Buscar rutas mientras existan lugares pendientes.
        while (true) {

            String actual = obtenerMenorNoVisitado(
                    distancias, visitados
            );

            // No quedan lugares alcanzables por procesar.
            if (actual == null) {
                break;
            }

            // Si llegamos al destino, terminamos.
            if (actual.equals(destinoId)) {
                break;
            }

            // Marcar el lugar actual como visitado.
            visitados.add(actual);

            // Revisar las conexiones del lugar actual.
            ArrayList<Arista> conexiones =
                    grafo.obtenerAdyacentes(actual);

            for (Arista arista : conexiones) {

                String vecino = arista.getDestino().getId();

                // No procesar lugares ya visitados.
                if (visitados.contains(vecino)) {
                    continue;
                }

                // Calcular la distancia pasando por el lugar actual.
                double nuevaDistancia =
                        distancias.get(actual) + arista.getPeso();

                double distanciaAnterior =
                        distancias.getOrDefault(
                                vecino, Double.POSITIVE_INFINITY
                        );

                // Actualizar si encontramos un camino más corto.
                if (nuevaDistancia < distanciaAnterior) {

                    distancias.put(vecino, nuevaDistancia);
                    anteriores.put(vecino, actual);
                }
            }
        }

        // Comprobar si existe una ruta hacia el destino.
        if (!distancias.containsKey(destinoId)) {
            System.out.println("No existe una ruta entre esos lugares.");
            return;
        }

        // Reconstruir la ruta desde el destino hasta el origen.
        ArrayList<Lugar> ruta = new ArrayList<>();

        String actual = destinoId;

        while (actual != null) {

            ruta.add(grafo.obtenerLugar(actual));

            if (actual.equals(origenId)) {
                break;
            }

            actual = anteriores.get(actual);
        }

        // Si no llegamos al origen, no existe una ruta válida.
        if (!ruta.get(ruta.size() - 1).getId().equals(origenId)) {
            System.out.println("No existe una ruta entre esos lugares.");
            return;
        }

        // Invertir para mostrar origen -> destino.
        Collections.reverse(ruta);

        // Mostrar los resultados.
        System.out.println("\nRUTA MAS CORTA");
        System.out.println("--------------------");

        for (int i = 0; i < ruta.size(); i++) {

            System.out.print(ruta.get(i).getNombre());

            if (i < ruta.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();

        System.out.println(
                "Distancia total: " + distancias.get(destinoId) + " metros"
        );
    }

    // Encuentra el lugar no visitado con menor distancia.
    private String obtenerMenorNoVisitado(
            HashMap<String, Double> distancias,
            Set<String> visitados) {

        String menor = null;
        double distanciaMinima = Double.POSITIVE_INFINITY;

        for (Map.Entry<String, Double> entrada
                : distancias.entrySet()) {

            String id = entrada.getKey();
            double distancia = entrada.getValue();

            if (!visitados.contains(id)
                    && distancia < distanciaMinima) {

                distanciaMinima = distancia;
                menor = id;
            }
        }

        return menor;
    }
}

