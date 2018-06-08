# Megawatt

1.Introduction

Le projet est basé sur un jeu appelé Jeu de Haut Tension, qui est un jeu stratégique comprenant l'achat de ressources et la construction de centrales électriques pour l'approvisionnement de villes. Le contenu de notre projet comprend la numérisation d du jeu, la création de quelques joueurs IA et l'analyse de stratégies gagnantes.
 Le projet global est divisé en deux grands parties: IHM et IA. Nous utilisons JAVA pour la programmation, dans la partie IHM nous avons créé le back end puis une interface présentable du jeu. Dans la section IA, nous avons créé des lecteurs informatiques pour augmenter l'optimisation de différentes stratégies. Ceci dans le but que l'IA ait la capacité de "l'apprentissage automatique" ainsi que celle de pouvoir modifier ses stratégies de base pour atteindre un niveau correcte face a un joueur humain.


2.Règles du jeu
1.La composition du jeu
Le jeu se compose de joueurs, un graphe de villes, des centrales électriques et quatre types de ressources. Chaque tour de jeux comprend la vente aux enchères de centrales électriques, l'achat de ressources, l'achat de villes et l'obtention de l'argent par les villes que les centrales électriques peuvent fournir.

Le jeu original comprend,
Un plateau de jeu : L'Europe d'un côté et L'Amérique du Nord de L’autre ,la  carte est divisée en 7 zones,chacune comparent 7 villes.
Joueurs : six joueurs maximum représenter par six couleurs
Ressources : charbon, gaz naturel , pétrole et  uranium .
Cartes:43 Centrales électrique . 



4.Realissation des règles

1. La carte du jeu
  Le graphe des villes est stocké dans un simple fichier txt lu par les fonctions d’initialisations. La forme la plus simple pour représenter un graph n’excédant pas les 50 noeuds el évidemment un matrice de connexion. Cependant pour un parcours plus efficace de l'algorithme de Dijkstra la représentation dans java (après l’initialisation) est une représentation par adjacence.

Plutôt que de laisser le choix au joueur de choisire un chemin au risque qu’il ne prenne pas l'optimum nous avons opté pour un choix légèrement plus coûteux en calcules mais  bien plus simple a implémenter. C’est à dire qu’un joueur ne choisit que la ville qu’il désire acheter et le jeux calcule pour lui le plus court chemin en coût pour y arriver. Ce qui nous économise la vérification de chemin possiblement aberrant sélectionnés par un joueur.

Une méthode appelé plusCourtChemin utilise donc l'algorithme de Dijkstra a entré multiple pour mesurer le coût le plus faible entre les villes déjà possédé par un joueur et la destination désirée. 

2. Les joueurs du jeu
Nous représentons un joueur par son ID, il aurait été possible de s’en passer mais cela nous a parfois simplifier le travail en éviter des complexite ou des problèmes particulier ex: mauvais pointeurs…). Ils ne sont au final qu'un nombre d'argent, des villes,  des ressources, et des usines.

3. Les villes, elles représente les noeuds du graphe. La seul autre utilité véritable est de tenir le compte des achats des joueurs en villes. 
