
package app;

import estructura.Grafo;
import modelo.Lugar;
import interfaz.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class Main {

    public static Grafo crearGrafo() {

        Grafo grafo = new Grafo();

        // Crear los lugares del ITSUR.
        Lugar biblioteca = new Lugar("biblioteca", "Biblioteca");
        Lugar cafeteria = new Lugar("cafeteria", "Cafeteria");
        Lugar direccion = new Lugar("direccion", "Direccion");
        Lugar edificioA = new Lugar("edificioA", "Edificio A");
        Lugar edificioB = new Lugar("edificioB", "Edificio B");
        Lugar edificioE = new Lugar("edificioE", "Edificio E");
        Lugar vinculacion = new Lugar("vinculacion", "Edificio de Vinculacion");
        Lugar palapas = new Lugar("palapas", "Palapas");
        Lugar tics = new Lugar("tics", "Edificio de TICS");
        Lugar futbol = new Lugar("futbol", "Cancha de Futbol");
        Lugar basketball = new Lugar("basketball", "Canchas de Basketball");
        Lugar estacionamiento = new Lugar("estacionamiento", "Estacionamiento");
        Lugar audVinculacion = new Lugar("audVinculacion", "Auditorio de Vinculacion");
        Lugar audPrincipal = new Lugar("audPrincipal", "Auditorio Principal");
        Lugar casetaEst = new Lugar("casetaEst", "Caseta de vigilancia del estacionamiento");
        Lugar casetaPrincipal = new Lugar("casetaPrincipal", "Caseta de vigilancia de la puerta principal");
        Lugar cocinas = new Lugar("cocinas", "Cocinas de Gastronomia");
        Lugar cabana = new Lugar("cabana", "Cabana de Gastronomia");

        // Agregar lugares.
        grafo.agregarLugar(biblioteca);
        grafo.agregarLugar(cafeteria);
        grafo.agregarLugar(direccion);
        grafo.agregarLugar(edificioA);
        grafo.agregarLugar(edificioB);
        grafo.agregarLugar(edificioE);
        grafo.agregarLugar(vinculacion);
        grafo.agregarLugar(palapas);
        grafo.agregarLugar(tics);
        grafo.agregarLugar(futbol);
        grafo.agregarLugar(basketball);
        grafo.agregarLugar(estacionamiento);
        grafo.agregarLugar(audVinculacion);
        grafo.agregarLugar(audPrincipal);
        grafo.agregarLugar(casetaEst);
        grafo.agregarLugar(casetaPrincipal);
        grafo.agregarLugar(cocinas);
        grafo.agregarLugar(cabana);

        // Conexiones del ITSUR.
        grafo.conectar("audPrincipal", "basketball", 22.81);

        grafo.conectar("basketball", "cafeteria", 60.37);
        grafo.conectar("basketball", "edificioE", 47.99);

        grafo.conectar("cafeteria", "edificioE", 37.02);
        grafo.conectar("cafeteria", "tics", 43.4);

        grafo.conectar("tics", "futbol", 91.01);
        grafo.conectar("tics", "edificioB", 60.46);
        grafo.conectar("tics", "edificioA", 63.03);
        grafo.conectar("tics", "edificioE", 53.4);

        grafo.conectar("futbol", "edificioB", 84.26);

        grafo.conectar("edificioB", "estacionamiento", 97.94);
        grafo.conectar("edificioB", "casetaEst", 82.18);
        grafo.conectar("edificioB", "casetaPrincipal", 93.79);
        grafo.conectar("edificioB", "edificioA", 54.38);

        grafo.conectar("estacionamiento", "casetaEst", 63.31);

        grafo.conectar("edificioA", "direccion", 12.96);
        grafo.conectar("edificioA", "edificioE", 56.82);
        grafo.conectar("edificioA", "palapas", 41.31);

        grafo.conectar("palapas", "vinculacion", 55.78);
        grafo.conectar("palapas", "biblioteca", 39.46);
        grafo.conectar("palapas", "casetaPrincipal", 65.43);

        grafo.conectar("biblioteca", "casetaPrincipal", 34.96);

        grafo.conectar("vinculacion", "audVinculacion", 19.26);
        grafo.conectar("vinculacion", "cocinas", 29.23);

        grafo.conectar("audVinculacion", "cocinas", 14.17);

        grafo.conectar("cocinas", "cabana", 30.34);

        return grafo;
    }

    public static void main(String[] args) {

    SwingUtilities.invokeLater(() -> {

        Grafo grafo = crearGrafo();

            VentanaPrincipal ventana = new VentanaPrincipal(grafo);
            ventana.setVisible(true);
        });
    }
}
