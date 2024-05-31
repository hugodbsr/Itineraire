import fr.ulille.but.sae_s2_2024.ModaliteTransport;

public class Correspondance {
    private String ville;
    private ModaliteTransport from;
    private ModaliteTransport to;
    private int minutes;
    private double co2;
    private double euro;

    public Correspondance(String ville, ModaliteTransport from, ModaliteTransport to, int minutes, double co2, double euro) {
        this.ville = ville;
        this.from = from;
        this.to = to;
        this.minutes = minutes;
        this.co2 = co2;
        this.euro = euro;
    }

    public String getVille() {
        return ville;
    }

    public ModaliteTransport getFrom() {
        return from;
    }

    public ModaliteTransport getTo() {
        return to;
    }

    public int getMinutes() {
        return minutes;
    }

    public double getCo2() {
        return co2;
    }

    public double getEuro() {
        return euro;
    }
}
