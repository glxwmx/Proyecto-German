package modelo;
public class Arista {
    private Lugar destino;
    private double peso;

    public Arista(Lugar destino, double peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public Lugar getDestino() {
        return destino;
    }

    public void setDestino(Lugar destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    public String toString(){
        return destino.getNombre() + " (" + peso + " m)";
    }
}
