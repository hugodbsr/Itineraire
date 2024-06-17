package Poo;
import java.io.*;
import java.util.*;
import fr.ulille.but.sae_s2_2024.*;

public class Historique implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Historique> historiqueList;

    private Voyageur voyageur;
    private Voyage voyage;
    private List<Chemin> chemins;

    public Historique(Voyageur voyageur, Voyage voyage, List<Chemin> chemins) {
        this.voyageur = voyageur;
        this.voyage = voyage;
        this.chemins = chemins;
        this.historiqueList = new ArrayList<>();
    }

    public Voyageur getVoyageur() {
        return voyageur;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public List<Chemin> getChemins() {
        return chemins;
    }

    public void ajouterHistorique(Historique historique) {
        historiqueList.add(historique);
    }

    public void sauvegarderHistorique(String chemin) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(chemin))) {
            oos.writeObject(historiqueList);
        }
    }

    public List<Historique> chargerHistorique(String chemin) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(chemin))) {
            return (List<Historique>) ois.readObject();
        }
    }
}
