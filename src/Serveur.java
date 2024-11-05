/**
 * La classe <code>Serveur</code> gère la connexion et les interactions avec la base de données.
 * Elle permet de récupérer des séries, des tuiles, et des scores, ainsi que d'enregistrer des scores.
 * @version 4.1
 * @author Clémence Ducreux, Clément Jannaire, aurelien
 */
package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Serveur {
      /**
     * Connexion à la base de données.
     */
    private Connection bd;
 /**
     * Constructeur qui initialise la connexion à la base de données.
     * Charge le driver JDBC et se connecte avec les informations fournies par la classe <code>Clé</code>.
     */
    public Serveur() {
      
        try {
            // Charger le driver JDBC
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Classe de BD absente");
            System.exit(1);
        }
        try {
            // Connexion à la base de données avec les informations de Clé
            this.bd = DriverManager.getConnection(Clé.LIEN, Clé.ID, Clé.MP);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
   /**
     * Récupère toutes les séries disponibles dans la base de données.
     *
     * @return Liste des noms des séries disponibles.
     */
    public List<String> getSeries() {
        List<String> series = new ArrayList<>();
        try {
            PreparedStatement requete = bd.prepareStatement("SELECT Nom FROM Séries");
            ResultSet resultSet = requete.executeQuery();
            while (resultSet.next()) {
                series.add(resultSet.getString("Nom"));
                //System.out.println("[DEBUG] Série trouvée : " + resultSet.getString("Nom"));
            }
            requete.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return series;
    }

      /**
     * Récupère les tuiles d'une série spécifique dans la base de données.
     *
     * @param serieNom Nom de la série pour laquelle récupérer les tuiles.
     * @return Liste des tuiles associées à la série.
     */
    public List<Tuile> getTuiles(String serieNom) {
        List<Tuile> tuiles = new ArrayList<>();
        try {
            PreparedStatement requeteSerie = bd.prepareStatement("SELECT NumSérie FROM Séries WHERE Nom = ?");
            requeteSerie.setString(1, serieNom);
            ResultSet resultSerie = requeteSerie.executeQuery();

            if (resultSerie.next()) {
                int numSerie = resultSerie.getInt("NumSérie");

                PreparedStatement requeteTuiles = bd.prepareStatement(
                    "SELECT NumTuile, CodeTuile, Orientation FROM Tuiles WHERE NumSérie = ?"
                );
                requeteTuiles.setInt(1, numSerie);
                ResultSet resultTuiles = requeteTuiles.executeQuery();

                while (resultTuiles.next()) {
                    int numTuile = resultTuiles.getInt("NumTuile");
                    String codeTuile = resultTuiles.getString("CodeTuile");
                    int orientation = resultTuiles.getInt("Orientation");

                    Tuile tuile = new Tuile((byte) 0, (byte) 0, orientation);
                    tuile.setCode(codeTuile);

                    PreparedStatement requeteTerrains = bd.prepareStatement(
                        "SELECT TerrainType, NombreTriangles FROM CompositionTuiles WHERE NumTuile = ?"
                    );
                    requeteTerrains.setInt(1, numTuile);
                    ResultSet resultTerrains = requeteTerrains.executeQuery();

                    while (resultTerrains.next()) {
                        String terrainType = resultTerrains.getString("TerrainType");
                        int nombreTriangles = resultTerrains.getInt("NombreTriangles");
                        tuile.ajouterTerrain(terrainType, nombreTriangles);
                    }
                    requeteTerrains.close();
                    tuiles.add(tuile);
                }
                requeteTuiles.close();
            }
            requeteSerie.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors du chargement des tuiles: " + e.getMessage());
        }
        return tuiles;
    }

     /**
     * Récupère les scores pour une série spécifique depuis la base de données.
     *
     * @param serieNom Nom de la série pour laquelle récupérer les scores.
     * @return Liste des scores pour la série.
     */
    public List<Integer> getScores(String serieNom) {
        List<Integer> scores = new ArrayList<>();
        try {
            PreparedStatement requeteSerie = bd.prepareStatement("SELECT NumSérie FROM Séries WHERE Nom = ?");
            requeteSerie.setString(1, serieNom);
            ResultSet resultSerie = requeteSerie.executeQuery();

            if (resultSerie.next()) {
                int numSerie = resultSerie.getInt("NumSérie");

                PreparedStatement requeteScores = bd.prepareStatement(
                    "SELECT Score FROM Score WHERE NumSérie = ? ORDER BY Score DESC"
                );
                requeteScores.setInt(1, numSerie);
                ResultSet resultScores = requeteScores.executeQuery();

                while (resultScores.next()) {
                    scores.add(resultScores.getInt("Score"));
                }
                requeteScores.close();
            }
            requeteSerie.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return scores;
    }

     /**
     * Enregistre un nouveau score pour une série dans la base de données.
     *
     * @param serieNom Nom de la série pour laquelle le score est enregistré.
     * @param score Valeur du score à enregistrer.
     * @param tempsJoue Temps joué en secondes.
     */
    public void ajouterScore(String serieNom, int score, int tempsJoue) {
        try {
            PreparedStatement requeteSerie = bd.prepareStatement("SELECT NumSérie FROM Séries WHERE Nom = ?");
            requeteSerie.setString(1, serieNom);
            ResultSet resultSerie = requeteSerie.executeQuery();

            if (resultSerie.next()) {
                int numSerie = resultSerie.getInt("NumSérie");

                PreparedStatement requeteAjoutScore = bd.prepareStatement(
                    "INSERT INTO Score (NumSérie, Score, TempsJoué) VALUES (?, ?, ?)"
                );
                requeteAjoutScore.setInt(1, numSerie);
                requeteAjoutScore.setInt(2, score);
                requeteAjoutScore.setInt(3, tempsJoue);
                requeteAjoutScore.executeUpdate();
                System.out.println("[INFO] Score ajouté pour la série " + serieNom + " : " + score);
                requeteAjoutScore.close();
            }
            requeteSerie.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du score : " + e.getMessage());
        }
    }

       /**
     * Ferme la connexion à la base de données.
     */
    public void fermerServeur() {
        try {
            bd.close();
            System.out.println("[INFO] Connexion fermée.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

      /**
     * Récupère les tuiles complètes pour une série, y compris leurs terrains, depuis la base de données.
     *
     * @param serieNom Nom de la série pour laquelle récupérer les tuiles.
     * @return Liste des objets Tuile associés à la série.
     */
    public List<Tuile> getTuilesAsObjects(String serieNom) {
        List<Tuile> tuiles = new ArrayList<>();
        try {
            PreparedStatement requeteSerie = bd.prepareStatement("SELECT NumSérie FROM Séries WHERE Nom = ?");
            requeteSerie.setString(1, serieNom);
            ResultSet resultSerie = requeteSerie.executeQuery();
            if (resultSerie.next()) {
                int numSerie = resultSerie.getInt("NumSérie");

                PreparedStatement requeteTuiles = bd.prepareStatement(
                    "SELECT NumTuile, CodeTuile, Orientation FROM Tuiles WHERE NumSérie = ?"
                );
                requeteTuiles.setInt(1, numSerie);
                ResultSet resultTuiles = requeteTuiles.executeQuery();
                while (resultTuiles.next()) {
                    int numTuile = resultTuiles.getInt("NumTuile");
                    String codeTuile = resultTuiles.getString("CodeTuile");
                    int orientation = resultTuiles.getInt("Orientation");

                    Tuile tuile = new Tuile((byte) 0, (byte) 0, orientation);
                    tuile.setCode(codeTuile);

                    PreparedStatement requeteTerrains = bd.prepareStatement(
                        "SELECT TerrainType, NombreTriangles FROM CompositionTuiles WHERE NumTuile = ?"
                    );
                    requeteTerrains.setInt(1, numTuile);
                    ResultSet resultTerrains = requeteTerrains.executeQuery();

                    while (resultTerrains.next()) {
                        String terrainType = resultTerrains.getString("TerrainType");
                        int nombreTriangles = resultTerrains.getInt("NombreTriangles");
                        tuile.ajouterTerrain(terrainType, nombreTriangles);
                    }
                    requeteTerrains.close();
                    tuiles.add(tuile);
                }
                requeteTuiles.close();
            }
            requeteSerie.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors du chargement des tuiles: " + e.getMessage());
        }
        return tuiles;
    }

    // Obtenir le classement
    public List<String> getClassement() {
        List<String> classement = new ArrayList<>();
        String query = "SELECT S.Nom AS Serie, Sc.Score, Sc.TempsJoue, Sc.DateJeu " +
                       "FROM Score Sc " +
                       "JOIN Séries S ON Sc.NumSérie = S.NumSérie " +
                       "ORDER BY S.Nom, Sc.Score DESC";
        try (PreparedStatement stmt = bd.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String serie = rs.getString("Serie");
                int score = rs.getInt("Score");
                int tempsJoue = rs.getInt("TempsJoue");
                Timestamp dateJeu = rs.getTimestamp("DateJeu");
                classement.add("Série : " + serie + " | Score : " + score +
                               " | Temps : " + tempsJoue + "s | Date : " + dateJeu);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du classement : " + e.getMessage());
        }
        return classement;
    }
}