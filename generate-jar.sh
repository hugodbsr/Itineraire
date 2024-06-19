jar -cvfm application.jar manifest.mf ./bin/IHM/FXMLdemo.class ./bin/
if [ $? -eq 0 ];
then
    echo 'Jar generated'
else 
    echo 'Erreur: Veuillez verifier que le projet est bien compilé (existante du dossier bin)'
fi