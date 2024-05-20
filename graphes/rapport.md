SAE S2.02 -- Rapport pour la ressource Graphes
===

*Hugo DEBUYSER, Gaël Dierynck, Maxence Antoine, groupe F*


Version 1 : un seul moyen de transport
---

### Présentation d'un exemple

Toto habite à Paris et souhaite se rendre à l'IUT de Lille afin d'assister à un cours important. Pour ce faire, il dispose de plusieurs options de déplacement en train entre différentes gares. Toto préfère minimiser le coût de son trajet tout en minimisant son empreinte écologique, tout en tenant compte du temps de trajet.

| Départ      | Arrivée    | Moyen | Prix (en €) | Pollution (en kg CO2) | Durée (en minutes) |
|-------------|------------|-------|-------------|------------------------|---------------------|
| Paris Nord  | Lille Flandres | Train | 25          | 0.8                    | 80                  |
| Paris Nord  | Arras      | Train | 15          | 0.5                    | 60                  |
| Paris Nord  | Douai      | Train | 20          | 0.6                    | 70                  |
| Lille Flandres | IUT        | Train | 5           | 0.2                    | 30                  |
| Arras       | IUT        | Train | 10          | 0.3                    | 40                  |
| Douai       | IUT        | Train | 8           | 0.25                   | 35                  |

Toto cherche à trouver les itinéraires les moins chers pour se rendre à l'IUT de Lille tout en minimisant son empreinte écologique et en tenant compte du temps de trajet. Les meilleures options pour lui seront celles qui offrent le meilleur compromis entre ces critères.


### Modèle pour l'exemple

Pour modéliser le problème de Toto, nous pouvons représenter les différentes gares comme des sommets dans un graphe. Les liaisons entre ces gares, accessibles par train, seront représentées par des arêtes. Les poids de ces arêtes seront déterminés par les critères d'optimisation choisis par Toto, à savoir le prix du billet, l'empreinte carbone et la durée du trajet.

Les meilleurs chemins pour Toto sont :

1. Paris Nord - Lille Flandres - IUT pour 30€.
2. Paris Nord - Douai - IUT pour 28€.
3. Paris Nord - Arras - IUT pour 25€.

Ces chemins sont les meilleurs compromis entre le prix du billet, l'empreinte carbone et la durée du trajet.

### Modélisation pour la Version 1 dans le cas général

Dans le cas général, pour construire un graphe permettant de résoudre un problème de recherche d'itinéraire, nous considérons chaque point de départ et d'arrivée comme des sommets du graphe. Les liaisons entre ces sommets, représentant les différentes possibilités de trajet, sont représentées par des arêtes.

Les poids des arêtes dépendent des critères d'optimisation choisis par l'utilisateur, tels que le prix du billet, l'empreinte écologique ou la durée du trajet. Par exemple, une arête entre deux sommets peut avoir comme poids le prix du billet, la quantité de CO2 émise ou la durée du trajet associée à ce trajet.

L'algorithme de Dijkstra peut être utilisé pour résoudre le problème d'itinéraire. Cet algorithme trouve le chemin le plus court entre deux sommets dans un graphe pondéré. En lui fournissant le graphe représentant les différentes options de trajet ainsi que les critères d'optimisation, l'algorithme de Dijkstra peut être utilisé pour trouver les meilleurs itinéraires en fonction des préférences de l'utilisateur.


### Implémentation de la Version 1

Nous allons effectuer l'implémentation de notre cas dans la classe de test `Version1Test.java` qui se trouve dans le commit ** ** à la date du ****.
Lien : 



Version 2 : multimodalité et prise en compte des correspondances
---

*Cette section explique la solution pour la Version 2 du projet.*

### Présentation d'un exemple

*Présenter un exemple concret (plateforme, couts de correspondance, critère d'optimalité).*
*Donner la solution du problème du point de vue de l'utilisatrice (quels sont les itinéraires possibles, lesquels sont optimaux et pourquoi).*
*Il est possible d'utiliser le même exemple que pour la Version 1 ou le modifier si pertinent.*

### Modèle pour l'exemple

*Donner le graphe modélisant l'exemple ci-dessus.*
*Donner la solution du problème (càd les meilleurs itinéraires) en tant que chemins dans le graphe.*

### Modélisation pour la Version 2 dans le cas général

*Mêmes questions que pour la section correspondante de la Version 1, mais cette fois-ci les données d'entrée contiennent aussi des couts de correspondance.*
*Vous pouvez expliquer l'entièreté de la solution pour la Version 2, ou bien indiquer **clairement** les différences par rapport à la solution proposée pour la Version 1.*

### Implémentation de la Version 2

*Écrire une classe de test qui reprend l'exemple, définit toutes les données de la plateforme, construit le graphe et calcule la solution.*
*Votre classe peut utiliser des assertions (test unitaire) ou bien afficher la solution.*
*Donner ici le **nom complet de la classe**, **la date et l'identifiant du commit à regarder** et un **lien vers la page de cette classe sur gitlab qui correspond au bon commit***.
*En particulier, il peut s'agir de la même classe que celle donnée pour la Version 1, mais un commit différent.*


Version 3 : optimisation multi-critères
---

*Suivre le même plan que pour les deux autres sections.*
*Pour l'exemple, veillez à spécifier toutes les données des problèmes. En particulier, on ajoute ici l'expression des préférences d'optimisation de l'utilisatrice.*
*Comme précédemment, il est possible d'utiliser le même exemple et simplement l'enrichir.*

----------------------------------------------------

**Fin du rapport**

### Barème sur 30 pts

Toute question sur le barème est à adresser à iovka.boneva@univ-lille.fr


- Rapport non rendu à temps -> note 0 
- **(7, décomposé comme suit)** Divers
  - **(1,5)** Respect de la structure du rapport
  - **(1,5)** Section Version 1 rendue pour le 18/05/2024. Cette version peut contenir les parties en italique.
  - **(1,5)** Section Version 2 rendue pour le 08/06/2024. Cette version peut contenir les parties en italique.
  - **(1)** Utilisation de vocabulaire précis sur les graphes (termes vu en cours, noms des algorithmes, etc.)
  - **(1,5)** Style d'écriture fluide et compréhensible

- **(8, décomposé comme suit)** Solution pour la Version 1
  - **(2)** Exemple pertinent (illustre tous les aspects du problème) et lisible (en particulier, ni trop grand ni trop petit, bien présenté)
  - **(4)** Le modèle de l'exemple permet de trouver la solution sur l'exemple. La modélisation pour le cas général permet de résoudre le problème posé
  - **(2)** L'implémentation de l'exemple est correcte et fonctionnelle

- **(6, décomposé comme suit)** Solution pour la Version 2
  - **(1)** Exemple pertinent
  - **(4)** le modèle de l'exemple permet de trouver la solution sur l'exemple. La modélisation pour le cas général permet de résoudre le problème posé
  - **(1)** L'implémentation de l'exemple est correcte et fonctionnelle

- **(3)** Qualité de la description de la solution (concerne les sections "Modèlisation dans le cas général" pour les Versions 1 et 2):
  - La modélisation pour le cas général est décrite de manière abstraite mais précise et complète. Pour vous donner une idée, un·e étudiant·e de BUT qui a validé les ressources Graphes et Dev devrait être en mesure d'implémenter votre solution d'après la description que vous en faites, sans avoir à trop réfléchir.

- **(6)** Solution pour la Version 3: mêmes critères que pour la Version 2