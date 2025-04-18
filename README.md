# DevOps_Pandas

[![CI & Deploy SNAPSHOT](https://github.com/TheoGM1/DevOps_Pandas/actions/workflows/deploy.yml/badge.svg?branch=main)](https://github.com/TheoGM1/DevOps_Pandas/actions/workflows/deploy.yml)
[![Java CI with Maven](https://github.com/TheoGM1/DevOps_Pandas/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/TheoGM1/DevOps_Pandas/actions/workflows/maven.yml)

## Fonctionnalités implémentées

Notre librairie Pandas pour java implémente les fonctions de bases suivantes :

- Création d'un DataFrame à partir d'un tableau -> Cette fonction prend en entrée une liste de labels et un tableau de données. Le tableau contient des listes, dont les données d'une même liste sont du même type.

- Création d'un DataFrame à partir d'un fichier CSV -> Cette fonction prend en entrée un fichier en format CSV et est le parcours pour initialiser les données et les labels.

- Affichage d'un DataFrame -> Cette fonction permet d'afficher le contenu d'un DataFrame sous la forme d'un string. La fonction parcours ligne par ligne en recuperant les valeur de chaque colonne.

- Affichage de x premières lignes d'un DataFrame -> Cette fonction permet d'afficher les x premières lignes d'un DataFrame. La fonction parcours ligne par ligne en recuperant les valeur de chaque colonne pour celle demandée. Si x est supérieur au nombre de lignes, elle affiche toutes les lignes.
  
- Affichage de x dernières lignes d'un DataFrame -> Cette fonction permet d'afficher les x dernières lignes d'un DataFrame. La fonction parcours ligne par ligne en recuperant les valeur de chaque colonne pour celle demandée, le parcours s'effectue en commencant par la premiere ligne que l'on doit afficher. Si x est supérieur au nombre de lignes, elle affiche toutes les lignes.

- Sélectionne des lignes selon un indice, une liste d'indices ou un intervalle, et crée un nouveau DataFrame -> La fonction parcourt les colonnes une à une, récupère les valeurs aux indices demandés, ainsi que les labels des colonnes.

- Sélectionne des colonnes selon un label ou une liste de labels, et crée un nouveau DataFrame -> La fonction parcourt les labels et récupère les colonnes correspondantes.

- Sélectionne des colonnes selon une condition, et crée un nouveau DataFrame avec les données qui respectent cette condition -> La fonction prend en entrée un label, un comparateur sous forme de chaîne ("=", "<", ">", "<=", ">=") et une valeur numérique (double) pour effectuer la comparaison.

- Modifie la valeur d’une Series grâce à son indice et son label -> Modifie la valeur dans la bonne série après avoir vérifié que le type est bien compatible.

- Change le label d’une colonne.

- Ajouter une ligne dans le DataFrame.

- Ajouter une colonne avec un label et une liste d'éléments.

- Calculer la moyenne d'une colonne
- Calculer la valeur maximale ou minimale d'une colonne


## Outils utilisés

Nous avons utilisé deux outils pour notre développement :

Maven -> Cet outil nous a permis de structurer notre projet, de tester facilement notre code ainsi que d'automatiser les tests lors d'un merge dans la branche develop. Si une erreur est relevée lors des tests, le développeur reçoit un mail pour le prévenir. Il est aussi utilisé dans la branche main en testant et en créant le fichier JAR si tout se passe bien.

Jacoco -> Cet outil nous a permis de tester la couverture de code. Lors d'un merge dans la branche main, une vérification de la couverture de code par les tests est effectuée. Si la couverture est supérieure à 80%, le JAR du projet est créé.


## Workflow

Nous avons choisi le Workflow gitFlow pour notre projet. Nous avons créé une branche main qui représente les versions déployées de notre librairie. Pour push dans cette branche, une pull request doit être envoyée et validée par un autre développeur. Une fois validé et vérifié, le programme est déployé dans un fichier JAR.
Pour le développement principal, nous avons la branche develop, cette branche est aussi protégée par une obligation de faire des pulls request.
À partir de la branche develop, nous créons de nouvelles branches pour chaque nouvelle fonctionnalité que nous voulons ajouter. Une fois une fonctionnalité développée, nous faisons une pull request pour merge dans la branche develop.

Lors d'un merge sur la branche main qui corespond au déploiement d'une nouvelle version, les séries de tests sont exécutées afin de vérifier que les tests passent toujours. Cela permet de vérifier la couverture de code de nos tests et de vérifier si le seuil de 80% de couverture est atteint. Si ce seuil n'est pas atteint, le build va fail.
Ensuite, si la couverture de code a atteint le seuil minimum, on peut déployer notre bibliothèque sur le dépôt Maven avec GitHub Packages.
Pour finir, on déploie une image Docker contenant notre librairie et un petit programme démontrant le fonctionnement.
 


## Feedback

Lors de l'utilisation de notre système, nous avons éprouvé quelques difficultés lors de certaines manipulations, dues à la présence de notre branche develop protégée.
En effet, lors d'une configuration du workflow gitFlow depuis une mauvaise branche, l'annulation de nos modifications fut plus complexes que prévue.
Nous avons aussi eu un peu de mal à faire redescendre nos changements dans la branche main, puisque après configuration du workflow directement dans cette branche, la branche develop devait être actualisée, alors que nous cherchions à actualiser au contraire la branche main, et la protection sur les deux branches n'a pas aidé.
Plus tard, le problème de configuration avec les fichiers yaml précédemment cité fut aussi lié à notre fonctionnement mis en place.
Néanmoins cette méthodologie restait simple, claire, et pratique pour la majorité du projet, et nous a donc apporté plus d'avantages que d'inconvénients.
Au final, c'est méthode mise en place pour notre projet nous a beaucoup changé de nos méthodes habituelle, et dans le bon sens, elle nous a empêché de nous éparpiller et nous a aussi forcé à adopter de bonnes pratiques pour travailler en groupe en répartissant le travail et en se tenant au courant.
Nous retiendrons donc cela pour de futurs projets.

