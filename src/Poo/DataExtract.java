package Poo;

import java.util.ArrayList;

public class DataExtract extends Extractor {

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
     * Vérifie si une ligne de données est valide.
     *
     * @param row le tableau de chaînes de caractères représentant une ligne de données
     * @return true si la ligne de données est valide, sinon false
     */
    @Override
    protected boolean isValid(String[] row) {
        return isTransport(row[2]) && isDouble(row[3]) && isDouble(row[4]) && isDouble(row[5]);
    }
}