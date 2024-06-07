import fr.ulille.but.sae_s2_2024.ModaliteTransport;

public class Correspondance {
    private String ville;
    private ModaliteTransport ModDepart;
    private ModaliteTransport ModArrivee;
    private double temps;
    private double co2;
    private double prix;

    public Correspondance(String ville, ModaliteTransport ModDepart, ModaliteTransport ModArrivee, int temps, double co2, double prix) {
        this.ville = ville;
        this.ModDepart = ModDepart;
        this.ModArrivee = ModArrivee;
        this.temps = temps;
        this.co2 = co2;
        this.prix = prix;
    }

    public String getVille() {
        return ville;
    }

    public ModaliteTransport getDepart() {
        return ModDepart;
    }

    public ModaliteTransport getArrivee() {
        return ModArrivee;
    }

    public double getTemps() {
        return temps;
    }

    public double getCo2() {
        return co2;
    }

    public double getPrix() {
        return prix;
    }
    
}
