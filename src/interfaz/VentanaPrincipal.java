package interfaz;

import estructura.ResultadoRuta;
import estructura.Grafo;
import estructura.Dijkstra;
import modelo.Lugar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaPrincipal extends JFrame {

    private Grafo grafo;
    private MapaPanel mapaPanel;
    private Dijkstra dijkstra;

    private JLabel etiquetaOrigen;
    private JLabel etiquetaDestino;
    private JLabel etiquetaResultado;

    private JPanel tarjetaResultado;

    public VentanaPrincipal(Grafo grafo) {

        this.grafo = grafo;
        this.dijkstra = new Dijkstra();

        setTitle("ITSUR | Rutas inteligentes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 780);
        setMinimumSize(new Dimension(900, 700));
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(new Color(241, 245, 249));

        // Encabezado.
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(new Color(20, 45, 78));
        encabezado.setBorder(
                BorderFactory.createEmptyBorder(18, 25, 18, 25)
        );

        JLabel titulo = new JLabel("ITSUR | Rutas inteligentes");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 23));

        JLabel subtitulo = new JLabel(
                "Sistema de navegacion del campus"
        );
        subtitulo.setForeground(new Color(210, 220, 235));
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JPanel textosEncabezado = new JPanel(
                new BorderLayout(0, 4)
        );
        textosEncabezado.setOpaque(false);
        textosEncabezado.add(titulo, BorderLayout.NORTH);
        textosEncabezado.add(subtitulo, BorderLayout.SOUTH);

        encabezado.add(textosEncabezado, BorderLayout.WEST);

        add(encabezado, BorderLayout.NORTH);

        // Mapa.
        mapaPanel = new MapaPanel();
        mapaPanel.setBackground(new Color(241, 245, 249));

        JPanel contenedorMapa = new JPanel(new BorderLayout());
        contenedorMapa.setBackground(new Color(241, 245, 249));
        contenedorMapa.setBorder(
                BorderFactory.createEmptyBorder(12, 15, 5, 15)
        );
        contenedorMapa.add(mapaPanel, BorderLayout.CENTER);

        add(contenedorMapa, BorderLayout.CENTER);

        // Panel inferior.
        JPanel panelInferior = new JPanel(new BorderLayout(10, 10));
        panelInferior.setBackground(new Color(241, 245, 249));
        panelInferior.setBorder(
                BorderFactory.createEmptyBorder(10, 20, 18, 20)
        );

        // Informacion de origen y destino.
        JPanel panelSeleccion = new JPanel(new FlowLayout(
                FlowLayout.CENTER, 35, 8
        ));
        panelSeleccion.setBackground(Color.WHITE);
        panelSeleccion.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 226, 234)
                )
        );

        etiquetaOrigen = new JLabel("Origen: Sin seleccionar");
        etiquetaDestino = new JLabel("Destino: Sin seleccionar");

        etiquetaOrigen.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );
        etiquetaDestino.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        etiquetaOrigen.setForeground(new Color(22, 130, 75));
        etiquetaDestino.setForeground(new Color(190, 55, 55));

        panelSeleccion.add(etiquetaOrigen);
        panelSeleccion.add(etiquetaDestino);

        panelInferior.add(panelSeleccion, BorderLayout.NORTH);

        // Boton de busqueda.
        JButton botonRuta = new JButton("BUSCAR RUTA");
        botonRuta.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );
        botonRuta.setForeground(Color.WHITE);
        botonRuta.setBackground(new Color(37, 99, 235));
        botonRuta.setFocusPainted(false);
        botonRuta.setBorderPainted(false);
        botonRuta.setPreferredSize(new Dimension(190, 42));

        botonRuta.addActionListener(e -> buscarRuta());

        JPanel panelBoton = new JPanel(new FlowLayout(
                FlowLayout.CENTER, 0, 5
        ));
        panelBoton.setOpaque(false);
        panelBoton.add(botonRuta);

        panelInferior.add(panelBoton, BorderLayout.CENTER);

        // Tarjeta de resultado.
        tarjetaResultado = new JPanel(new BorderLayout());
        tarjetaResultado.setBackground(Color.WHITE);
        tarjetaResultado.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 226, 234)
                        ),
                        BorderFactory.createEmptyBorder(10, 15, 10, 15)
                )
        );

        etiquetaResultado = new JLabel(
                "Selecciona dos lugares para calcular una ruta.",
                SwingConstants.CENTER
        );
        etiquetaResultado.setFont(
                new Font("SansSerif", Font.PLAIN, 14)
        );
        etiquetaResultado.setForeground(new Color(90, 100, 115));

        tarjetaResultado.add(
                etiquetaResultado,
                BorderLayout.CENTER
        );

        panelInferior.add(tarjetaResultado, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private void buscarRuta() {

        String origenId = mapaPanel.getOrigenId();
        String destinoId = mapaPanel.getDestinoId();

        if (origenId == null || destinoId == null) {

            etiquetaResultado.setText(
                    "Selecciona un origen y un destino."
            );

            return;
        }

        if (origenId.equals(destinoId)) {

            etiquetaResultado.setText(
                    "El origen y el destino deben ser diferentes."
            );

            return;
        }

        Lugar origen = grafo.obtenerLugar(origenId);
        Lugar destino = grafo.obtenerLugar(destinoId);

        if (origen == null || destino == null) {

            etiquetaResultado.setText(
                    "Error: uno de los lugares no existe."
            );

            return;
        }

        etiquetaOrigen.setText(
                "Origen: " + origen.getNombre()
        );

        etiquetaDestino.setText(
                "Destino: " + destino.getNombre()
        );

        ResultadoRuta resultado =
                dijkstra.calcularRuta(grafo, origenId, destinoId);

        if (resultado == null) {

            etiquetaResultado.setText(
                    "No existe una ruta entre esos lugares."
            );

            return;
        }

        mapaPanel.setRuta(resultado);

        StringBuilder recorrido = new StringBuilder();

        for (int i = 0; i < resultado.getRuta().size(); i++) {

            recorrido.append(
                    resultado.getRuta().get(i).getNombre()
            );

            if (i < resultado.getRuta().size() - 1) {
                recorrido.append(" -> ");
            }
        }

        String distancia = String.format(
                "%.2f", resultado.getDistancia()
        );

        etiquetaResultado.setText(
                "<html><div style='text-align:center;'>"
                + "<b>Ruta mas corta</b><br>"
                + recorrido
                + "<br><font color='#15803D'><b>"
                + distancia + " metros</b></font>"
                + "</div></html>"
        );
    }
}
