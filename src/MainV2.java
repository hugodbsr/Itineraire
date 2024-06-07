import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import fr.ulille.but.sae_s2_2024.*;

public class MainV2 {
    public static void main(String[] args) throws IOException, CSVFormatException, RoadException {
        String correspondanceFilePath = "res/correspondance.csv";
        String dataFilePath = "res/data.csv";

        Voyageur voyageur = new Voyageur("Toto", TypeCout.PRIX, Arrays.asList(ModaliteTransport.TRAIN, ModaliteTransport.BUS), -1, -1, -1);

        Plateforme plateforme = new Plateforme(dataFilePath, correspondanceFilePath);

        MonLieu depart = plateforme.getLieuNom("villeA");
        MonLieu arrivee = plateforme.getLieuNom("villeD");

        Voyage voyage = new Voyage(depart, arrivee);
        List<Chemin> chemins = voyage.plusCourtChemins(plateforme, voyageur);
        System.out.println(voyage.toString(chemins, plateforme));
    }
}

