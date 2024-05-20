import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class DataExtractTest {

    @Test
    public void testVentilation() {
        DataExtract extractor = new DataExtract();
        String[] data = {"villeA;villeB;Train;60;1.7;80"};
        ArrayList<String[]> ventile = extractor.ventilation(data);
        assertEquals(1, ventile.size());
    }

    @Test
    public void testVerificationVentilationCorrect() {
        DataExtract extractor = new DataExtract();
        String[] data = {"villeA;villeB;Train;60;1.7;80"};
        ArrayList<String[]> ventile = extractor.ventilation(data);
        assertTrue(extractor.verificationVentilation(ventile));
    }

    @Test
    public void testVerificationVentilationIncorrect() {
        DataExtract extractor = new DataExtract();
        String[] data = {"villeA;villeB;Train;60;1.7"};
        ArrayList<String[]> ventile = extractor.ventilation(data);
        assertFalse(extractor.verificationVentilation(ventile));
    }
}
