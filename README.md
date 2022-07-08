# **A more realistic population growth**

![](https://raw.githubusercontent.com/rafaelbenaion/project-rabbitSimulation/master/src/Aspose.Words.00945d46-d327-4f90-b616-090ba31b7551.001.png)


Simulation Licence Informatique
Université Clermont Auvergne

Le présent rapport est un mémoire du dernier TP réalisé au cours de Projet Informatique Simulation, Licence Informatique en deuxième année à l'Université Clermont Auvergne. Le but du projet est d’appliquer les connaissances acquises pendant les cours magistraux et les séances pratiques. Nous implémentons ainsi une simulation de croissance de population de lapin plus fiable. En utilisant notamment un générateur pseudo aléatoire plus robuste, la technique de Monte Carlo et un calcul d’intervalle de confiance. Les choix de modélisation et représentation ont été exposés tout au long du document.


**Chapitre 1**
A fast simulation of a rabbit population growth

**1.1 La modélisation**

Dans cette simulation, nous avons une vision plus simplifiée du modèle de croissance d’une population de lapin. Nous considérons qu'à chaque mois (unité de temps virtuel) un couple de lapin bébé devient adulte. Chaque couple adulte produit 2 bébés par unité de temps. Il est sous-entendu aussi que les lapins ne meurent jamais. Il s'agit alors d’une représentation directe de la suite de Fibonacci.

**1.2 L’implémentation**

L’implémentation a été faite en C, utilisant un tableau d’entiers pour stocker le nombre de la population à chaque mois. Nous avons comme input un entier pour le nombre de mois à simuler, on l’utilise pour la boucle for. À chaque étape i, le résultat est la somme de la population de l’étape i - 1 plus l’étape i - 2.

**1.3 Les résultats**

![](https://raw.githubusercontent.com/rafaelbenaion/project-rabbitSimulation/master/src/Aspose.Words.00945d46-d327-4f90-b616-090ba31b7551.004.png)

Au bout de 30 répétitions (30 mois) nous avons comme nombre total de la population 514.229 couples. La croissance suit bien sur la suite de Fibonacci. La modélisation est trop simpliste et ne prend pas en compte plusieurs facteurs. Nous poursuivons l’expérience dans le prochain chapitre, abordant d’une autre manière le problème avec une simulation plus complexe.

**Chapitre 2**
A more realistic population growth

**2.1 La modélisation**

Le but de la deuxième partie du TP c’est d'implémenter une simulation plus réaliste, en prenant en considération plusieurs facteurs. Nous considérons ainsi, pour chaque expérience, les variables suivantes : la quantité de portée d’une lapine, le pourcentage de mortalité par accouchement, le taux d’infertilité, le taux de survie, l’âge maximal et l’âge de maturité sexuelle. Focalisons-nous d'abord sur le pourcentage de portée d’une lapine par an. Nous pouvons représenter le choix de probabilité par le graphique présent dans la figure 4. La portée varie entre 0 et 12. La chance d’aboutir dans chaque portée n’est pas uniforme, alors nous avons choisi chaque pourcentage pour que la somme totale donne 100%.

![](https://raw.githubusercontent.com/rafaelbenaion/project-rabbitSimulation/master/src/Aspose.Words.00945d46-d327-4f90-b616-090ba31b7551.005.png)

 Après avoir déterminé la probabilité de chaque catégorie, nous sommes arrivés aux probabilités cumulatives de portées par an. La probabilité cumulative va être utile plus tard dans l’implémentation pour simuler la quantité de portée avec un générateur aléatoire.
 
![]( https://raw.githubusercontent.com/rafaelbenaion/project-rabbitSimulation/master/src/Aspose.Words.00945d46-d327-4f90-b616-090ba31b7551.006.png)

Quelques autres constantes sont aussi importantes pour la suite. Chaque bébé lapin a 50% de chance de survie par an, le lapin adulte a 75%. Après l’âge de 7 ans, à chaque année le lapin perd 15% de taux de survie. Une lapine a 10% de chance de mourir par l’accouchement, et 15% de devenir infertile à chaque année.
Cela fait, nous pouvons maintenant attaquer la modélisation du programme. Nous avons ainsi organisé et défini toutes les structures de données nécessaires pour l’implémentation du programme en Java. Le pictogramme de la Figure 6 est le résultat en l’UML de la modélisation. Nous avons une classe abstract appelée Rabbit, qui contient tous les attributs et méthodes de base pour le fonctionnement d’un lapin. Ensuite, la classe Female hérite de la précédente et implémente d’autres méthodes spécifiques pour la reproduction de l’espèce. La classe Colony gère l’ensemble de lapins qui appartiennent au même groupe, on a ainsi des méthodes qui impactent toute la colonie. À la fin, la classe Environment sert de couche principale pour le déroulement des simulations de et pour l’analyse statistique de chaque réplication.


![](https://raw.githubusercontent.com/rafaelbenaion/project-rabbitSimulation/master/src/Aspose.Words.00945d46-d327-4f90-b616-090ba31b7551.007.jpeg)

Le programme de simulation se déroule en un temps virtuel à l’échelle d’un mois. Nous avons aussi fait le choix de faire quelques traitements au niveau du mois et d’autres au niveau de l’année (à chaque 12 mois). Le tableau suivant représente la séparation du traitement.

**2.2 L’implémentation**

Le Java est le langage choisi pour l’implémentation du programme. L’option de l’orienté objet nous semblait plus adaptée, prenant en compte la modélisation d’une simulation plus complexe avec plusieurs acteurs et facteurs à gérer. Pour les tirages de numéros aléatoires, nous utilisons ici une version Java du générateur Mersenne Twister de Makoto Matsumoto. Cette version a été conçue et optimisée par Sean Luke. L’initialisation du générateur est faite qu’une seule fois, dans une autre classe statique (MTRandom.java).

**2.3 Les résultats**

Une fois l’implémentation du programme finalisée, nous avons initié les simulations. Nous avons décidé de commencer l’expérience avec 10 couples de lapins. Nous pouvons ainsi observer le résultat au bout de 10 ans, 20 ans et 30 ans, dans l’extrait ci-dessous.

Total males:      3.090
Total females:    2.076
Total infertiles: 393
Total babies:     2.988
Total deaths:     6.667

Month:120 (10 ans)


Total males:      583.668
Total females:    382.757
Total infertiles: 71.713
Total babies:     557.914
Total deaths:     1.335.850

Month:240 (20 ans)


Total males:      110.402.098
Total females:    72.796.892
Total infertiles: 13.598.520
Total babies:     106.011.115
Total deaths:     253.131.737

Month:360 (30 ans)
 
En analysant de plus prêt les résultats de 20 ans de simulation ; nous pouvons observer que la croissance de la population suit une logique exponentielle. Dans la répartition de la population, nous pouvons aussi constater un écart entre la population de femelles et la population de mâles, due à la mortalité maternelle. Les femelles infertiles ne sont pas comptabilisées ici dans la catégorie “femelle”.

![](https://raw.githubusercontent.com/rafaelbenaion/project-rabbitSimulation/master/src/Aspose.Words.00945d46-d327-4f90-b616-090ba31b7551.020.png)

![](https://raw.githubusercontent.com/rafaelbenaion/project-rabbitSimulation/master/src/Aspose.Words.00945d46-d327-4f90-b616-090ba31b7551.021.png)

L’étape suivante, c'est de calculer l’écart de fiabilité. Nous avons ainsi mis en place un système de réplication des simulations, pour avoir un résultat plus précis. Il nous semble nécessaire de friser l’importance du rôle que joue le générateur MTRandom dans la réplication. Son initialisation doit être effectuée seulement une fois, pour ne pas compromettre la reproductibilité de l’expérience. Les paramètres pris en compte pour le calcul sont : la population totale de mâles, la population totale de femelles, la population totale d’infertiles et la population totale de bébés.

Pour des raisons de performance, nous allons d’abord commencer avec des simulations de 12 mois pour les réplications. Nous constatons ainsi que plus on simule, plus on a besoin de répétitions pour arriver à un intervalle de confiance fiable. Ça pose notamment des limitations pour la capacité de calcul.

**Conclusion**

Le TP4 de Projet Informatique nous a permis d’appliquer les connaissances acquises pendant tout le parcours du cours jusqu’ici. C’est une façon de tester l'assimilation des techniques. Mais aussi de vérifier si nous étions capables de trouver des solutions différentes, avec les outils qui nous ont été fournis au préalable. L’implémentation d’une simulation plus réaliste nous semble être bien exécutée. Le choix du langage nous a facilité l’abstraction du code, et on a pu focaliser notre travail sur l’interaction des agents. Une chose est claire, n’importe combien la simulation peut être complexe, elle reste une simplification de la réalité. 

Il y a plusieurs facteurs qu’on aurait pu prendre en compte (Les prédateurs, les changements de saisons, des maladies). Nous avons ainsi, plusieurs options d’amélioration pour une possible nouvelle version. Le pouvoir de calcul est la clé de la complexité de la simulation. Notre système est susceptible à la performance du programme pour qu’on puisse envisager d’avoir des résultats dans un espace de temps acceptable. Finalement, le cours a été extrêmement enrichissant et nous a ouvert les portes à pleins de domaines passionnants pour l’application de l’informatique. La simulation s’est montrée être un outil indispensable pour mieux comprendre le monde qui nous entoure.

