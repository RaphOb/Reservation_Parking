Reponse question 1 :

Méthode toString:
Donne une description personnalisable des objets de la class qui implémente cette méthode

Méthode equals:
Permet de définir en quoi 2 objets sont égaux (Compare 2 instances de class)

Méthode HashCode:
Permet de retrouver un résumé d'une instance(plus rapide qu'avec la méthode equals qui elle compare directement les instances)



Reponse question 2 :

List et Tableau ?
List est une interface permettant une gestion dynamique de son contenu, contrairement au tableau qui lui a une taille fixe et non modifiable.

List et ArrayList ?
List est une interface définissant les méthodes nécessaire pour manipuler et traiter ses éléments.
ArrayList est une class implémentant cette interface.


Map et HashMap ?
Map est une interface permettant le stockage d'informations sous la forme (clé, valeur)
HashMap est une class implémentant cette interface et stockant les informations de manière non ordonnée dans une table de hashage

Set et HashSet ?
Set est une interface fonctionnant comme une liste, mais refusant les valeurs dupliquées
HashSet est une class implémentant cette interface et stockant ses éléments dans une table de hachage.

List et Set ?
3 différences:

Contrairement à List:
- Set n'autorise pas de doublons
- Set n'autorise qu'une valeur nulle (List n'imporete quel nombre)
- Set ne maintient aucun ordre



Reponse question 3 :
Quels sont les avantages de l’utilisation du Design Pattern DAO ?

Ce Design Pattern permet de faire la séparation entre la couche d'accès aux données (BDD,Excel,...) et les objets JAVA qui eux manipulent ces données
Cela permet entre autre de pouvoir changer de modèle d'accès aux données sans impacter la partie manipulation.


