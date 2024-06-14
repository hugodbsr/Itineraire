# SAE S2.02 - Rapport pour la ressource Dev-OO


##### *De Hugo DEBUYSER, Gaël Dierynck, Maxence Antoine, groupe F*
___
## Version 1 : Un seul moyen de transport

#### Dans cette première version, il fallait faire la base du programme de cette saé : Calculer un itinéraire optimal entre deux villes, avec une seule modalité de transport et un seul type de ligne. Pour plus de renseignements, rendez vous sur le rapport de la partie ***Graphes***.
---
![UML](../../Downloads/UML.png)

Notre code se décompose en 9 classes et 4 classes de test. 

1 - L'énumération `TypeCout`
    
L'énumération TypeCout.java permet de d'énumerer par le type TypeCout la contrainte du **Prix**, du **Temps** et du **CO2** émis. 

&nbsp; 

2 - La classe `MonLieu` 

    La Classe MonLieu.java permet de décrire un lieu utilisé pour un potentiel transport. 

Son seul attribut est  `String Nom`, qui naturellement, donne un nom au lieu. 

La classe fournit un comparatateur d'objets MonLieu avec `boolean equals(Object Obj)`
    Elle compare un Object MonLieu avec un other MonLieu. 

&nbsp; 

3 - La classe `Voyageur`

    La classe Voyageur.java permet de décrire un voyageur avec un nom, une préférence de modalité entre le coût, les émissions de CO2, le temps et un moyen de transport.

Ses attributs sont : 
`String Nom`
`TypeCout preference`
`ModaliteTransport transport`
`int prix` `int co2` `int temps`

&nbsp; 

4 - La classe `MonTroncon`

    Cette classe permet de décrire un tronçon de transport avec un lieu de départ, d'arrivée, une modalité de transport et un coût.
Ses attributds sont : 
`MonLieu depLieu` `MonLieu aLieu` ` ModaliteTransport modaliteTransport` ` HashMap<TypeCout , Double> cout`

5 - La classe 