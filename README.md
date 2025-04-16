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


## Outils utilisés

Nous avons utilisé deux outils pour notre développement :

Maven -> Cet outil nous a permis de structurer notre projet, de tester facilement notre code ainsi que d'automatiser les tests lors d'un merge dans la branche develop. Si une erreur est relevée lors des tests, le développeur reçoit un mail pour le prévenir. Il est aussi utilisé dans la branche main en testant et en créant le fichier JAR si tout se passe bien.

Jacoco -> Cet outil nous a permis de tester la couverture de code. Lors d'un merge dans la branche main, une vérification de la couverture de code par les tests est effectuée. Si la couverture est supérieure à 80%, le JAR du projet est créé.


## Workflow

Nous avons choisi le Workflow gitFlow pour notre projet. Nous avons créé une branche main qui représente les versions déployées de notre librairie. Pour push dans cette branche, une pull request doit être envoyée et validée par un autre développeur. Une fois validé et vérifié, le programme est déployé dans un fichier JAR.
Pour le développement principal, nous avons la branche develop, cette branche est aussi protégée par une obligation de faire des pulls request.
À partir de la branche develop, nous créons de nouvelles branches pour chaque nouvelle fonctionnalité que nous voulons ajouter. Une fois une fonctionnalité développée, nous faisons une pull request pour merge dans la branche develop.


## Feedback

