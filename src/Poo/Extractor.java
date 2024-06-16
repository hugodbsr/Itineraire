package Poo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import fr.ulille.but.sae_s2_2024.Lieu;
import fr.ulille.but.sae_s2_2024.ModaliteTransport;

public abstract class Extractor {

    /**
     * Extrait des tronçons de transport à partir des données fournies et crée
     * des tronçons correspondants. Chaque tronçon est représenté par deux objets
     * MonTroncon : un pour la direction aller et un pour la direction retour.
     *
     * @param ventile la liste des tableaux de données divisées
     * @param lieux l'ensemble des lieux existants
     * @return une ArrayList des tronçons extraits MonTroncon
     */
    public ArrayList<MonTroncon> extraireTroncon(ArrayList<String[]> ventile, Set<Lieu> lieux, ArrayList<Correspondance> correspondances) {
        ArrayList<MonTroncon> renvoie = new ArrayList<>();
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

    /**
     * Vérifie que chaque ligne dans les données fournies contient exactement six éléments
     * et que certains éléments sont du bon type.
     *
     * @param data une ArrayList de tableaux de chaînes de caractères à vérifier
     * @return true si toutes les lignes ont le bon format et les bons types ;
     *         false sinon
     */
    protected boolean verificationVentilation(ArrayList<String[]> data) {
        for (String[] row : data) {
            if (row.length != 6 || !isValid(row)) {
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
    protected boolean isTransport(String transportType) {
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
    protected boolean isDouble(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Crée un nouveau lieu avec le nom donné s'il n'existe pas déjà dans l'ensemble de lieux fourni.
     * Si le lieu existe, il retourne le lieu existant.
     *
     * @param nom le nom du lieu à créer
     * @param lieux l'ensemble des lieux existants
     * @return le lieu existant ou nouvellement créé
     */
    protected Lieu createLieu(String nom, Set<Lieu> lieux) {
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
     * Cette méthode vérifie si une ligne de données est valide.
     * Elle vérifie si le transport, les valeurs de CO2, d'euro et de minutes sont valides.
     * @param row le tableau de chaînes de caractères représentant une ligne de données
     * @return true si la ligne de données est valide, false sinon
     */
    protected boolean isValid(String[] row) {
        return isTransport(row[2]) && isDouble(row[3]) && isDouble(row[4]) && isDouble(row[5]);
    }

}