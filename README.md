# DevOps_Pandas


## Fonctionalités implementées 

Notre librerie Pandas pour java implemente les fonctions de bases suivantes :

- Creation d'une DataFrame à partire d'un tableau -> Cette fonction prend en entrées une liste de labels et un tableau de données. Le tableau contient des liste, dont les donées d'une meme liste sont tu meme type. 

- Creation d'une DataFrame à partie d'un fichier csv -> Cette fonction prend en entrée un fichier en format csv est le parcour pour initialisée les donnée et labels.

- 


## Outiles utilisés 

nous avons utilisés deux outils pour notre developpement : 

Maven -> Cette outil nous a permit de structurer notre projet, de tester facilement notre code ainsi que d'automatisser les test lors d'un merge dans la branche develop. Si une erreur est relevé lors des test le developeur resoit un mail pour le prevenire. Il est aussi utiliser dans la branche main en testant est creant le fichier JAR si tout ce passe bien.

Jacoco -> Cette outil nous a permit de tester la couverture de code. Lors d'un merge dans la branche main, une verification de la couverture de code par les test est effectuée, si la couverture est supperieur a 80% le JAR du projet est crée.


## Workflow

Nous avons choisit le WorkFlow gitFlow pour notre projet. Nous avons cree une branche main qui represente les versions deployer de notre librerie, pour push dans cette branche une pull request doit etre envoyais et valider par un autre developpeur. Une vois validée et verifiée le programme est deployer dans un fichier JAR.
Pour le developpement principal nous avons la branche develop, cette branche est aussi proteger par une obligation de faire des pull request.
A partire de la branche develop nous creont de nouvelle banche pour chaque nouvelle fonctionalitées que nous voulont ajouter, une fois une fonctionalité developper nous fesont une pull request pour merge dans la branche develop.


## Feedback


