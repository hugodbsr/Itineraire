java --module-path ./lib/javafx/ --add-modules javafx.controls,javafx.fxml -jar application.jar
if [ $? -eq 0 ];
then
    echo 'Lancement'
else 
    echo 'Erreur'
fi