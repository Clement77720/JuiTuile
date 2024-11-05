/**
 * La classe <code>Main</code> contient le point d'entrée du programme.
 * Elle initialise les éléments nécessaires, y compris une liste pour stocker les parties précédentes,
 * et affiche la fenêtre d'accueil du jeu.
 * @version 4.1
* @author Clémence Ducreux, Clément Jannaire, aurelien
 */
package src;

import java.util.ArrayList;
import java.util.List;

public class Main {
      /**
     * Méthode principale et point d'entrée du programme.
     * Initialise la liste des parties précédentes et lance la page d'accueil.
     *
     * @param args Arguments de la ligne de commande (non utilisés dans cette application).
     */
    public static void main(String[] args) {
        List<Partie> partiesPrecedentes = new ArrayList<>();
    // Création et affichage de la page d'accueil
        PageAccueil pageAccueil = new PageAccueil();
        pageAccueil.setVisible(true);
    }
}