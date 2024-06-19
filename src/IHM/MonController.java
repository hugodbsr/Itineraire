package IHM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import fr.ulille.but.sae_s2_2024.*;
import Poo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Exception.*;

public class MonController {
    @FXML
    TextField tempsField;

    @FXML
    Button rechercherButton;

    @FXML
    Button histoButton;

    @FXML
    CheckBox checkTrain;

    @FXML
    CheckBox CheckBus;

    @FXML
    CheckBox CheckAvion;

    @FXML
    AnchorPane renduApane;

    @FXML
    AnchorPane rechercherApane;

    @FXML
    AnchorPane choixApane;

    @FXML
    StackPane stackPane;

    @FXML
    AnchorPane anchorPane;

    @FXML
    Button finaliserButton;

    @FXML
    Button menuButton;

    @FXML
    Button saveExitButton;

    @SuppressWarnings("rawtypes")
    @FXML
    ChoiceBox DepartChoice;

    @SuppressWarnings("rawtypes")
    @FXML
    ChoiceBox ArriveeChoice;

    @FXML
    TextField CO2Field;

    @FXML
    TextField EuroFiled;

    @SuppressWarnings("rawtypes")
    @FXML
    ListView ListDepart;

    @SuppressWarnings("rawtypes")
    @FXML
    ListView arriveeList;

    @SuppressWarnings("rawtypes")
    @FXML
    ListView ModaliteList;

    @SuppressWarnings("rawtypes")
    @FXML
    ListView TempsList;

    @SuppressWarnings("rawtypes")
    @FXML
    ListView PrixList;

    @SuppressWarnings("rawtypes")
    @FXML
    ListView CO2List;

    @SuppressWarnings("rawtypes")
    @FXML
    ListView SelectionList;

    @FXML
    Label DepartLabel;

    @FXML
    Label ArriveeLabel;

    @FXML
    Label MadaliteLabel;

    @FXML
    Label TempsLabel;

    @FXML
    Label PrixLabel;

    @FXML
    Label Co2Label;

    @FXML
    Label CheminSelection;

    @FXML
    Button QuitterCheminButton;

    @FXML
    Button ConfirmerCheminButton;

    Plateforme plateforme;
    Voyageur voyageur;
    Voyage voyage;

    List<ModaliteTransport> listTransport = new ArrayList<ModaliteTransport>();

    String correspondanceFilePath = "res/correspondance.csv";
    String dataFilePath = "res/data.csv";
    String historiqueFilePath = "res/historique.bin";

