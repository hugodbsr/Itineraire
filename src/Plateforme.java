import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import fr.ulille.but.sae_s2_2024.*;

/**
 * La classe Plateforme représente une plateforme de transport.
 * Elle contient des lieux, des tronçons et un voyageur.
 * J'ai spécifié le constructeur et les méthodes car elles traitent des ArrayList et Hashmaps. 
 * @author Hugo Debuyser, Gaël Dierynck, Maxence Antoine
 */
public class Plateforme {
    /**
     * L'ensemble des lieux de la plateforme.
     */
    private Set<Lieu> lieux;

    /**
     * L'ensemble des tronçons de la plateforme.
     */
    private Set<Trancon> troncons;

    /**
     * Le voyageur associé à la plateforme.
     */
    private Voyageur voyageur;

    /**
     * Constructeur de la classe Plateforme.
     * @param data les données de la plateforme
     * @param voyageur le voyageur associé à la plateforme
     */
    public Plateforme(String[] data, Voyageur voyageur) {
        this.lieux = new HashSet<>();
        DataExtract dataExtract = new DataExtract();
        ArrayList<String[]> ventile = dataExtract.ventilation(data);
        if (dataExtract.verificationVentilation(ventile)) {
            this.troncons = new HashSet<>(dataExtract.extraireTroncon(ventile, lieux));
            this.voyageur = voyageur;
        }
    }

    /**
     * Retourne l'ensemble des lieux de la plateforme.
     * @return l'ensemble des lieux
     */
    public Set<Lieu> getLieux() {
        return lieux;
    }
    
    /**
     * Retourne le lieu avec le nom spécifié.
     * @param nom le nom du lieu à rechercher
     * @return le lieu correspondant au nom, ou null si aucun lieu n'est trouvé
     */
    public MonLieu getLieuNom(String nom) {
        for (Lieu lieu : lieux) {
            if (lieu instanceof MonLieu && ((MonLieu) lieu).getNom().equals(nom)) {
                return (MonLieu) lieu;
            }
        }
        return null;
    }

    /**
     * Retourne l'ensemble des tronçons de la plateforme.
     * @return l'ensemble des tronçons
     */
    public Set<Trancon> getTroncons() {
        return troncons;
    }

    /**
     * Retourne le graphe orienté avec les valeurs spécifiées par le critère de coût.
     * @param critere le critère de coût pour les tronçons
     * @return le graphe orienté avec les valeurs de coût correspondantes
     */
    public MultiGrapheOrienteValue getGraphe(TypeCout critere) {
        MultiGrapheOrienteValue graphe = new MultiGrapheOrienteValue();
    
        for (Lieu lieu : this.lieux) {
            graphe.ajouterSommet(lieu);
        }
    
        for (Trancon troncon : this.troncons) {
            if (troncon.getModalite() == this.voyageur.getTransport()) {
                double poids = ((MonTroncon)troncon).getCout().get(critere);
                graphe.ajouterArete(troncon, poids);
            }
        }
    
        return graphe;
    }
}