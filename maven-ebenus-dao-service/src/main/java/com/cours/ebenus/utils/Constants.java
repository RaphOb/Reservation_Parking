package com.cours.ebenus.utils;

import java.nio.file.Paths;

public class Constants {

    // Url de connexion en base de donnée
    public static String DATABASE_URL = "jdbc:mysql://localhost:3306/base_voiture_ebenus?useSSL=false";
    // Utilisateur de la base de données
    public static String DATABASE_USER = "application";
    // Mot de passe de la base de données
    public static String DATABASE_PASSWORD = "passw0rd";

    // Drivers Jdbc
    public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static String SQL_JUNIT_PATH_FILE = "script_base_test_junit_quest_ebenus.sql";

    public static int EXCEPTION_CODE_USER_ALREADY_EXIST = -1;

    public static final String TOKENS_DIRECTORY_PATH = "com/cours/ebenus/credentials/";

    public static final String CALENDAR_ID = "etna-learning.fr_p6pljnfran3u2vb8ovp9unpedo@group.calendar.google.com";
//    public static java.io.File CREDENTIALS_FILE_PATH = new java.io.File(System.getProperty("user.dir") + "/src/main/java/com/cours/ebenus/credentials/keys.json");
    public static java.io.File CREDENTIALS_FILE_PATH = new java.io.File("C:/Users/oraph/Desktop/ETNA/MAST_1/JAVA/EBENUS/group-740465/maven-ebenus-dao-service/src/main/java/com/cours/ebenus/credentials/keys.json");
}
