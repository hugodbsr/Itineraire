import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import fr.ulille.but.sae_s2_2024.*;

public class MainV2 {

    public static void main(String[] args) throws IOException {
        String correspondanceFilePath = "res/correspondance.csv";
        String dataFilePath = "res/data.csv";

        File dataFile = new File(dataFilePath);
        Scanner scanner = new Scanner(dataFile);
        ArrayList<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        String[] dataLines = lines.toArray(new String[0]);
        scanner.close();

        Voyageur voyageur = new Voyageur("Toto", TypeCout.CO2, ModaliteTransport.TRAIN, -1, -1, 150);

        Plateforme plateforme = new Plateforme(dataLines, voyageur, correspondanceFilePath);

        MonLieu depart = plateforme.getLieuNom("villeC");
        MonLieu arrivee = plateforme.getLieuNom("villeD");

        Voyage voyage = new Voyage(null, arrivee, depart);
        System.out.println(voyage.plusCourtChemins(plateforme, voyageur));
    }
}
