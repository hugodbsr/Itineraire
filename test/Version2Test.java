import org.junit.jupiter.api.Test;

import Exception.CSVFormatException;
import Exception.RoadException;
import Poo.Correspondance;
import Poo.MonLieu;
import Poo.Plateforme;
import Poo.TypeCout;
import Poo.Voyage;
import Poo.Voyageur;
import fr.ulille.but.sae_s2_2024.ModaliteTransport;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Version2Test {
    
    String correspondanceFilePath = "res/correspondanceTest.csv";
    String dataFilePath = "res/dataTest.csv";

    @Test
    public void testConstructionPlateforme() throws IOException, CSVFormatException {

        Plateforme plateforme = new Plateforme(dataFilePath, correspondanceFilePath);

        assertNotNull(plateforme.getLieux());
        assertNotNull(plateforme.getTroncons());
        assertNotNull(plateforme.getCorrespondances());
        assertEquals(5, plateforme.getLieux().size());
        assertEquals(18, plateforme.getTroncons().size());
        assertEquals(1, plateforme.getCorrespondances().size());
    }

    @Test
    public void testGetCorrespondance() throws IOException, CSVFormatException {
        Plateforme plateforme = new Plateforme(dataFilePath, correspondanceFilePath);

        MonLieu lieu = new MonLieu("Paris_Nord");
        Correspondance correspondance = plateforme.getCorrespondance(lieu, ModaliteTransport.BUS);
        
        assertNotNull(correspondance);
        assertEquals("Paris_Nord", correspondance.getVille());
        assertEquals(ModaliteTransport.BUS, correspondance.getDepart());
        assertEquals(ModaliteTransport.BUS, correspondance.getArrivee());
        assertEquals(15, correspondance.getPrix());
        assertEquals(0.2, correspondance.getCo2());
        assertEquals(5, correspondance.getTemps());
    }

    @Test
    public void testGetGrapheAvecCritereEtTransports() throws IOException, CSVFormatException, RoadException {
        Plateforme plateforme = new Plateforme(dataFilePath, correspondanceFilePath);

        MonLieu depart = plateforme.getLieuNom("Paris_Nord");
        List<ModaliteTransport> transports = new ArrayList<>();
        transports.add(ModaliteTransport.TRAIN);
        transports.add(ModaliteTransport.BUS);

        MultiGrapheOrienteValue graphe = plateforme.getGrapheAvecCritereEtTransports(TypeCout.PRIX, transports);

        assertNotNull(graphe);
        assertEquals(6, graphe.sommets().size());
        assertEquals(6, graphe.aretes().size());
    }

    @Test
    public void testPlusCourtChemins() throws IOException, CSVFormatException, RoadException {

        Plateforme plateforme = new Plateforme(dataFilePath, correspondanceFilePath);

        MonLieu depart = plateforme.getLieuNom("Paris_Nord");
        MonLieu arrivee = plateforme.getLieuNom("IUT");
        Voyageur voyageur = new Voyageur("Toto", TypeCout.PRIX, new ArrayList<>(), -1, -1, -1);

        Voyage voyage = new Voyage(depart, arrivee);
        List<Chemin> chemins = voyage.plusCourtChemins(plateforme, voyageur);

        assertNotNull(chemins);
        assertEquals(1, chemins.size());
    }
}
