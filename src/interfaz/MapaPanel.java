
package interfaz;

import estructura.ResultadoRuta;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

public class MapaPanel extends JPanel {

    private Image mapa;
    private ArrayList<Marcador> marcadores;

    private Marcador origen;
    private Marcador destino;

    private ResultadoRuta rutaCalculada;
    private int mapaX;
    private int mapaY;
    private int mapaAncho;
    private int mapaAlto;

    public MapaPanel() {

        ImageIcon imagen = new ImageIcon(
                getClass().getResource("/imagenes/mapa_itsur.png")
        );

        mapa = imagen.getImage();

        marcadores = new ArrayList<>();

        agregarMarcadores();

        setPreferredSize(new Dimension(1027, 510));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarMarcador(e.getX(), e.getY());
            }
        });
    }
    
    public void setRuta(ResultadoRuta ruta) {
        this.rutaCalculada = ruta;
        repaint();
    }
    
    private Marcador buscarMarcador(String id) {

        for (Marcador marcador : marcadores) {

            if (marcador.id.equals(id)) {
                return marcador;
            }
        }

        return null;
    }
    private void dibujarRuta(Graphics2D g2) {

        if (rutaCalculada == null) {
                return;
        }

        ArrayList<modelo.Lugar> lugares = rutaCalculada.getRuta();

        g2.setColor(Color.ORANGE);
        g2.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));

        for (int i = 0; i < lugares.size() - 1; i++) {

            Marcador primero = buscarMarcador(lugares.get(i).getId());

            Marcador segundo = buscarMarcador(lugares.get(i + 1).getId());

            if (primero != null && segundo != null) {

                int x1 = mapaX + primero.x * mapaAncho / 1027;
                int y1 = mapaY + primero.y * mapaAlto / 510;

                int x2 = mapaX + segundo.x * mapaAncho / 1027;
                int y2 = mapaY + segundo.y * mapaAlto / 510;

                g2.drawLine(x1, y1, x2, y2);
            }
        }
    }
    private void agregarMarcadores() {

        marcadores.add(new Marcador("casetaPrincipal",
            "Caseta de vigilancia de la puerta principal", 522, 84));

        marcadores.add(new Marcador("casetaEst",
            "Caseta de vigilancia del estacionamiento", 659, 88));

        marcadores.add(new Marcador("estacionamiento",
                "Estacionamiento", 772, 145));

        marcadores.add(new Marcador("biblioteca",
                "Biblioteca", 454, 112));

        marcadores.add(new Marcador("palapas",
                "Palapas", 443, 184));

        marcadores.add(new Marcador("vinculacion",
                "Edificio de Vinculacion", 335, 170));

        marcadores.add(new Marcador("audVinculacion",
                "Auditorio de Vinculacion", 299, 180));

        marcadores.add(new Marcador("cocinas",
                "Cocinas de Gastronomia", 297, 207));

        marcadores.add(new Marcador("cabana",
                "Cabana de Gastronomia", 308, 270));

        marcadores.add(new Marcador("direccion",
                "Direccion", 508, 237));

        marcadores.add(new Marcador("edificioA",
                "Edificio A", 494, 254));

        marcadores.add(new Marcador("edificioB",
                "Edificio B", 603, 248));

        marcadores.add(new Marcador("edificioE",
                "Edificio E", 452, 364));

        marcadores.add(new Marcador("cafeteria",
                "Cafeteria", 491, 421));

        marcadores.add(new Marcador("futbol",
                "Cancha de Futbol", 735, 344));

        marcadores.add(new Marcador("tics",
                "Edificio de TICS", 557, 364));

        marcadores.add(new Marcador("audPrincipal",
                "Auditorio Principal", 338, 387));

        marcadores.add(new Marcador("basketball",
                "Canchas de Basketball", 374, 418));

        marcadores.add(new Marcador("cocinas",
                "Cocinas de Gastronomia", 297, 207));
    }

    private void seleccionarMarcador(int mouseX, int mouseY) {

        for (Marcador marcador : marcadores) {

            int x = mapaX + marcador.x * mapaAncho / 1027;
            int y = mapaY + marcador.y * mapaAlto / 510;

            double distancia = Math.hypot(mouseX - x, mouseY - y);

            if (distancia <= 18) {

                if (origen == null) {
                    origen = marcador;
                } else if (destino == null) {
                    destino = marcador;
                } else {
                    origen = marcador;
                    destino = null;
                }

                repaint();
                return;
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        int anchoOriginal = mapa.getWidth(this);
        int altoOriginal = mapa.getHeight(this);

        double escala = Math.min(
                (double) getWidth() / anchoOriginal,
                (double) getHeight() / altoOriginal
        );

        mapaAncho = (int) (anchoOriginal * escala);
        mapaAlto = (int) (altoOriginal * escala);

        mapaX = (getWidth() - mapaAncho) / 2;
        mapaY = (getHeight() - mapaAlto) / 2;

        g2.drawImage(
                mapa,
                mapaX,
                mapaY,
                mapaAncho,
                mapaAlto,
                this
        );
        
        dibujarRuta(g2);
        
        for (Marcador marcador : marcadores) {

            int x = mapaX + marcador.x * mapaAncho / 1027;
            int y = mapaY + marcador.y * mapaAlto / 510;

            if (marcador == origen) {
                g2.setColor(Color.GREEN);
            } else if (marcador == destino) {
                g2.setColor(Color.RED);
            } else {
                g2.setColor(Color.BLUE);
            }

            g2.fillOval(x - 9, y - 9, 18, 18);

            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2));
            g2.drawOval(x - 9, y - 9, 18, 18);
        }
    }

    private class Marcador {

        String id;
        String nombre;
        int x;
        int y;

        public Marcador(String id, String nombre, int x, int y) {
            this.id = id;
            this.nombre = nombre;
            this.x = x;
            this.y = y;
        }
    }
    
    public String getOrigenId() {
        return origen == null ? null : origen.id;
    }

    public String getDestinoId() {
        return destino == null ? null : destino.id;
    }
}
