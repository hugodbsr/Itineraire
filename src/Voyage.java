import java.util.List;

import fr.ulille.but.sae_s2_2024.*;

public class Voyage {
    /**
     * Le lieu d'arrivée du voyage.
     */
    private MonLieu arrivee;

    /**
     * Le lieu de départ du voyage.
     */
    private MonLieu depart;

    /**
     * Constructeur de la classe Voyage.
     * @param arrivee Le lieu d'arrivée du voyage.
     * @param depart Le lieu de départ du voyage.
     */
    public Voyage(MonLieu depart, MonLieu arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
    }

    /**
     * Retourne le lieu d'arrivée du voyage.
     * @return Le lieu d'arrivée.
     */
    public MonLieu getArrivee() {
        return arrivee;
    }

    /**
     * Retourne le lieu de départ du voyage.
     * @return Le lieu de départ.
     */
    public MonLieu getDepart() {
        return depart;
    }

    /**
     * Définit le lieu d'arrivée du voyage.
     * @param arrivee Le nouveau lieu d'arrivée.
     */
    public void setArrivee(MonLieu arrivee) {
        this.arrivee = arrivee;
    }

    /**
     * Définit le lieu de départ du voyage.
     * @param depart Le nouveau lieu de départ.
     */
    public void setDepart(MonLieu depart) {
        this.depart = depart;
    }

    /**
     * Retourne une liste des plus courts chemins basés sur différents critères de coût.
     * Les critères sont prix, CO2 et temps. Les chemins qui ne respectent pas les contraintes du voyageur sont supprimés.
     * @param plateforme La plateforme de transport.
     * @param voyageur Le voyageur.
     * @return La liste des chemins les plus courts selon le critère de préférence du voyageur.
     */
    public List<Chemin> plusCourtChemins(Plateforme plateforme, Voyageur voyageur) throws RoadException {
        List<Chemin> listPrix = null;
        List<Chemin> listCo2 = null;
        List<Chemin> listTemps = null;

        try {
            listPrix = AlgorithmeKPCC.kpcc(plateforme.getGrapheAvecCritereEtTransports(TypeCout.PRIX, voyageur.getTransports()), this.getDepart(), this.getArrivee(), 4);
            listCo2 = AlgorithmeKPCC.kpcc(plateforme.getGrapheAvecCritereEtTransports(TypeCout.CO2, voyageur.getTransports()), this.getDepart(), this.getArrivee(), 4);
            listTemps = AlgorithmeKPCC.kpcc(plateforme.getGrapheAvecCritereEtTransports(TypeCout.TEMPS, voyageur.getTransports()), this.getDepart(), this.getArrivee(), 4);
        } catch (RoadException e) {
            throw new RoadException("Aucun chemin trouvé pour le voyage : " + e.getMessage());
        }

        for (int i = listPrix.size() - 1; i >= 0; i--) {
            if ((listPrix.get(i).poids() > voyageur.getPrix() && voyageur.getPrix() != -1) ||
                (listCo2.get(i).poids() > voyageur.getCo2() && voyageur.getCo2() != -1) ||
                (listTemps.get(i).poids() > voyageur.getTemps() && voyageur.getTemps() != -1)) {
                listPrix.remove(i);
                listCo2.remove(i);
                listTemps.remove(i);
            }
        }

        List<Chemin> list = listPrix;
        if (voyageur.getPreference() == TypeCout.PRIX) list = listPrix;
        if (voyageur.getPreference() == TypeCout.CO2) list = listCo2;
        if (voyageur.getPreference() == TypeCout.TEMPS) list = listTemps;

        return list;
    }

    public String toString(List<Chemin> chemins, Plateforme plateforme) {
        String representation = "";
        for (int i = 0; i < chemins.size(); i++) {
            Chemin chemin = chemins.get(i);
            List<Trancon> aretes = chemin.aretes();
            double poids = chemin.poids();
            double prixTotal = poids;
            String trajet = "Trajet de " + ((MonLieu)aretes.get(0).getDepart()).getNom() + " à " + ((MonLieu)aretes.get(aretes.size() - 1).getArrivee()).getNom() + ": ";
            String changements = "Départ en " + aretes.get(0).getModalite() + " à " + ((MonLieu)aretes.get(0).getDepart()).getNom();
            for (int j = 1; j < aretes.size(); j++) {
                Trancon trancon = aretes.get(j);
                if (!trancon.getModalite().equals(aretes.get(j - 1).getModalite())) {
                    changements += ", changement à " + ((MonLieu)trancon.getArrivee()).getNom() + " en " + trancon.getModalite();
                    Correspondance correspondance = plateforme.getCorrespondance(trancon.getArrivee(), trancon.getModalite());
                    if (correspondance != null) {
                        prixTotal += correspondance.getPrix();
                    }
                }
            }
            representation += trajet + changements + ". Cout total " + prixTotal + System.getProperty("line.separator");
        }
        return representation;
    }

}
