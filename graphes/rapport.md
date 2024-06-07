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

Nous allons effectuer l'implémentation de notre cas dans la classe de test `Version1Test.java` qui se trouve dans le commit **84297a0870cc2c5ba3191273950bccaf026624fb** à la date du **20/05/2024**.
Lien : https://gitlab.univ-lille.fr/sae2.01-2.02/2024/F8/-/commit/84297a0870cc2c5ba3191273950bccaf026624fb


Version 2 : multimodalité et prise en compte des correspondances
---

### Présentation d'un exemple

Toto habite à Paris et souhaite se rendre à l'IUT de Lille afin d'assister à un cours important. Cette fois-ci, Toto dispose de différentes options de déplacement impliquant plusieurs moyens de transport et des correspondances entre ceux-ci. Toto a toujours les mêmes préoccupations : minimiser le coût de son trajet, réduire son empreinte écologique et limiter la durée de son voyage.

#### Plateforme :
| Départ      | Arrivée    | Moyen | Prix (en €) | Pollution (en kg CO2) | Durée (en minutes) |
|-------------|------------|-------|-------------|------------------------|---------------------|
| Paris Nord  | Lille Flandres | Train | 25          | 0.8                    | 80                  |
| Paris Nord  | Lille Flandres | Bus   | 10          | 0.5                    | 120                 |
| Paris Nord  | Arras      | Train | 15          | 0.5                    | 60                  |
| Paris Nord  | Arras      | Bus   | 8           | 0.4                    | 90                  |
| Paris Nord  | Douai      | Train | 20          | 0.6                    | 70                  |
| Paris Nord  | Douai      | Bus   | 12          | 0.4                    | 100                 |
| Lille Flandres | IUT        | Métro | 2           | 0.1                    | 15                  |
| Lille Flandres | IUT        | Bus   | 0.5         | 0.05                   | 20                  |
| Arras       | IUT        | Bus   | 1           | 0.1                    | 25                  |
| Douai       | IUT        | Bus   | 1.5         | 0.15                   | 30                  |

#### Correspondances :
| Ville          | Correspondance 1 | Correspondance 2 | Prix (en €) | Pollution (en kg CO2) | Durée (en minutes) |
|----------------|------------------|------------------|-------------|------------------------|---------------------|
| Paris Nord     | Train            | Bus              | 0           | 0                      | 10                  |
| Paris Nord     | Bus              | Bus              | 0           | 0                      | 5                   |
| Lille Flandres| Train            | Métro            | 0           | 0                      | 5                   |
| Lille Flandres| Bus              | Métro            | 0           | 0                      | 10                  |
| Arras          | Train            | Bus              | 0           | 0                      | 5                   |
| Douai          | Train            | Bus              | 0           | 0                      | 5                   |

Toto cherche à trouver les itinéraires les moins chers pour se rendre à l'IUT de Lille tout en minimisant son empreinte écologique et en tenant compte du temps de trajet. Les meilleures options pour lui seront celles qui offrent le meilleur compromis entre ces critères, en tenant compte des correspondances entre les différents moyens de transport.

### Modèle pour l'exemple

Pour modéliser le problème de Toto dans la Version 2, nous pouvons toujours utiliser un graphe où les sommets représentent les différentes gares et les arêtes représentent les liaisons entre ces gares, tout en tenant compte des correspondances entre les moyens de transport.

Les meilleurs itniéraires pour toto sont :
1. Paris Nord - Lille Flandres (Train) - IUT (Métro) pour un total de 27€
2. Paris Nord - Lille Flandres (Bus) - IUT (Bus) pour un total de 10.5€
3. Paris Nord - Arras (Train) - IUT (Bus) pour un total de 16€

Les poids des arêtes incluront désormais les critères d'optimisation ainsi que les coûts de correspondance entre les moyens de transport. L'algorithme de Dijkstra sera également utilisé pour trouver les itinéraires optimaux.

### Modélisation pour la Version 2 dans le cas général

### Modélisation pour la Version 2 dans le cas général

Dans cette version, nous prenons en compte les correspondances entre les différents moyens de transport et leurs coûts associés. Nous utilisons un modèle de graphe pour représenter les différents itinéraires possibles, en tenant compte des correspondances et de leurs implications sur le prix, l'empreinte écologique et la durée du trajet.

Pour illustrer notre modèle, voici comment notre graphe pourrait être représenté avec notre exemple :

**Sommets :**
  - Paris Nord
  - Lille Flandres
  - Arras
  - Douai
  - IUT

**Arêtes :**
  - Paris Nord --> Lille Flandres : Train (25€, 0.8kg, 80min)
  - Paris Nord --> Lille Flandres : Bus (10€, 0.5kg, 120min)
  - Paris Nord --> Arras : Train (15€, 0.5kg, 60min)
  - Paris Nord --> Arras : Bus (8€, 0.4kg, 90min)
  - Paris Nord --> Douai : Train (20€, 0.6kg, 70min)
  - Paris Nord --> Douai : Bus (12€, 0.4kg, 100min)
  - Lille Flandres --> IUT : Métro (2€, 0.1kg, 15min)
  - Lille Flandres --> IUT : Bus (0.5€, 0.05kg, 20min)
  - Arras --> IUT : Bus (1€, 0.1kg, 25min)
  - Douai --> IUT : Bus (1.5€, 0.15kg, 30min)

**Correspondances :**

**Lille Flandres :**
  - Train --> Bus : 0€, 0kg, 10min
  - Train --> Train : 0€, 0kg, 2min
  - Bus --> Bus : 3€, 0kg, 3min

**Arras :**
  - Train --> Bus : 0€, 0kg, 10min
  - Train --> Train : 0€, 0kg, 2min
  - Bus --> Bus : 3€, 0kg, 3min

**Douai :**
  - Train --> Bus : 0€, 0kg, 10min
  - Train --> Train : 0€, 0kg, 2min
  - Bus --> Bus : 3€, 0kg, 3min

Cette modélisation nous permet de représenter efficacement les différents itinéraires possibles pour Toto, et de prendre en compte les divers moyens de transport ainsi que les correspondances disponibles.

### Implémentation de la Version 2
La classe de test `Version2Test.java` sera utilisée pour implémenter et tester la solution pour la Version 2. Cette classe se trouve dans le commit **9c161c703216d025680e46c5528956e68998b5db** à la date du **7 Juin 2024 à 22:56**.
(Les classes de test ne fonctionne pas, mais la logique devrais être la bonne, cela est surement dûs au fait que certaines classes ne fonctionne pas correctement)

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