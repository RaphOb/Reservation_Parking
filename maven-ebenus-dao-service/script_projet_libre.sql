SET FOREIGN_KEY_CHECKS = 0; 
DROP TABLE IF EXISTS Utilisateur;
DROP TABLE IF EXISTS Role;
DROP TABLE IF EXISTS Voiture;
DROP TABLE IF EXISTS PlaceParking;

 CREATE TABLE Role (
	idRole INTEGER PRIMARY KEY AUTO_INCREMENT,
	identifiant VARCHAR(50),
	description VARCHAR(400)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 CREATE TABLE Utilisateur (
	idUtilisateur INTEGER PRIMARY KEY AUTO_INCREMENT,
	idRole INTEGER NOT NULL,
	civilite VARCHAR(100),
	prenom VARCHAR(100),
	nom VARCHAR(100),
	email VARCHAR(100),
	motPasse VARCHAR(100),
	dateCreation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	dateModification TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT `FK_Utilisateur_Role` FOREIGN KEY (idRole) referenceS Role (idRole)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;


 CREATE TABLE Voiture (
	idVoiture INTEGER PRIMARY KEY AUTO_INCREMENT,
	idUtilisateur INTEGER NOT NULL,
	FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(idUtilisateur),
	marque VARCHAR(50),
	immatriculation VARCHAR(10)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE PlaceParking (
 	idPlaceParking INTEGER PRIMARY KEY AUTO_INCREMENT,
 	idVoiture INTEGER,
 	FOREIGN KEY (idVoiture) REFERENCES Voiture(idVoiture),
	num INTEGER NOT NULL,
	available TINYINT(1) DEFAULT 1
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* Insertion des Roles */ 
INSERT INTO Role(identifiant,description) VALUES ('Administrateur','Le rôle administrateur');
INSERT INTO Role(identifiant,description) VALUES ('Directeur','Le rôle de directeur de magasin');
INSERT INTO Role(identifiant,description) VALUES ('Standard','Le rôle standard');


/* Utilisateurs avec le role Administrateur  */
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,email ,motPasse) VALUES ('Mr','Kamel','Petit',(SELECT idRole FROM Role WHERE identifiant LIKE  'Administrateur'),'kamel.petit@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom, idRole,email ,motPasse) VALUES ('Mr','Jerome','Cantin',(SELECT idRole FROM Role WHERE identifiant LIKE  'Administrateur'),'admin@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,email ,motPasse) VALUES ('Mr','Nicolas','Gruber',(SELECT idRole FROM Role WHERE identifiant LIKE  'Administrateur'),'nicolas.gruber@gmail.com','passw0rd');

/*  Utilisateurs avec le role Directeur  */  
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,email ,motPasse) VALUES ('Mr','Eric','Lalitte',(SELECT idRole FROM Role WHERE identifiant LIKE  'Directeur'),'eric.lallite@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,email ,motPasse) VALUES ('Mr','Laurent','Bordet',(SELECT idRole FROM Role WHERE identifiant LIKE  'Directeur'),'laurent.bordet@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,email ,motPasse) VALUES ('Mr','Jimmy','Petit',(SELECT idRole FROM Role WHERE identifiant LIKE  'Directeur'),'jimmy.petit@gmail.com','passw0rd');
/*  Utilisateurs avec le role Standard  */  
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,email ,motPasse) VALUES ('Mr','Nicolas','Petit',(SELECT idRole FROM Role WHERE identifiant LIKE  'Standard'),'nicolas.petit@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,email ,motPasse) VALUES ('Mr','Paul','Gilbert',(SELECT idRole FROM Role WHERE identifiant LIKE  'Standard'),'paul.gilbert@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,email ,motPasse) VALUES ('Mr','Xavier','Dupont',(SELECT idRole FROM Role WHERE identifiant LIKE  'Standard'),'xavier.dupont@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,email ,motPasse) VALUES ('Mr','Thierry','Marec',(SELECT idRole FROM Role WHERE identifiant LIKE  'Standard'),'thierry.marec@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,email ,motPasse) VALUES ('Mme','Aurelie','Cassas',(SELECT idRole FROM Role WHERE identifiant LIKE  'Standard'),'aurelie.cassas@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,email ,motPasse) VALUES ('Mr','Alain','Cassas',(SELECT idRole FROM Role WHERE identifiant LIKE  'Standard'),'alain.cassas@gmail.com','passw0rd');
INSERT INTO Utilisateur(civilite,prenom,nom,idRole,email ,motPasse) VALUES ('Mr','Julien','Bille',(SELECT idRole FROM Role WHERE identifiant LIKE  'Standard'),'julien.bille@gmail.com','passw0rd');


/* Insertion des Voitures */
INSERT INTO Voiture(idUtilisateur, marque, immatriculation) VALUES (1, 'Citroen', 'CZ-436-G7');
INSERT INTO Voiture(idUtilisateur, marque, immatriculation) VALUES (2, 'Mercedes', 'AV-526-V7');
INSERT INTO Voiture(idUtilisateur, marque, immatriculation) VALUES (3, 'Peugeot', 'QZ-896-L0');
INSERT INTO Voiture(idUtilisateur, marque, immatriculation) VALUES (4, 'Fiat', 'IY-392-G7');
INSERT INTO Voiture(idUtilisateur, marque, immatriculation) VALUES (5, 'Toyota', 'PB-199-PP');

/* Insertion des places de parking */
INSERT INTO PlaceParking(num) VALUES (1);
INSERT INTO PlaceParking(num) VALUES (2);
INSERT INTO PlaceParking(num) VALUES (3);
INSERT INTO PlaceParking(num) VALUES (4);
INSERT INTO PlaceParking(num) VALUES (5);
INSERT INTO PlaceParking(num) VALUES (6);
INSERT INTO PlaceParking(num) VALUES (7);
INSERT INTO PlaceParking(num) VALUES (8);
INSERT INTO PlaceParking(num) VALUES (9);
INSERT INTO PlaceParking(num) VALUES (10);

