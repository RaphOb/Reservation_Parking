Reponse question 1 : Qu’est-ce qu’une injection SQL ?
Une injection SQL, est une faille qu'une personne mal intentionné souhaite exploiter en passant des requetes SQL
à un endroit non prévu par le programme.En d'autre mots, l'attaquant modifie une requete SQL en injectant sa requete dans une portion non filtré,
generalement dans un input ou form. N'importe quelle application connecté à une database peut etre vulnerable à ce type d'attaque.



Reponse question 2 : Quelle est la différence entre Statement et PreparedStatement ? Quels sont les avantages de l’un par rapport à l’autre ?
L'interface PreparedStatement qui herite de l'interface Statement permet de passer des parametres à la requetes contraitement à l'interface Statement.
Deplus les requetes sont compilées une seul fois, ce qui permet d'executer des requetes rapidements.



Reponse question 3 : Quelle est la différence entre une jointure totale, INNER JOIN, LEFT JOIN, et RIGHT JOIN ?
Totale JOIN :Jointures externe pour retourner les resultats quand la conditions est vrai dans au moins une des deux tables.
INNER JOIN : Jointure interne pour retourner les enregistrements quand la condition est vrai dans les 2 tables.
LEFT JOIN : jointure externe pour retourner tous les enregistrements de la table gauche meme si aucune correspondance n'a ete trouve dans la table de droite.
(Nous l'avons utilisé pour l'utilisateur (left) et Role (right))
RIGHT JOIN : Jointure externe pour retourner toutes les correspondances de la table de droite meme si la condition n'est pas verifiée dans l'autre table




