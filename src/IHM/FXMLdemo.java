package IHM;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLdemo extends Application{

    public void start(Stage stage) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlFileUrl = getClass().getResource("Main.fxml");
            if (fxmlFileUrl == null) {
                    System.out.println("Impossible de charger le fichier fxml");
                    System.exit(-1);
            }
            loader.setLocation(fxmlFileUrl);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("FXML demo");
            stage.show();
    }   

    public static void main(String[] args) {
            Application.launch(args);
    }
}