    @SuppressWarnings("unchecked")
    public void initialize() throws IOException, CSVFormatException {
        plateforme = new Plateforme(dataFilePath, correspondanceFilePath);
        ObservableList<String> elementsList = FXCollections.observableArrayList(ListLieuxToString(plateforme.getLieux()));
        DepartChoice.setItems(elementsList);
        ArriveeChoice.setItems(elementsList);
        DepartChoice.setValue("départ");
        ArriveeChoice.setValue("arrivée");
        rechercherButton.setOnAction(event -> {
            try {
                pressedButtonRechercher();
            } catch (RoadException e) {
                e.printStackTrace();
            }
        });
        initEcouteursCheckBox();
        QuitterCheminButton.setOnAction(event -> {
            rechercherApane.setVisible(true);
            renduApane.setVisible(false);
        });
        ConfirmerCheminButton.setOnAction(event -> {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmation du chemin");
        alert.setHeaderText("Votre itiniéraire à été confirmé !");
        ButtonType buttonTypeConfirmer = new ButtonType("Ok");
        alert.getButtonTypes().setAll(buttonTypeConfirmer);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeConfirmer) {
            }
        });
    });
    }

    private void initEcouteursCheckBox() {
        checkTrain.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                ajouterCheckBox(ModaliteTransport.TRAIN);
            } else {
                ajouterCheckBox(ModaliteTransport.AVION);
            }
        });
        CheckBus.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                ajouterCheckBox(ModaliteTransport.BUS);
            } else {
                ajouterCheckBox(ModaliteTransport.AVION);
            }
        });
        CheckAvion.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                ajouterCheckBox(ModaliteTransport.AVION);
            } else {
                ajouterCheckBox(ModaliteTransport.AVION);
            }
        });
    }

    private void ajouterCheckBox(ModaliteTransport transport) {
        if(listTransport.contains(transport)){
            listTransport.remove(transport);
        }
        if(!listTransport.contains(transport)){
            listTransport.add(transport);
        }
    }

    @SuppressWarnings("unchecked")
    @FXML
    public void pressedButtonRechercher() throws RoadException {
        Object departObj = DepartChoice.getValue();
        Object arriveeObj = ArriveeChoice.getValue();
        if (departObj != null && arriveeObj != null) {
            String depart = departObj.toString();
            String arrivee = arriveeObj.toString();
            if (!"départ".equals(depart) && !"arrivée".equals(arrivee)) {
                choixApane.setVisible(false);
                rechercherApane.setVisible(true);
                voyageur = new Voyageur("Toto", TypeCout.PRIX, listTransport, -1, -1, -1);
                voyage = new Voyage(plateforme.getLieuNom(depart), plateforme.getLieuNom(arrivee));
                List<Chemin> chemins = voyage.plusCourtChemins(plateforme, voyageur);
                ObservableList<String> departList = FXCollections.observableArrayList(CheminsToString(chemins, depart));
                ListDepart.setItems(departList);
                ObservableList<String> listArrivee = FXCollections.observableArrayList(CheminsToString(chemins, arrivee));
                arriveeList.setItems(listArrivee);
                ObservableList<String> listModal = FXCollections.observableArrayList(modaliteToString(chemins));
                ModaliteList.setItems(listModal);
                afficherCout(chemins);
                selectionButtons(chemins);
            }
        }
    }

    @SuppressWarnings("unchecked")
private void selectionButtons(List<Chemin> chemins) {
    ObservableList<AnchorPane> buttonList = FXCollections.observableArrayList();
    for(int i = 0; i < chemins.size(); i++){
        Button button = new Button("Selection");
        AnchorPane anchorPane = new AnchorPane(button);
        button.setPrefWidth(70);
        button.setPrefHeight(17);
        button.setFont(Font.font("Arial", 10));
        int finalI = i;
        button.setOnAction(event -> {
            rechercherApane.setVisible(false);
            renduApane.setVisible(true);
            CheminSelection.setText(chemins.get(finalI).toString());
        });
        buttonList.add(anchorPane);
    }
    SelectionList.setItems(buttonList);
}


    @SuppressWarnings("unchecked")
    private void afficherCout(List<Chemin> chemins){
        List<String> renvoie = new ArrayList<String>();
        for(Chemin chemin : chemins){
            renvoie.add(""+chemin.poids());
        }
        ObservableList<String> ListCout = FXCollections.observableArrayList(renvoie);
        if(voyageur.getPreference() == TypeCout.CO2){
            CO2List.setItems(ListCout);
        }
        if(voyageur.getPreference() == TypeCout.PRIX){
            PrixList.setItems(ListCout);
        }
        if(voyageur.getPreference() == TypeCout.TEMPS){
            TempsList.setItems(ListCout);            
        }
    }

    private List<String> modaliteToString(List<Chemin> chemins){
        List<String> renvoie = new ArrayList<String>();
        for(Chemin chemin : chemins){
            String liste = "";
            for(Trancon arrete : chemin.aretes()){
                if(!liste.contains(arrete.getModalite().name())){
                    if(!liste.isEmpty()){
                        liste += ", ";
                    }
                    liste += arrete.getModalite().name();
                }
            }
            renvoie.add(liste);
        }
        return renvoie;
    }

    @FXML
    private List<String> CheminsToString(List<Chemin> chemins, String ville){
        List<String> villes = new ArrayList<>();
        for(int i = 0; i<chemins.size();i++){
            villes.add(ville);
        }
        return villes;
    }


    @FXML
    private List<String> ListLieuxToString(Set<Lieu> lieux){
        List<String> nomsLieux = new ArrayList<>();
        for (Lieu lieu : plateforme.getLieux()) {
            nomsLieux.add(((MonLieu)lieu).getNom());
        }
        return nomsLieux;
    }

}

