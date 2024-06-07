import java.util.HashMap;

import fr.ulille.but.sae_s2_2024.*;

/**
 * @author Hugo Debuyser, Gaël Dierynck
 * La classe MonTroncon permet de décrire un tronçon de transport
 * avec un lieu de départ, un lieu d'arrivée, une modalité de transport et un coût.
 * Getter et Setter fournis.
 */
public class MonTroncon implements Trancon {
    /**
     * Le lieu de départ du tronçon.
     */
    private MonLieu depLieu;

    /**
     * Le lieu d'arrivée du tronçon.
     */
    private MonLieu aLieu;

    /**
     * La modalité de transport pour ce tronçon.
     */
    private ModaliteTransport modaliteTransport;

    /**
     * Le coût associé à ce tronçon.
     * Il est stocké dans une HashMap avec des types de coûts et leurs valeurs.
     */
    private HashMap<TypeCout, Double> cout;

    /**
     * Constructeur de la classe MonTroncon.
     * @param depLieu Le lieu de départ.
     * @param aLieu Le lieu d'arrivée.
     * @param modaliteTransport La modalité de transport.
     * @param cout Le coût associé au tronçon.
     */
    public MonTroncon(MonLieu depLieu, MonLieu aLieu, ModaliteTransport modaliteTransport, HashMap<TypeCout, Double> cout) {
        this.depLieu = depLieu;
        this.aLieu = aLieu;
        this.modaliteTransport = modaliteTransport;
        this.cout = cout;
    }

    /**
     * Retourne le lieu de départ du tronçon.
     * @return Le lieu de départ.
     */
    public MonLieu getDepart() {
        return this.depLieu;
    }

    /**
     * Retourne le lieu d'arrivée du tronçon.
     * @return Le lieu d'arrivée.
     */
    public MonLieu getArrivee() {
        return this.aLieu;
    }

    /**
     * Retourne la modalité de transport pour ce tronçon.
     * @return La modalité de transport.
     */
    public ModaliteTransport getModalite() {
        return this.modaliteTransport;
    }

    /**
     * Retourne le coût associé à ce tronçon.
     * @return Une HashMap contenant les types de coûts et leurs valeurs.
     */
    public HashMap<TypeCout, Double> getCout() {
        return this.cout;
    }

    public boolean compareModalite(MonTroncon autre) {
        return this.getModalite().equals(autre.getModalite());
    }

    /**
     * Retourne une représentation sous forme de chaîne du tronçon de transport.
     * @return Une description textuelle du tronçon.
     */
    public String toString() {
        return "De " + this.depLieu + " à " + this.aLieu + " en " + this.modaliteTransport;
    }
}