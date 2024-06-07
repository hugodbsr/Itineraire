import fr.ulille.but.sae_s2_2024.ModaliteTransport;

/**
 * La classe Correspondance représente une correspondance entre deux modes de transport
 * pour se déplacer d'une ville à une autre.
 */
public class Correspondance {
    private String ville;
    private ModaliteTransport modDepart;
    private ModaliteTransport modArrivee;
    private double temps;
    private double co2;
    private double prix;

    /**
     * Constructeur de la classe Correspondance.
     * 
     * @param ville la ville de correspondance
     * @param modDepart le mode de transport de départ
     * @param modArrivee le mode de transport d'arrivée
     * @param temps le temps de trajet en heures
     * @param co2 les émissions de CO2 en kg
     * @param prix le prix du trajet en euros
     */
    public Correspondance(String ville, ModaliteTransport modDepart, ModaliteTransport modArrivee, int temps, double co2, double prix) {
        this.ville = ville;
        this.modDepart = modDepart;
        this.modArrivee = modArrivee;
        this.temps = temps;
        this.co2 = co2;
        this.prix = prix;
    }

    /**
     * Retourne la ville de correspondance.
     * 
     * @return la ville de correspondance
     */
    public String getVille() {
        return ville;
    }

    /**
     * Retourne le mode de transport de départ.
     * 
     * @return le mode de transport de départ
     */
    public ModaliteTransport getDepart() {
        return modDepart;
    }

    /**
     * Retourne le mode de transport d'arrivée.
     * 
     * @return le mode de transport d'arrivée
     */
    public ModaliteTransport getArrivee() {
        return modArrivee;
    }

    /**
     * Retourne le temps de trajet en heures.
     * 
     * @return le temps de trajet en heures
     */
    public double getTemps() {
        return temps;
    }

    /**
     * Retourne les émissions de CO2 en kg.
     * 
     * @return les émissions de CO2 en kg
     */
    public double getCo2() {
        return co2;
    }

    /**
     * Retourne le prix du trajet en euros.
     * 
     * @return le prix du trajet en euros
     */
    public double getPrix() {
        return prix;
    }
    
}
