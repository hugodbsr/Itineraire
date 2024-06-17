package Poo;
import java.io.Serializable;
import java.util.List;

import Exception.RoadException;
import fr.ulille.but.sae_s2_2024.*;

public class Voyage implements Serializable{
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
        return this.arrivee;
    }

    /**
     * Retourne le lieu de départ du voyage.
     * @return Le lieu de départ.
     */
    public MonLieu getDepart() {
        return this.depart;
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

    public String toString(List<Chemin> chemins, Plateforme plateforme, Voyageur voyageur) {
        String renvoie = "Chemins pour se rendre de " + this.depart + " à " + this.arrivee + ":" + System.getProperty("line.separator");
        
        for(int i = 0; i < chemins.size();i++){
            Chemin cheminActu = chemins.get(i);
            renvoie += i+1 + ") Chemin de " + depart.getNom() + " à " + arrivee.getNom() + ": ";
            for(int j = 0; j < cheminActu.aretes().size(); j++){
                MonTroncon arreteActu = (MonTroncon)cheminActu.aretes().get(j);
                renvoie += "De " + arreteActu.getDepart() + " à " + arreteActu.getArrivee() + " en " + cheminActu.aretes().get(j).getModalite();
                if(j+1 < cheminActu.aretes().size()){
                    renvoie += ", ";
                    MonTroncon arreteSuiv = (MonTroncon)cheminActu.aretes().get(j+1);
                    if(!arreteActu.getModalite().equals(arreteSuiv.getModalite())){
                        renvoie += "Changement à " + arreteActu.getArrivee() + " : " +  arreteActu.getModalite() + " -> " + arreteSuiv.getModalite() + ", " ;
                    }
                }
            }
            renvoie += " pour un coût total de " + cheminActu.poids() + getUnit(voyageur.getPreference()) + System.getProperty("line.separator");
            
        }
        return renvoie;
    }

    private String getUnit(TypeCout typeCout){
        if(typeCout == TypeCout.CO2){
            return "Kg";
        }
        if(typeCout == TypeCout.PRIX){
            return " Euro";
        }
        return "minutes";
    }

}
