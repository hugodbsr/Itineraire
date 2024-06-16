import static org.junit.Assert.*;
import org.junit.Test;

import Poo.MonLieu;
import Poo.MonTroncon;
import Poo.TypeCout;
import fr.ulille.but.sae_s2_2024.ModaliteTransport;

import java.util.HashMap;

public class MonTronconTest {

    @Test
    public void testToString() {
        MonLieu depart = new MonLieu("villeA");
        MonLieu arrivee = new MonLieu("villeB");
        ModaliteTransport transport = ModaliteTransport.TRAIN;
        HashMap<TypeCout, Double> cout = new HashMap<>();
        cout.put(TypeCout.PRIX, 60.0);
        cout.put(TypeCout.CO2, 1.7);
        cout.put(TypeCout.TEMPS, 80.0);
        MonTroncon troncon = new MonTroncon(depart, arrivee, transport, cout);
        assertEquals("De villeA à villeB en TRAIN", troncon.toString());
    }
}