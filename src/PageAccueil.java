/**
 * La classe <code>PageAccueil</code> représente l'interface d'accueil.
 * Elle permet de démarrer une nouvelle partie, afficher le classement, et consulter les commandes.
 *
 * @version 4.1
 * @author Clémence Ducreux, Clément Jannaire, aurelien
 */
package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.stream.Collectors;
import src.GamePanel;

public class PageAccueil extends JFrame {
 /**
     * Liste des parties précédentes.
     */
    private List<Partie> partiesPrecedentes; 
      /**
     * Instance de la classe Serveur pour accéder aux données de la base.
     */
    private Serveur serveur; // Instance de la classe Serveur

        /**
     * Constructeur qui initialise la page d'accueil avec une liste donnée de parties précédentes.
     *
     * @param partiesPrecedentes Liste des parties précédentes.
     */
    public PageAccueil(List<Partie> partiesPrecedentes) {
        this.partiesPrecedentes = partiesPrecedentes;
        this.serveur = new Serveur();

        initUI(); // Initialise l'interface utilisateur
    }
   /**
     * Constructeur par défaut de <code>PageAccueil</code> qui initialise une liste vide pour les parties précédentes.
     */
    public PageAccueil() {
        this.partiesPrecedentes = List.of(); // Liste vide par défaut
        this.serveur = new Serveur();

        initUI(); // Initialise l'interface utilisateur
    }
  /**
     * Initialise l'interface utilisateur de la page d'accueil avec les boutons et les labels.
     */
    private void initUI() {
        setTitle("Paysage");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel labelBienvenue = new JLabel("Bienvenue", JLabel.CENTER);
        labelBienvenue.setFont(new Font("Arial", Font.BOLD, 20));
        labelBienvenue.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(labelBienvenue);

        JButton btnNouvellePartie = new JButton("Nouvelle Partie");
        btnNouvellePartie.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNouvellePartie.addActionListener(e -> choisirSerie());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(btnNouvellePartie);

        JButton btnClassement = new JButton("Classement");
        btnClassement.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnClassement.addActionListener(e -> afficherScoresTemps());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(btnClassement);

        JButton btnCommandes = new JButton("Commandes du Jeu");
        btnCommandes.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCommandes.addActionListener(e -> afficherCommandes());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(btnCommandes);

        add(mainPanel);
    }
 /**
     * Affiche une boîte de dialogue permettant à l'utilisateur de choisir une série pour lancer une nouvelle partie.
     */
    private void choisirSerie() {
        List<String> seriesDisponibles = serveur.getSeries();
        String[] seriesOptions = seriesDisponibles.toArray(new String[0]);

        String serieChoisie = (String) JOptionPane.showInputDialog(
            this,
            "Choisissez une série pour la nouvelle partie :",
            "Choisir une série",
            JOptionPane.QUESTION_MESSAGE,
            null,
            seriesOptions,
            seriesOptions[0]
        );

        if (serieChoisie != null) {
            lancerNouvellePartie(serieChoisie);
        }
    }
    /**
     * Lance une nouvelle partie avec la série spécifiée et ouvre une nouvelle fenêtre de jeu.
     * La fenêtre de jeu est fermée après la sauvegarde du score du joueur et du temps joué dans la base de données.
     *
     * @param serie La série sélectionnée pour la nouvelle partie.
     */
    private void lancerNouvellePartie(String serie) {
        Partie nouvellePartie = new Partie(serie);
        GamePanel gamePanel = new GamePanel(nouvellePartie, this); // Passe `this` pour JFrame
    
        JFrame frameJeu = new JFrame("Paysage - " + serie);
        frameJeu.setSize(800, 600);
        frameJeu.add(gamePanel);
        frameJeu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameJeu.setLocationRelativeTo(null);
        frameJeu.setVisible(true);
    
        frameJeu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int joueurScore = nouvellePartie.getScore().getValeur();
                int tempsJoue = (int) (System.currentTimeMillis() - nouvellePartie.getTempsEcoule()) / 1000;
    
                serveur.ajouterScore(serie, joueurScore, tempsJoue);
                serveur.fermerServeur();
            }
        });
    
        dispose();
    }
    /**
     * Affiche les séries disponibles sous forme de boutons et permet de consulter le classement pour une série spécifique.
     */
    private void afficherScoresTemps() {
        // Création d'un panel pour les boutons de sélection de série
        JPanel seriesPanel = new JPanel();
        seriesPanel.setLayout(new FlowLayout()); // Layout horizontal

        // Récupérer les séries disponibles
        List<String> seriesDisponibles = serveur.getSeries();
        for (String serie : seriesDisponibles) {
            JButton serieButton = new JButton(serie);
            serieButton.addActionListener(e -> afficherClassement(serie)); // Action pour afficher le classement de la série
            seriesPanel.add(serieButton); // Ajouter le bouton au panel
        }

        // Afficher les boutons pour choisir la série
        JOptionPane.showMessageDialog(this, seriesPanel, "Choisissez une série", JOptionPane.PLAIN_MESSAGE);
    }
  /**
     * Affiche le classement des scores pour une série spécifique, limitée aux dix meilleurs scores.
     *
     * @param serie La série pour laquelle le classement des scores doit être affiché.
     */
    private void afficherClassement(String serie) {
        List<Integer> scores = serveur.getScores(serie);
        StringBuilder message = new StringBuilder("Classement pour la série : " + serie + "\n\n");

        if (scores.isEmpty()) {
            message.append("Aucun score enregistré pour cette série.");
        } else {
            for (int i = 0; i < 10; i++) {
                if (i < scores.size()) {
                    // Affiche le score existant avec le numéro de classement
                    message.append((i + 1)).append(". Score : ").append(scores.get(i)).append("\n");
                } else {
                    // Affiche "Score non disponible" pour les places vides
                    message.append((i + 1)).append(". Score : \n");
                }
            }
        }

        JOptionPane.showMessageDialog(this, message.toString(), "Classement", JOptionPane.INFORMATION_MESSAGE);
    }
  /**
     * Affiche les commandes du jeu dans une boîte de dialogue, incluant les actions de la souris
     * et la légende des couleurs des terrains.
     */
    private void afficherCommandes() {
        String commandes = "Commandes du Jeu :\n\n" +
                "- Clic gauche : Poser une tuile sur une case vide.\n" +
                "- Clic droit maintenu : Déplacer la vue.\n" +
                "- Molette de la souris : Faire pivoter la tuile à placer.\n" +
                "- Temps : Le jeu est chronométré automatiquement.\n" +
                "- Score : Le score dépend de votre performance.\n\n" +
                "Légende des Couleurs :\n" +
                "- Champ : Jaune\n" +
                "- Mer : Bleu\n" +
                "- Pré : Vert clair\n" +
                "- Forêt : Vert foncé\n" +
                "- Montagne : Gris\n";

        JOptionPane.showMessageDialog(this, commandes, "Commandes du Jeu", JOptionPane.INFORMATION_MESSAGE);
    }
   /**
     * Point d'entrée alternatif pour lancer directement la page d'accueil.
     * 
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PageAccueil(List.of()));
    }
}