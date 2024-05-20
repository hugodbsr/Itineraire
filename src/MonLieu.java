import fr.ulille.but.sae_s2_2024.Lieu;

/**
 * La classe MonLieu permet de décrire un lieu de transport.
 * Getter et Setter fournis.
 * 
 * @autor Hugo Debuyser, Gaël Dierynck, Maxence Antoine
 */
public class MonLieu implements Lieu {
    /**
     * Le nom du lieu de transport.
     */
    private String nom;

    /**
     * Constructeur de la classe MonLieu.
     * @param nom Le nom du lieu de transport.
     */
    public MonLieu(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le nom du lieu de transport.
     * @return Le nom du lieu de transport.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du lieu de transport.
     * @param nom Le nouveau nom du lieu de transport.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Permet de comparer deux lieux traités comme objets.
     * @param obj l'objet à comparer
     * @return true si les objets sont égaux, false sinon
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof MonLieu)) return false;
        MonLieu other = (MonLieu) obj;
        if (nom == null) {
            if (other.nom != null) return false;
        } else if (!nom.equals(other.nom)) return false;
        return true;
    }

    /**
     * Retourne une représentation sous forme de chaîne du lieu de transport.
     * @return Le nom du lieu de transport.
     */
    public String toString() {
        return this.nom;
    }
}
