package Poo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Exception.CSVFormatException;
import Exception.RoadException;
import fr.ulille.but.sae_s2_2024.*;

/**
 * La classe Plateforme représente une plateforme de transport.
 * Elle contient des lieux, des tronçons et un voyageur.
 * J'ai spécifié le constructeur et les méthodes car elles traitent des ArrayList et Hashmaps.
 * Auteur: Hugo Debuyser, Gaël Dierynck, Maxence Antoine
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

    private List<Correspondance> correspondances;

    /**
     * Constructeur de la classe Plateforme.
     * @param data les données de la plateforme
     * @param voyageur le voyageur associé à la plateforme
     */
    /* public Plateforme(String[] data) {
        this.lieux = new HashSet<>();
        DataExtract dataExtract = new DataExtract();
        ArrayList<String[]> ventile = dataExtract.ventilation(data);
        if (dataExtract.verificationVentilation(ventile)) {
            this.troncons = new HashSet<>(dataExtract.extraireTroncon(ventile, lieux, this.correspondances));
        }
    } */
    
    public Plateforme(String dataFilePath, String correspondanceFilePath) throws IOException, CSVFormatException {
        this.lieux = new HashSet<>();
        this.troncons = new HashSet<>();
        this.correspondances = new ArrayList<>();
        CsvExtract csvExtract = new CsvExtract();
        ArrayList<String[]> ventile = csvExtract.ventilation(dataFilePath);
        if (csvExtract.verificationVentilation(ventile)) {
            this.troncons.addAll(csvExtract.extraireTroncon(ventile, lieux, new ArrayList<>(this.correspondances)));
        }
        this.correspondances = csvExtract.extraireCorrespondances(correspondanceFilePath);
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
    public MonLieu getLieuNom(String nom) throws RoadException {
        for (Lieu lieu : lieux) {
            if (lieu instanceof MonLieu && ((MonLieu) lieu).getNom().equals(nom)) {
                return (MonLieu) lieu;
            }
        }
        throw new RoadException("Lieu non trouvé avec le nom : " + nom);
    }

    /**
     * Retourne l'ensemble des tronçons de la plateforme.
     * @return l'ensemble des tronçons
     */
    public Set<Trancon> getTroncons() {
        return troncons;
    }

    public List<Correspondance> getCorrespondances() {
        return correspondances;
    }

    /**
     * Retourne le graphe orienté avec les valeurs spécifiées par le critère de coût et les modalités de transport.
     * @param critere le critère de coût pour les tronçons
     * @param transports les modalités de transport préférées
     * @return le graphe orienté avec les valeurs de coût correspondantes
     */
    public MultiGrapheOrienteValue getGrapheAvecCritereEtTransports(TypeCout critere, List<ModaliteTransport> transports) throws RoadException {
        MultiGrapheOrienteValue graphe = new MultiGrapheOrienteValue();
        for (Lieu lieu : this.lieux) {
            graphe.ajouterSommet(lieu);
        }
        for (Trancon troncon : this.troncons) {
            if (transports.contains(troncon.getModalite())) {
                double poids = ((MonTroncon)troncon).getCout().get(critere);
                graphe.ajouterArete(troncon, poids);
            }
        }
        if (graphe.sommets().size() == 0) {
            throw new RoadException("Aucun tronçon trouvé pour les types de transport : " + transports);
        }
        return graphe;
    }

    public Correspondance getCorrespondance(Lieu arrivee, ModaliteTransport modalite) {
        for (Correspondance correspondance : correspondances) {
            if (correspondance.getVille().equals(((MonLieu)arrivee).getNom()) && correspondance.getDepart() == modalite) {
                return correspondance;
            }
        }
        return null;
    }
    
}
