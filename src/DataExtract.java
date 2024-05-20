import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import fr.ulille.but.sae_s2_2024.*;

/**
 * @author Hugo Debuyser, Gaël Dierynck, Maxence Antoine
 * La classe DataExtract fournit des méthodes pour extraire et vérifier
 * les données des tronçons de transport, créer des lieux et extraire des tronçons
 * à partir des données fournies.
 */
public class DataExtract {
    /**
     * Divise les données d'entrée en une liste de tableaux de chaînes de caractères,
     * où chaque tableau représente une ligne de données divisée par des points-virgules.
     *
     * @param data le tableau de chaînes de caractères à diviser
     * @return une ArrayList de tableaux de chaînes de caractères, où chaque
     *         tableau représente une ligne de données divisée
     */
    public ArrayList<String[]> ventilation(String[] data) {
        ArrayList<String[]> retour = new ArrayList<>();
        for (String datum : data) {
            retour.add(datum.split(";"));
        }
        return retour;
    }

    /**
     * Vérifie que chaque ligne dans les données fournies contient exactement six éléments
     * et que certains éléments sont du bon type.
     *
     * @param data une ArrayList de tableaux de chaînes de caractères à vérifier
     * @return true si toutes les lignes ont le bon format et les bons types ;
     *         false sinon
     */
    public boolean verificationVentilation(ArrayList<String[]> data) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).length != 6) {
                System.out.println("Format incorrect, il manque des valeurs pour le tronçon " + i);
                return false;
            }
        }
        for (String[] tab : data) {
            if (!isTransport(tab[2]) || !isDouble(tab[3]) || !isDouble(tab[4]) || !isDouble(tab[5])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si la chaîne de caractères fournie correspond à une valeur de l'énumération
     * ModaliteTransport.
     *
     * @param transportType le type de transport à vérifier
     * @return true si le type de transport est valide, false sinon
     */
    private boolean isTransport(String transportType) {
        for (ModaliteTransport modalite : ModaliteTransport.values()) {
            if (modalite.name().equalsIgnoreCase(transportType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vérifie si la chaîne de caractères fournie peut être convertie en double.
     *
     * @param number la chaîne de caractères à vérifier
     * @return true si la chaîne peut être convertie en double, false sinon
     */
    private boolean isDouble(String number) {
        try {
            Double.parseDouble(number);
        } catch (NumberFormatException e) {
            System.out.println(number + " mauvais type");
            return false;
        }
        return true;
    }

    /**
     * Crée un nouveau lieu avec le nom donné s'il n'existe pas déjà dans l'ensemble de lieux fourni.
     * Si le lieu existe, il retourne le lieu existant.
     *
     * @param nom le nom du lieu à créer
     * @param lieux l'ensemble des lieux existants
     * @return le lieu existant ou nouvellement créé
     */
    public Lieu createLieu(String nom, Set<Lieu> lieux) {
        for (Lieu lieu : lieux) {
            if (((MonLieu)lieu).getNom().equals(nom)) {
                return lieu;
            }
        }
        Lieu nouveauLieu = new MonLieu(nom);
        lieux.add(nouveauLieu);
        return nouveauLieu;
    }
    
    /**
     * Extrait des tronçons de transport à partir des données fournies et crée
     * des tronçons correspondants. Chaque tronçon est représenté par deux objets
     * MonTroncon : un pour la direction aller et un pour la direction retour.
     *
     * @param ventile la liste des tableaux de données divisées
     * @param lieux l'ensemble des lieux existants
     * @return une ArrayList des tronçons extraits MonTroncon
     */
    public ArrayList<MonTroncon> extraireTroncon(ArrayList<String[]> ventile, Set<Lieu> lieux) {
        ArrayList<MonTroncon> renvoie = new ArrayList<MonTroncon>();
        for (String[] row : ventile) {
            if (row.length >= 6) {
                MonLieu depart = (MonLieu) createLieu(row[0], lieux);
                MonLieu arrivee = (MonLieu) createLieu(row[1], lieux);
                ModaliteTransport transport = ModaliteTransport.valueOf(row[2].toUpperCase());
                HashMap<TypeCout, Double> cout = new HashMap<>();
                cout.put(TypeCout.PRIX, Double.parseDouble(row[3]));
                cout.put(TypeCout.CO2, Double.parseDouble(row[4]));
                cout.put(TypeCout.TEMPS, Double.parseDouble(row[5]));
                MonTroncon troncon1 = new MonTroncon(depart, arrivee, transport, cout);
                MonTroncon troncon2 = new MonTroncon(arrivee, depart, transport, cout);
                renvoie.add(troncon1);
                renvoie.add(troncon2);
            } else {
                System.out.println("Tronçon incorrect : " + row);
            }
        }
        return renvoie;
    }
}
