# Megawatt

1.Introduction

Le projet est basé sur un jeu appelé Jeu de Haut Tension, qui est un jeu stratégique comprenant l'achat de ressources et la construction de centrales électriques pour l'approvisionnement de villes. Le contenu de notre projet comprend la numérisation du jeu, la création de quelques joueurs IA et l'analyse de stratégies gagnantes.
Le projet global est divisé en deux grandes parties : IHM et IA. Nous utilisons JAVA pour la programmation, dans la partie IHM, nous avons créé le back end puis une interface présentable du jeu.


2. Règle du jeu
1.La composition du jeu
Le jeu se compose de joueurs, un graphe de villes, des centrales électriques et quatre types de ressources. Chaque tour de jeux comprend la vente aux enchères de centrales électriques, l'achat de ressources, l'achat de villes et l'obtention de l'argent par les villes que les centrales électriques peuvent fournir.

Le jeu original comprend:
Un plateau de jeu : L'Europe d'un côté et L'Amérique du Nord de L’autre,la carte est divisée en 7 zones, chacune compare 7 villes.
Joueurs : six joueurs maximum représentés par six couleurs
Ressources : charbon, gaz naturel , pétrole et uranium .
Cartes:43 Centrales électrique .



4.Réalisation des règles

1. La carte du jeu
Le graphe des villes est stocké dans un simple fichier txt lu par les fonctions d’initialisations. La forme la plus simple pour représenter un graphe n’excédant pas les 50 nœuds est évidemment une matrice de connexion. Cependant, pour un parcours plus efficace de l'algorithme de Dijkstra la représentation dans java (après l’initialisation) est une représentation par adjacence.

Plutôt que de laisser le choix au joueur de choisir un chemin au risque qu’il ne prenne pas l'optimum, nous avons opté pour un choix légèrement plus coûteux en calcul, mais bien plus simple à implémenter. C’est-à-dire qu’un joueur ne choisit que la ville qu’il désire acheter et le jeu calcul pour lui le plus court chemin en coût pour y arriver. Ce qui nous économise la vérification de chemin possiblement aberrant sélectionné par un joueur.

Une méthode appelée plusCourtChemin utilise donc l'algorithme de Dijkstra a entré multiple pour mesurer le coût le plus faible entre les villes déjà possédées par un joueur et la destination désirée.

2. Les joueurs du jeu
Nous représentons un joueur par son ID, il aurait été possible de s’en passer, mais cela nous a parfois simplifier le travail en éviter des complexité ou des problèmes particulier ex: mauvais pointeurs…). Ils ne sont au final qu'un nombre d'argents, des villes, des ressources, et des usines.

3. Les villes, elles représentent les nœuds du graphe. La seul autre utilité véritable est de tenir le compte des achats des joueurs en villes. 
