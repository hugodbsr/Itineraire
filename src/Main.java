import fr.ulille.but.sae_s2_2024.*;
/**
 * @author Hugo Debuyser, Gaël Dierynck, Maxence Antoine 
 * La classe {@code Main} permet de tester le projet
 */
public class Main {
/**
 * On ajoute des données de test, on crée un voyageur, une plateforme et on teste le plus court chemin
 * @param args
 */
    public static void main(String[] args) {
        String[] data = new String[]{
            "villeA;villeB;Train;60;1.7;80",
            "villeB;villeD;Train;22;2.4;40",
            "villeA;villeC;Train;42;1.4;50",
            "villeB;villeC;Train;14;1.4;60",
            "villeC;villeD;Avion;110;150;22",
            "villeC;villeD;Train;65;1.2;90"
        };
        Voyageur voyageur = new Voyageur("Toto", TypeCout.CO2, ModaliteTransport.TRAIN, -1, -1, 150);

        Plateforme plateforme = new Plateforme(data, voyageur);

        MonLieu depart = plateforme.getLieuNom("villeC");
        MonLieu arrivee = plateforme.getLieuNom("villeD");

        Voyage voyage = new Voyage(null, arrivee, depart);
        System.out.println(voyage.plusCourtChemins(plateforme, voyageur));
    }
}
