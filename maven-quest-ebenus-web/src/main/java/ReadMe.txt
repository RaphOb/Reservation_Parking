Reponse question 1 : Qu’est-ce qu’un serveur d’applications et quel est son rôle ?
C'est un logiciel offrant un environnement d'exécution pour des composants applicatifs. Facilitant le développement et le déploiement du code
Les principaux services sont : accés  aux bases de données, gestion de transactions, La connexion au systeme d'information de l'entreprise,
gestion de l'authentification.




Reponse question 2 : Quelle est la différence entre TomCat et GlashFish ?
TomCat est un conteneur web, un moteur de servlet, il n'a pas les fonctionnalités d'un serveur d'application.
Il est considéré comme un "web server".
Aucun support commercial et supporté par la communauté.

GlashFish est un serveur d'application complet pour Java EE
IL est la référence du serveur d'application java EE.
Les features principales sont : Servlets, Enterprise java Beans, Java Persistence API, JavaServer Faces,
Java Message Service
GlashFish est sous licence CDDL et GPL



Reponse question 3 :Dans le modèle MVC de cette étape, quel est le modèle ? Quelle est la vue ? Quel est le contrôleur ?
Le modèle est : le modèle se fait à travers l'interface IserviceFacade qui va appeler la dao nécessaire ( ici utilisateur et role).

Le contrôleur est : les servlet ( CrudUserServlet, LoginServlet, LogoutServlet).

La vue est : Le fichier jsp (addUpdateUser.jsp, allUser.jsp.




