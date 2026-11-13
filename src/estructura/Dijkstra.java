
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

    public ResultadoRuta calcularRuta(
            Grafo grafo, String origenId, String destinoId) {

        if (grafo.obtenerLugar(origenId) == null
                || grafo.obtenerLugar(destinoId) == null) {
            return null;
        }

        HashMap<String, Double> distancias = new HashMap<>();
        HashMap<String, String> anteriores = new HashMap<>();
        Set<String> visitados = new HashSet<>();

        distancias.put(origenId, 0.0);

        while (true) {

            String actual = obtenerMenorNoVisitado(
                    distancias, visitados
            );

            if (actual == null) {
                break;
            }

            if (actual.equals(destinoId)) {
                break;
            }

            visitados.add(actual);

            ArrayList<Arista> conexiones =
                    grafo.obtenerAdyacentes(actual);

            for (Arista arista : conexiones) {

                String vecino = arista.getDestino().getId();

                if (visitados.contains(vecino)) {
                    continue;
                }

                double nuevaDistancia =
                        distancias.get(actual) + arista.getPeso();

                double distanciaAnterior =
                        distancias.getOrDefault(
                                vecino,
                                Double.POSITIVE_INFINITY
                        );

                if (nuevaDistancia < distanciaAnterior) {

                    distancias.put(vecino, nuevaDistancia);
                    anteriores.put(vecino, actual);
                }
            }
        }

        if (!distancias.containsKey(destinoId)) {
            return null;
        }

        ArrayList<Lugar> ruta = new ArrayList<>();

        String actual = destinoId;

        while (actual != null) {

            ruta.add(grafo.obtenerLugar(actual));

            if (actual.equals(origenId)) {
                break;
            }

            actual = anteriores.get(actual);
        }

        if (!ruta.get(ruta.size() - 1).getId().equals(origenId)) {
            return null;
        }

        Collections.reverse(ruta);

        return new ResultadoRuta(
                ruta,
                distancias.get(destinoId)
        );
    }

    private String obtenerMenorNoVisitado(
            HashMap<String, Double> distancias,
            Set<String> visitados) {

        String menor = null;

        double distanciaMinima =
                Double.POSITIVE_INFINITY;

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
