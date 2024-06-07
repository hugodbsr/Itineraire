import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import fr.ulille.but.sae_s2_2024.ModaliteTransport;

/**
 * Cette classe représente un extracteur de données CSV.
 * Elle hérite de la classe Extractor.
 */
public class CsvExtract extends Extractor {

    /**
     * Cette méthode permet de ventiler les données d'un fichier CSV.
     * Elle lit le fichier ligne par ligne et ajoute chaque ligne dans une liste.
     * @param filePath le chemin du fichier CSV à extraire
     * @return une liste de tableaux de chaînes de caractères représentant les données ventilées
     * @throws IOException si une erreur de lecture du fichier se produit
     */
    protected ArrayList<String[]> ventilation(String filePath) throws IOException {
        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitData = line.split(";");
                data.add(splitData);
            }
        }
        return data;
    }

    /**
     * Cette méthode permet d'extraire les correspondances à partir d'un fichier CSV.
     * Elle utilise la méthode ventilation pour obtenir les données ventilées du fichier.
     * Ensuite, elle vérifie le format des données ventilées et les convertit en objets Correspondance.
     * @param filePath le chemin du fichier CSV à extraire
     * @return une liste d'objets Correspondance représentant les correspondances extraites
     * @throws IOException si une erreur de lecture du fichier se produit
     * @throws CSVFormatException si le format du fichier CSV est incorrect
     */
    public ArrayList<Correspondance> extraireCorrespondances(String filePath) throws IOException, CSVFormatException {
        ArrayList<Correspondance> correspondances = new ArrayList<>();
        ArrayList<String[]> data = ventilation(filePath);
        if (!verificationVentilation(data)) {
            throw new CSVFormatException("Format de fichier CSV incorrect");
        }
        for (String[] values : data) {
            String ville = values[0];
            ModaliteTransport from = ModaliteTransport.valueOf(values[1].toUpperCase());
            ModaliteTransport to = ModaliteTransport.valueOf(values[2].toUpperCase());
            int minutes = Integer.parseInt(values[3]);
            double co2 = Double.parseDouble(values[4]);
            double euro = Double.parseDouble(values[5]);
            correspondances.add(new Correspondance(ville, from, to, minutes, co2, euro));
        }
        return correspondances;
    }

    /**
     * Cette méthode vérifie si une ligne de données est valide.
     * Elle vérifie si le transport, les valeurs de CO2, d'euro et de minutes sont valides.
     * @param row le tableau de chaînes de caractères représentant une ligne de données
     * @return true si la ligne de données est valide, false sinon
     */
    @Override
    protected boolean isValid(String[] row) {
        return isTransport(row[2]) && isDouble(row[3]) && isDouble(row[4]) && isDouble(row[5]);
    }
}
