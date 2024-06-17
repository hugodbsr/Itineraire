package Poo;

import java.io.*;
import java.util.*;

import fr.ulille.but.sae_s2_2024.Chemin;

public class Historique implements Serializable {
    private Map<Voyageur, List<String>> historiqueMap;

    public Historique() {
        historiqueMap = new HashMap<>();
    }

    public Map<Voyageur, List<String>> getHistoriqueMap() {
        return historiqueMap;
    }

    public void ajouterChemin(Voyageur voyageur, Chemin chemin) {
        historiqueMap.computeIfAbsent(voyageur, k -> new ArrayList<>()).add(chemin.toString());
    }

    public void sauvegarderHistorique(String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(historiqueMap);
        }
    }

    @SuppressWarnings("unchecked")
    public void chargerHistorique(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            historiqueMap = (Map<Voyageur, List<String>>) ois.readObject();
        }
    }

    public void chargerOuCreerHistorique(String filePath) throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (file.exists()) {
            chargerHistorique(filePath);
        } else {
            System.out.println("Aucun historique trouvé pour l'utilisateur, création d'une nouvelle entrée.");
            historiqueMap = new HashMap<>();
        }
    }

    public void afficherHistorique(String nomUtilisateur) {
        boolean utilisateurTrouve = false;
        System.out.println("Voyageur: " + nomUtilisateur);
        for (Map.Entry<Voyageur, List<String>> entry : historiqueMap.entrySet()) {
            Voyageur v = entry.getKey();
            if (v.getNom().equals(nomUtilisateur)) {
                utilisateurTrouve = true;
                List<String> cheminList = entry.getValue();
                for (String chemin : cheminList) {
                    System.out.println("Chemin: " + chemin);
                }
            }
        }
        if (!utilisateurTrouve) {
            System.out.println("Aucun historique trouvé pour l'utilisateur " + nomUtilisateur);
        }
    }
}
