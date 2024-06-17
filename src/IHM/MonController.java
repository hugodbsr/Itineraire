package IHM;
import Poo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
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


    private void switchPane(AnchorPane pane) {
        stackPane.getChildren().forEach(child -> child.setVisible(false));
        pane.setVisible(true);
    }

    public void initialize() {
            System.out.println("Initialisation...");
            histoButton.setOnAction(event -> {
                    System.out.println();
            });
    }


    public void pressedButtonHisto() {
        menuButton.setOnAction(event -> {
                switchPane(choixApane);
                });
    }

    public void pressedButtonRechercher() {
        rechercherButton.setOnAction(event -> {
                switchPane(rechercherApane);
                });
    }

    public void pressedButtonStop() {
        saveExitButton.setOnAction(event -> {
                System.exit(0);
        //TODO : appeller la fonction save de la classe Historique        
        });
    }

    public void pressedButtonFinaliser() {
        finaliserButton.setOnAction(event-> {
            switchPane(renduApane);
        });
    }
    //Fonction de test
    /*public void pressedButtonPlus(ActionEvent event) {
            int newValue = Integer.parseInt(tempsField.getText()) + 1;
            tempsField.setText("" + newValue);
    }*/

}

