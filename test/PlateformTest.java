import static org.junit.Assert.*;

import java.util.Set;
import java.util.List;
import org.junit.Test;

import Exception.RoadException;
import Poo.MonLieu;
import Poo.Plateforme;
import Poo.TypeCout;
import Poo.Voyage;
import Poo.Voyageur;
import fr.ulille.but.sae_s2_2024.*;

public class PlateformTest {

    String[] data = new String[]{
        "villeA;villeB;Train;60;1.7;80",
        "villeB;villeD;Train;22;2.4;40",
        "villeA;villeC;Train;42;1.4;50",
        "villeB;villeC;Train;14;1.4;60",
        "villeC;villeD;Avion;110;150;22",
        "villeC;villeD;Train;65;1.2;90"
    };
    Voyageur voyageur = new Voyageur("Toto", TypeCout.CO2, ModaliteTransport.TRAIN, -1, -1, -1);

    Plateforme plateforme = new Plateforme(data, voyageur);

    @Test
    public void testGetLieux() {
        Set<Lieu> lieux = plateforme.getLieux();
        assertNotNull(lieux);
        assertEquals(4, lieux.size());
    }

    @Test
    public void testGetLieuNomExisting() throws RoadException {
        MonLieu lieu = plateforme.getLieuNom("villeA");
        assertNotNull(lieu);
        assertEquals("villeA", lieu.getNom());
    }

    @Test
    public void testGetLieuNomNonExisting() throws RoadException {
        MonLieu lieu = plateforme.getLieuNom("villeX");
        assertNull(lieu);
    }

    @Test
    public void testGetTroncons() {
        Set<Trancon> troncons = plateforme.getTroncons();
        assertNotNull(troncons);
        assertEquals(12, troncons.size());
    }

    @Test
    public void testGetGraphe() throws RoadException {
        MultiGrapheOrienteValue graphe = plateforme.getGraphe(TypeCout.CO2);
        assertNotNull(graphe);
        assertEquals(4, graphe.sommets().size());
        assertEquals(10, graphe.aretes().size());
    }

    @Test
    public void testGetGrapheWithEmptyData() throws RoadException {
        Voyageur voyageur = new Voyageur("Titi", TypeCout.PRIX, ModaliteTransport.TRAIN, -1, -1, -1);
        Plateforme plateformeWithEmptyData = new Plateforme(new String[]{}, voyageur);
        MultiGrapheOrienteValue graphe = plateformeWithEmptyData.getGraphe(TypeCout.PRIX);
        assertNotNull(graphe);
        assertEquals(0, graphe.sommets().size());
        assertEquals(0, graphe.aretes().size());
    }

    @Test
    public void testShortestPathWithEnvironmentImpact() throws RoadException {
        Voyageur voyageur = new Voyageur("Toto", TypeCout.CO2, ModaliteTransport.TRAIN, -1, -1, 180);
        Plateforme plateforme = new Plateforme(data, voyageur);
        MonLieu depart = plateforme.getLieuNom("villeC");
        MonLieu arrivee = plateforme.getLieuNom("villeD");
        Voyage voyage = new Voyage(null, arrivee, depart);
        List<Chemin> chemins = voyage.plusCourtChemins(plateforme, voyageur);
        assertFalse("No path found for the given criteria.", chemins.isEmpty());
        for (Chemin chemin : chemins) {
            assertTrue("Path duration exceeds the allowed time limit.", chemin.poids() <= voyageur.getTemps());
        }
    }
}