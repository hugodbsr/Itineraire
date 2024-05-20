import fr.ulille.but.sae_s2_2024.ModaliteTransport;

/**
 * Classe Voyageur
 * @author Hugo Debuyser, Gaël Dierynck, Maxence Antoine
 * Permet de décrire un voyageur type, avec un nom, une préférence de coût et un moyen de transport
 * Getter et Setter fournis. 
 */
public class Voyageur {
    /**
     * Le nom du voyageur.
     */
    private String nom;

    /**
     * La préférence de coût du voyageur.
     */
    private TypeCout preference;

    /**
     * Le moyen de transport préféré du voyageur.
     */
    private ModaliteTransport transport;

    /**
     * La contrainte de prix du voyageur.
     */
    private int prix;

    /**
     * La contrainte de CO2 du voyageur.
     */
    private int co2;

    /**
     * La contrainte de temps du voyageur.
     */
    private int temps;

    /**
     * Constructeur de la classe Voyageur.
     * @param nom Le nom du voyageur.
     * @param preference La préférence de coût du voyageur.
     * @param transport Le moyen de transport préféré du voyageur.
     * @param prix La contrainte de prix du voyageur.
     * @param co2 La contrainte de CO2 du voyageur.
     * @param temps La contrainte de temps du voyageur.
     */
    public Voyageur(String nom, TypeCout preference, ModaliteTransport transport, int prix, int co2, int temps) {
        this.nom = nom;
        this.preference = preference;
        this.transport = transport;
        this.prix = prix;
        this.co2 = co2;
        this.temps = temps;
    }

    /**
     * Retourne le nom du voyageur.
     * @return Le nom du voyageur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la préférence de coût du voyageur.
     * @return La préférence de coût.
     */
    public TypeCout getPreference() {
        return preference;
    }

    /**
     * Retourne le moyen de transport préféré du voyageur.
     * @return Le moyen de transport préféré.
     */
    public ModaliteTransport getTransport() {
        return transport;
    }

    /**
     * Retourne la contrainte de CO2 du voyageur.
     * @return La contrainte de CO2.
     */
    public int getCo2() {
        return co2;
    }

    /**
     * Retourne la contrainte de prix du voyageur.
     * @return La contrainte de prix.
     */
    public int getPrix() {
        return prix;
    }

    /**
     * Retourne la contrainte de temps du voyageur.
     * @return La contrainte de temps.
     */
    public int getTemps() {
        return temps;
    }

    /**
     * Définit le nom du voyageur.
     * @param nom Le nouveau nom du voyageur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Définit la préférence de coût du voyageur.
     * @param preference La nouvelle préférence de coût.
     */
    public void setPreference(TypeCout preference) {
        this.preference = preference;
    }

    /**
     * Définit le moyen de transport préféré du voyageur.
     * @param transport Le nouveau moyen de transport préféré.
     */
    public void setTransport(ModaliteTransport transport) {
        this.transport = transport;
    }

    /**
     * Définit la contrainte de CO2 du voyageur.
     * @param co2 La nouvelle contrainte de CO2.
     */
    public void setCo2(int co2) {
        this.co2 = co2;
    }

    /**
     * Définit la contrainte de prix du voyageur.
     * @param prix La nouvelle contrainte de prix.
     */
    public void setPrix(int prix) {
        this.prix = prix;
    }

    /**
     * Définit la contrainte de temps du voyageur.
     * @param temps La nouvelle contrainte de temps.
     */
    public void setTemps(int temps) {
        this.temps = temps;
    }
}