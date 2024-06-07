import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.ulille.but.sae_s2_2024.*;

/**
 * Classe principale de l'interface utilisateur en ligne de commande pour la recherche de chemins de transport.
 * La classe TUIMain permet à un utilisateur de saisir des préférences de voyage et de trouver les chemins les plus courts 
 * entre des lieux en fonction de ces préférences.
 */
public class TUIMain {
    
    /**
     * Méthode principale pour exécuter le programme.
     * Elle initialise les données, demande les préférences de l'utilisateur, et permet de rechercher des chemins
     * en fonction de ces préférences avec un contrôle de saisie 
     * @param args
     * @throws RoadException
     */
    public static void main(String[] args) throws RoadException {
        Scanner scanner = new Scanner(System.in);

        // Données initiales de transport entre différentes villes
        String[] data = new String[]{
            "villeA;villeB;Train;60;1.7;80",
            "villeB;villeD;Train;22;2.4;40",
            "villeA;villeC;Train;42;1.4;50",
            "villeB;villeC;Train;14;1.4;60",
            "villeC;villeD;Avion;110;150;22",
            "villeC;villeD;Train;65;1.2;90"
        };

        System.out.println("Entrez votre nom:");
        String nom = scanner.nextLine();

        System.out.println("Entrez votre type de coût préféré (CO2, TEMPS, PRIX):");
        String typeCoutInput = scanner.nextLine();
        TypeCout preference = TypeCout.valueOf(typeCoutInput.toUpperCase());

        System.out.println("Entrez votre type de transport préféré (TRAIN, AVION, etc.):");
        String typeTransportInput = scanner.nextLine();
        ModaliteTransport transport = ModaliteTransport.valueOf(typeTransportInput.toUpperCase());

        Voyageur voyageur = new Voyageur(nom, preference, transport, -1, -1, -1);
        Plateforme plateforme = new Plateforme(data, voyageur);

        // Liste des noms de lieux disponibles
        List<String> nomsLieux = new ArrayList<>();
        for (Lieu lieu : plateforme.getLieux()) {
            nomsLieux.add(((MonLieu) lieu).getNom());
        }

        boolean continuer = true;

        // Boucle principale pour la recherche de chemins
        while (continuer) {
            System.out.print("Lieux disponibles: ");
            for (int i = 0; i < nomsLieux.size(); i++) {
                System.out.print(nomsLieux.get(i));
                if (i < nomsLieux.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();

            MonLieu depart = null;
            MonLieu arrivee = null;

            // Saisie et validation du lieu de départ
            while (depart == null) {
                System.out.println("Entrez votre lieu de départ:");
                String departNom = scanner.nextLine();
                for (Lieu lieu : plateforme.getLieux()) {
                    if (((MonLieu) lieu).getNom().equalsIgnoreCase(departNom)) {
                        depart = (MonLieu) lieu;
                    }
                }
                if (depart == null) {
                    System.out.println("Lieu de départ invalide. Veuillez réessayer.");
                }
            }

            // Saisie et validation du lieu d'arrivée
            while (arrivee == null) {
                System.out.println("Entrez votre lieu d'arrivée:");
                String arriveeNom = scanner.nextLine();
                for (Lieu lieu : plateforme.getLieux()) {
                    if (((MonLieu) lieu).getNom().equalsIgnoreCase(arriveeNom)) {
                        arrivee = (MonLieu) lieu;
                    }
                }
                if (arrivee == null) {
                    System.out.println("Lieu d'arrivée invalide. Veuillez réessayer.");
                }
            }

            Voyage voyage = new Voyage(null, arrivee, depart);
            List<Chemin> chemins = voyage.plusCourtChemins(plateforme, voyageur);

            // Affichage des chemins trouvés ou non
            if (chemins.isEmpty()) {
                System.out.println("Aucun chemin trouvé pour les critères donnés.");
            } else {
                System.out.println("Chemins les plus courts trouvés:");
                for (Chemin chemin : chemins) {
                    System.out.println(chemin);
                }
            }

            System.out.println("Voulez-vous chercher un autre chemin ? (oui/non):");
            String continuerInput = scanner.nextLine();
            continuer = continuerInput.equalsIgnoreCase("oui");
        }

        scanner.close();
    }
}
