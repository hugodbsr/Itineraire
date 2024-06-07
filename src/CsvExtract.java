import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import fr.ulille.but.sae_s2_2024.ModaliteTransport;

public class CsvExtract extends Extractor {

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

    @Override
    protected boolean isValid(String[] row) {
        return isTransport(row[2]) && isDouble(row[3]) && isDouble(row[4]) && isDouble(row[5]);
    }
}
