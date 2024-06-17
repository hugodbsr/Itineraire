package IHM;
import Poo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stackPane;
public class MonController {
    @FXML
    TextField tempsField;

    @FXML
    Button rechercherButton;

    @FXML
    Button histoButton;

     @FXML
     CheckBox checkTrain;


        private void switchPane(Pane pane) {
                stackPane.getChildren().forEach(child -> child.setVisible(false));
                pane.setVisible(true);
    }
    public void initialize() {
            System.out.println("Initialisation...");
            histoButton.setOnAction(event -> {
                    System.out.println();
            });
    }

    public void pressedButtonPlus(ActionEvent event) {
            int newValue = Integer.parseInt(tempsField.getText()) + 1;
            tempsField.setText("" + newValue);
    }

}

