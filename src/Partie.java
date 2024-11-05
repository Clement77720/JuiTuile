/**
 * La classe <code>Partie</code> gère la logique d'une partie de jeu, incluant les tuiles posées, 
 * le score, et le temps écoulé. Elle prend en charge le calcul du score en fonction des connexions 
 * de tuiles et permet de suivre les tuiles disponibles pour le placement.
 * @version 4.1
 * @author Clémence Ducreux, Clément Jannaire, aurelien
 */
package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Partie {
    private String serie; // Attribut pour stocker la série
    private List<Tuile> tuilesPosees; // Liste des tuiles posées
    private List<Tuile> tuilesDisponibles; // Liste des tuiles disponibles à placer
    private Tuile prochaineTuile; // La prochaine tuile à afficher
    private Score score; // Un score pour la partie
    private long tempsEcoule; // Temps écoulé en millisecondes
    private int nombreTuilesPosees; // Compteur de tuiles posées

     /**
     * Constructeur qui initialise une nouvelle partie pour la série spécifiée.
     * Charge les tuiles disponibles à partir de la base de données.
     *
     * @param serie Le nom de la série pour cette partie.
     */
    public Partie(String serie) {
        this.serie = serie;
        this.tuilesPosees = new ArrayList<>();
        this.tuilesDisponibles = new ArrayList<>(); // Initialisation de la liste des tuiles disponibles
        this.score = new Score(0); // Initialiser le score à 0
        this.nombreTuilesPosees = 0;
        this.tempsEcoule = 0; // Initialiser tempsEcoule à 0

        // Charger les tuiles via le Serveur
        Serveur serveur = new Serveur();
        this.tuilesDisponibles = serveur.getTuilesAsObjects(serie);
        serveur.fermerServeur();

        // Initialiser la première tuile si elle existe dans la liste
        if (!tuilesDisponibles.isEmpty()) {
            this.prochaineTuile = tuilesDisponibles.remove(0);
        } else {
            this.prochaineTuile = null;
        }
    }

    /**
     * Retourne la prochaine tuile sans la retirer de la liste.
     *
     * @return La prochaine tuile ou <code>null</code> si aucune tuile n'est disponible.
     */
    public Tuile peekProchaineTuile() {
        if (!tuilesDisponibles.isEmpty()) {
            return tuilesDisponibles.get(0); // Renvoie la tuile suivante sans la retirer
        }
        return null;
    }

   /**
     * Constructeur par défaut qui initialise une nouvelle partie avec une série par défaut.
     */
    public Partie() {
        this("DefaultSeries");
    }

      /**
     * Retourne et retire la prochaine tuile de la liste des tuiles disponibles.
     *
     * @return La prochaine tuile ou <code>null</code> si aucune tuile n'est disponible.
     */
    public Tuile getProchaineTuile() {
        if (!tuilesDisponibles.isEmpty()) {
            prochaineTuile = tuilesDisponibles.remove(0);
            return prochaineTuile;
        } else {
            return null; // Pas de prochaine tuile si la liste est vide
        }
    }

       /**
     * Définit la prochaine tuile à afficher.
     *
     * @param tuile La tuile à définir comme prochaine tuile.
     */
    public void setProchaineTuile(Tuile tuile) {
        this.prochaineTuile = tuile;
    }

     /**
     * Ajoute une tuile à la liste des tuiles posées, met à jour le compteur de tuiles
     * et recalcule le score de la partie.
     *
     * @param tuile La tuile à ajouter.
     */
    public void ajouterTuile(Tuile tuile) {
        tuilesPosees.add(tuile);
        nombreTuilesPosees++;
        calculerScore();  // Recalculer le score chaque fois qu'une tuile est ajoutée
    }

       /**
     * Calcule le score basé sur les poches de terrains connectées. Le score augmente en fonction
     * de la taille des poches de tuiles de même type de terrain.
     */
    private void calculerScore() {
        Set<Tuile> tuilesVisitees = new HashSet<>();
        int nouveauScore = -1;
        System.out.println("[DEBUG] Calcul du score...");

        for (Tuile tuile : tuilesPosees) {
            if (!tuilesVisitees.contains(tuile)) {
                List<Tuile> poche = getPocheDeTuile(tuile);
                int taillePoche = poche.size();
                //System.out.println("[DEBUG] Poche trouvée avec " + taillePoche + " tuiles.");

                if (taillePoche > 0) {
                    nouveauScore += taillePoche * taillePoche;
                    tuilesVisitees.addAll(poche);
                }
            }
        }
        //System.out.println("[DEBUG] Score calculé : " + nouveauScore);
        score.setValeur(nouveauScore);
    }

      /**
     * Retourne la liste des tuiles connectées formant une poche de terrain avec la tuile donnée.
     *
     * @param tuile La tuile de départ pour trouver la poche.
     * @return Liste des tuiles formant une poche de même type de terrain.
     */
    private List<Tuile> getPocheDeTuile(Tuile tuile) {
        List<Tuile> poche = new ArrayList<>();
        Set<Tuile> visitees = new HashSet<>();
        explorePoche(tuile, poche, visitees); // Explore et ajoute les tuiles connectées
        return poche;
    }

  /**
     * Méthode récursive pour explorer et ajouter toutes les tuiles connectées de même type de terrain.
     *
     * @param tuile La tuile de départ pour l'exploration.
     * @param poche Liste pour stocker les tuiles connectées.
     * @param visitees Ensemble des tuiles déjà visitées pour éviter les boucles.
     */
    private void explorePoche(Tuile tuile, List<Tuile> poche, Set<Tuile> visitees) {
        if (visitees.contains(tuile)) return;

        visitees.add(tuile);
        poche.add(tuile);

        for (Tuile adjacente : getTuilesAdjacentes(tuile)) {
            if (!visitees.contains(adjacente) && sontTerrainsCompatibles(tuile, adjacente)) {
                explorePoche(adjacente, poche, visitees);
            }
        }
    }

  /**
     * Retourne la liste des tuiles adjacentes à une tuile donnée.
     *
     * @param tuile La tuile pour laquelle on souhaite obtenir les adjacentes.
     * @return Liste des tuiles adjacentes.
     */
    private List<Tuile> getTuilesAdjacentes(Tuile tuile) {
        List<Tuile> adjacentes = new ArrayList<>();
        byte y = tuile.getY();
        byte r = tuile.getR();
        int[][] directions = {{1, 0}, {0, 1}, {-1, 1}, {-1, 0}, {0, -1}, {1, -1}};

        for (int[] direction : directions) {
            byte adjY = (byte) (y + direction[0]);
            byte adjR = (byte) (r + direction[1]);
            for (Tuile autreTuile : tuilesPosees) {
                if (autreTuile.getY() == adjY && autreTuile.getR() == adjR) {
                    adjacentes.add(autreTuile);
                }
            }
        }
        return adjacentes;
    }

      /**
     * Vérifie si deux tuiles ont des terrains compatibles pour former une poche de terrain connectée.
     *
     * @param tuile1 Première tuile à comparer.
     * @param tuile2 Deuxième tuile à comparer.
     * @return <code>true</code> si les terrains sont compatibles, sinon <code>false</code>.
     */
    private boolean sontTerrainsCompatibles(Tuile tuile1, Tuile tuile2) {
        for (Tuile.TerrainInfo terrain1 : tuile1.getTerrains()) {
            for (Tuile.TerrainInfo terrain2 : tuile2.getTerrains()) {
                if (terrain1.getType() == terrain2.getType()) {
                    return true;
                }
            }
        }
        return false;
    }
   /**
     * Retourne la liste des tuiles posées sur le plateau.
     *
     * @return Liste des tuiles posées.
     */
    public List<Tuile> getTuilesPosees() {
        return tuilesPosees;
    }
  /**
     * Retourne le nom de la série associée à la partie.
     *
     * @return Nom de la série.
     */
    public String getSerie() {
        return serie;
    }
 /**
     * Retourne l'objet Score de la partie.
     *
     * @return Score de la partie.
     */
    public Score getScore() {
        return score;
    }
/**
     * Retourne le temps écoulé en millisecondes.
     *
     * @return Temps écoulé en millisecondes.
     */

    public long getTempsEcoule() {
        return tempsEcoule;
    }
    /* Définit le score de la partie.*/
    public void setScore(Score score) {
        this.score = score;
    }
}