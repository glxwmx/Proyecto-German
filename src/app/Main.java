
package app;

import modelo.Lugar;
import estructura.Grafo;
import estructura.Dijkstra;

public class Main {

    public static void main(String[] args) {

        Grafo grafo = new Grafo();

        Lugar biblioteca =
                new Lugar("biblioteca", "Biblioteca");

        Lugar cafeteria =
                new Lugar("cafeteria", "Cafetería");

        Lugar edificioA =
                new Lugar("edificioA", "Edificio A");

        grafo.agregarLugar(biblioteca);
        grafo.agregarLugar(cafeteria);
        grafo.agregarLugar(edificioA);

        grafo.conectar("biblioteca", "cafeteria", 50);
        grafo.conectar("biblioteca", "edificioA", 10);
        grafo.conectar("edificioA", "cafeteria", 15);

        Dijkstra algoritmo = new Dijkstra();

        algoritmo.mostrarRutaMasCorta(
                grafo,
                "biblioteca",
                "cafeteria"
        );
    }
}
