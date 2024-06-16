package Exception;
/**
 * La classe CSVFormatException est levée lorsqu'il y a une erreur dans le format d'un fichier CSV
 * Sous-classe de la classe Exception.
 */
public class CSVFormatException extends Exception {
    /**
     * Construit une nouvelle instance de CSVFormatException avec le message d'erreur spécifié.
     *
     * @param message le message d'erreur
     */
    public CSVFormatException(String message) {
        super(message);
    }
}