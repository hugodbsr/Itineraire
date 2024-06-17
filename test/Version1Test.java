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

public class Version1Test {

    String[] data = new String[]{
        "Paris Nord;Lille Flandres;Train;25;0.8;80",
        "Paris Nord;Arras;Train;15;0.5;60",
        "Paris Nord;Douai;Train;20;0.6;70",
        "Lille Flandres;IUT;Train;5;0.2;30",
        "Arras;IUT;Train;10;0.3;40",
        "Douai;IUT;Train;8;0.25;35"
    };
    Voyageur toto = new Voyageur("Toto", TypeCout.PRIX, ModaliteTransport.TRAIN, -1, -1, -1);

    Plateforme plateforme = new Plateforme(data, toto);

    @Test
    public void testGetLieux() {
        Set<Lieu> lieux = plateforme.getLieux();
        assertNotNull(lieux);
        assertEquals(5, lieux.size());
    }

    @Test
    public void testGetLieuNomExisting() throws RoadException {
        MonLieu lieu = plateforme.getLieuNom("Paris Nord");
        assertNotNull(lieu);
        assertEquals("Paris Nord", lieu.getNom());
    }

    @Test
    public void testGetLieuNomNonExisting() throws RoadException {
        MonLieu lieu = plateforme.getLieuNom("Marseille");
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
        MultiGrapheOrienteValue graphe = plateforme.getGraphe(TypeCout.PRIX);
        assertNotNull(graphe);
        assertEquals(5, graphe.sommets().size());
        assertEquals(12, graphe.aretes().size());
    }

    @Test
    public void testPlusCourtChemins() throws RoadException {
        Voyage voyage = new Voyage(null, plateforme.getLieuNom("IUT"), plateforme.getLieuNom("Paris Nord"));
        List<Chemin> chemins = voyage.plusCourtChemins(plateforme, toto);
        assertEquals(3, chemins.size());

    }
}
