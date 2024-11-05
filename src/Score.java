/**
 * La classe <code>Score</code> représente le score d'une partie.
 * Elle contient une valeur de score qui peut être consultée et modifiée.
 * @version 4.1
 * @author Clémence Ducreux, Clément Jannaire, aurelien
 * Date : 10-10-24
 */
package src;

public class Score {
    private int valeur; // Valeur du score
/**
     * Constructeur qui initialise le score avec une valeur donnée.
     *
     * @param valeur La valeur initiale du score.
     */
    public Score(int valeur) {
        this.valeur = valeur;
    }
   /**
     * Retourne la valeur actuelle du score.
     *
     * @return La valeur du score.
     */
    public int getValeur() {
        return valeur;
    }
 /**
     * Définit une nouvelle valeur pour le score.
     *
     * @param valeur La nouvelle valeur du score.
     */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
}