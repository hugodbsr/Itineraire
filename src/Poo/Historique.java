package Poo;

import java.io.*;
import java.util.*;

import fr.ulille.but.sae_s2_2024.Chemin;

/**
 * La classe Historique représente l'historique des chemins parcourus par les voyageurs.
 * Elle permet d'ajouter des chemins à l'historique, de sauvegarder et charger l'historique depuis un fichier,
 * de charger ou créer l'historique, et d'afficher l'historique d'un utilisateur spécifique.
 */
public class Historique implements Serializable {
    private Map<Voyageur, List<String>> historiqueMap;

    /**
     * Constructeur de la classe Historique.
     * Initialise la map de l'historique.
     */
    public Historique() {
        historiqueMap = new HashMap<>();
    }

    /**
     * Retourne la map de l'historique.
     * @return La map de l'historique.
     */
    public Map<Voyageur, List<String>> getHistoriqueMap() {
        return historiqueMap;
    }

    /**
     * Ajoute un chemin à l'historique d'un voyageur.
     * Si le voyageur n'existe pas dans l'historique, une nouvelle entrée est créée.
     * @param voyageur Le voyageur.
     * @param chemin Le chemin parcouru.
     */
    public void ajouterChemin(Voyageur voyageur, Chemin chemin) {
        historiqueMap.computeIfAbsent(voyageur, k -> new ArrayList<>()).add(chemin.toString());
    }

    /**
     * Sauvegarde l'historique dans un fichier.
     * @param filePath Le chemin du fichier de sauvegarde.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la sauvegarde.
     */
    public void sauvegarderHistorique(String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(historiqueMap);
        }
    }

    /**
     * Charge l'historique depuis un fichier.
     * @param filePath Le chemin du fichier de chargement.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors du chargement.
     * @throws ClassNotFoundException Si la classe de l'objet chargé n'est pas trouvée.
     */
    @SuppressWarnings("unchecked")
    public void chargerHistorique(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            historiqueMap = (Map<Voyageur, List<String>>) ois.readObject();
        }
    }

    /**
     * Charge l'historique depuis un fichier s'il existe, sinon crée un nouvel historique.
     * @param filePath Le chemin du fichier de chargement/création.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors du chargement/création.
     * @throws ClassNotFoundException Si la classe de l'objet chargé n'est pas trouvée.
     */
    public void chargerOuCreerHistorique(String filePath) throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (file.exists()) {
            chargerHistorique(filePath);
        } else {
            System.out.println("Aucun historique trouvé pour l'utilisateur, création d'une nouvelle entrée.");
            historiqueMap = new HashMap<>();
        }
    }

    /**
     * Affiche l'historique des chemins parcourus par un utilisateur spécifique, sinon affiche un message d'erreur.
     * @param nomUtilisateur Le nom de l'utilisateur.
     */
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
