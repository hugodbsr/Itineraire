import java.util.ArrayList;
import java.util.List;
import fr.ulille.but.sae_s2_2024.*;

/**
 * @author Hugo Debuyser, Gaël Dierynck, Maxence Antoine
 * Cette classe permet de décrire un voyage, avec une liste de tronçons, un lieu de départ et un lieu d'arrivée.
 * Getter et Setter fournis.
 */

public class Voyage {
    /**
     * La liste des tronçons du voyage.
     */
    private List<MonTroncon> aretes;

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
     * @param aretes La liste des tronçons du voyage.
     * @param arrivee Le lieu d'arrivée du voyage.
     * @param depart Le lieu de départ du voyage.
     */
    public Voyage(List<MonTroncon> aretes, MonLieu arrivee, MonLieu depart) {
        this.aretes = aretes;
        this.arrivee = arrivee;
        this.depart = depart;
    }

    /**
     * Retourne la liste des tronçons du voyage.
     * @return La liste des tronçons.
     */
    public List<MonTroncon> getAretes() {
        return aretes;
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
     * Définit la liste des tronçons du voyage.
     * @param aretes La nouvelle liste des tronçons.
     */
    public void setAretes(List<MonTroncon> aretes) {
        this.aretes = aretes;
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
    public List<Chemin> plusCourtChemins(Plateforme plateforme, Voyageur voyageur) {
        List<Chemin> listPrix = AlgorithmeKPCC.kpcc(plateforme.getGraphe(TypeCout.PRIX), this.getDepart(), this.getArrivee(), 3);
        List<Chemin> listCo2 = AlgorithmeKPCC.kpcc(plateforme.getGraphe(TypeCout.CO2), this.getDepart(), this.getArrivee(), 3);
        List<Chemin> listTemps = AlgorithmeKPCC.kpcc(plateforme.getGraphe(TypeCout.TEMPS), this.getDepart(), this.getArrivee(), 3);

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

}