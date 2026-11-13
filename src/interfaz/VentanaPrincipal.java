
package interfaz;

import estructura.Grafo;
import estructura.Dijkstra;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

    private Grafo grafo;
    private MapaPanel mapaPanel;
    private Dijkstra dijkstra;

    public VentanaPrincipal(Grafo grafo) {

        this.grafo = grafo;

        dijkstra = new Dijkstra();

        setTitle("Sistema de rutas ITSUR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 750);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Panel del mapa.
        mapaPanel = new MapaPanel();
        add(mapaPanel, BorderLayout.CENTER);

        // Panel inferior.
        JPanel panelControles = new JPanel();

        JLabel instrucciones = new JLabel(
                "Selecciona un origen y un destino en el mapa."
        );

        JButton botonRuta = new JButton("Buscar ruta");

        botonRuta.addActionListener(e -> buscarRuta());

        panelControles.add(instrucciones);
        panelControles.add(botonRuta);

        add(panelControles, BorderLayout.SOUTH);
    }

    private void buscarRuta() {

        String origen = mapaPanel.getOrigenId();
        String destino = mapaPanel.getDestinoId();

        if (origen == null || destino == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un origen y un destino.",
                    "Seleccion incompleta",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (origen.equals(destino)) {

            JOptionPane.showMessageDialog(
                    this,
                    "El origen y el destino deben ser diferentes."
            );

            return;
        }

        // Ejecutar Dijkstra.
        dijkstra.mostrarRutaMasCorta(grafo, origen, destino);
    }
}
