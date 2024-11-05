/**
 * La classe <code>Clé</code> contient les informations de connexion à la base de données.
 * Elle définit les constantes nécessaires pour l'URL, l'identifiant et le mot de passe.
 *
 * @version 4.1
 * @author Clémence Ducreux, Clément Jannaire, aurelien
 */
package src;

public class Clé {

    /**
     * Lien de connexion JDBC pour la base de données.
     * Utilisé pour spécifier l'URL de la base de données à laquelle se connecter.
     */
    public static final String LIEN = "jdbc:mariadb://dwarves.iut-fbleau.fr/amary";
     /**
     * Identifiant pour la connexion à la base de données.
     */
    public static final String ID = "amary";
    /**
     * Mot de passe pour l'authentification de la base de données.
     */
    public static final String MP = "sudo2BD";
     /**
     * Constructeur par défaut de la classe <code>Clé</code>.
     * Initialise les constantes de connexion, bien qu'elles soient déjà définies en tant que constantes statiques.
     */
 
    public Clé() {
        // Constructeur vide car les valeurs sont statiques et finales.
    }
}