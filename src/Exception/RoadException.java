package Exception;
/**
 * La classe RoadException est levée lorsqu'une erreur est détectée dans la classe Road
 * Sous-classe de la classe Exception.
 */
public class RoadException extends Exception {
    /**
     * Construit une nouvelle RoadException avec le message détaillé spécifié.
     *
     * @param message le message détaillé (qui est enregistré pour une récupération ultérieure par la méthode getMessage())
     */
    public RoadException(String message) {
        super(message);
    }
}