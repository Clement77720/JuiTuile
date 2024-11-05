/**
 * La classe <code>GamePanel</code> représente le panneau de jeu où les tuiles sont placées.
 * Elle gère l'affichage des tuiles, le déplacement de la caméra et un timer affiché en haut à gauche.
 *
 * @version 4.1
 * @author Clémence Ducreux, Clément Jannaire, aurelien
 */
package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import src.Partie;
import src.Tuile;

/**
 * La classe <code>GamePanel</code> représente le panneau de jeu et gère l'affichage graphique
 * ainsi que les interactions de l'utilisateur, incluant le déplacement de la caméra et la gestion des événements
 * de souris. Cette classe utilise des écouteurs pour les interactions de souris et met en place un timer 
 * pour suivre le temps écoulé dans le jeu.
 *
 * @version 4.1
 */

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
      /**
     * Instance de la classe <code>Partie</code> qui gère les informations de la partie en cours.
     */
    private Partie partie;

    /**
     * Tuile actuellement sélectionnée par l'utilisateur pour le placement.
     */
    private Tuile tuileEnCours;
      /**
     * Taille d'une tuile (en pixels) utilisée pour le calcul de l'affichage.
     */
    private static final int TILE_SIZE = 40;
   /**
     * Indicateur pour savoir si l'utilisateur est en train de glisser (drag) la vue.
     */
    private boolean dragging = false;
      /**
     * Dernière position de la souris, utilisée pour suivre le déplacement de la caméra lors du glissement.
     */
    private Point lastMousePosition;
      /**
     * Décalage en X de la caméra pour le déplacement du plateau.
     */
    private int cameraOffsetX = 0;
      /**
     * Décalage en Y de la caméra pour le déplacement du plateau.
     */
    private int cameraOffsetY = 0;

    private Timer timer;
       /**
     * Timer pour gérer le temps écoulé dans le jeu.
     */
    private long startTime;
     /**
     * Temps au moment de la pause, utilisé pour gérer le temps total écoulé.
     */
    private long pausedTime;
       /**
     * Orientation de la tuile actuelle, en degrés.
     */
    private int orientation = 0;
       /**
     * Indicateur pour savoir si le jeu est en pause.
     */
    private boolean isPaused = false;
    /**
     * Bouton de pause permettant à l'utilisateur de mettre le jeu en pause ou de le reprendre.
     */

    private JButton pauseButton;
    private JFrame pageAccueil;
    /**
     * Constructeur de la classe <code>GamePanel</code> qui initialise le panneau de jeu avec une partie donnée.
     *
     * @param partie L'objet <code>Partie</code> contenant les informations de la partie en cours.
     */

     public GamePanel(Partie partie, JFrame pageAccueil) {
        this.partie = partie;
        this.pageAccueil = pageAccueil; // Initialisation de pageAccueil


        // Configuration de la première tuile
        Tuile premiereTuile = partie.getProchaineTuile();
        if (premiereTuile != null) {
            premiereTuile.setY((byte) 4);
            premiereTuile.setR((byte) 3);
            partie.ajouterTuile(premiereTuile);
            tuileEnCours = partie.getProchaineTuile();
        }

        // Ajout des écouteurs de souris
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        // Configuration du bouton de pause
        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> togglePause());
        setLayout(new BorderLayout());
        
        // Ajoute le bouton dans le coin inférieur droit
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(pauseButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Timer pour rafraîchir l'affichage
        timer = new Timer(1000, e -> {
            if (!isPaused) repaint();
        });
        timer.start();
        startTime = System.currentTimeMillis();
    }

    // Méthode pour gérer la pause
    private void togglePause() {
        if (!isPaused) {
            isPaused = true;
            pausedTime = System.currentTimeMillis();
            timer.stop();

            String[] options = {"Reprendre", "Quitter", "Commandes"};
            int choice = JOptionPane.showOptionDialog(this, "Jeu en pause", "Pause", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0: // Reprendre
                    resumeGame();
                    break;
                case 1: // Quitter
                    quitterJeu();
                    break;
                case 2: // Commandes
                    afficherCommandes();
                    togglePause(); // Maintient le jeu en pause après avoir affiché les commandes
                    break;
                default:
                    break;
            }
        } else {
            resumeGame();
        }
    }
/**
 * Reprend le jeu après une pause en réinitialisant le temps de départ et en redémarrant le timer.
 * Le temps écoulé pendant la pause est soustrait pour assurer une continuité correcte du chronomètre.
 */
    private void resumeGame() {
        isPaused = false;
        startTime += System.currentTimeMillis() - pausedTime;
        timer.start();
    }
/**
 * Quitte le jeu et retourne à la page d'accueil. Cette méthode utilise un thread d'interface graphique
 * pour créer une nouvelle instance de <code>PageAccueil</code>, la rendre visible,
 * et fermer la fenêtre de jeu actuelle.
 */
public void quitterJeu() {
    SwingUtilities.invokeLater(() -> {
        pageAccueil.setVisible(true);
        JFrame frameJeu = (JFrame) SwingUtilities.getWindowAncestor(this);
        frameJeu.dispose();
    });
    }
/**
 * Affiche une boîte de dialogue avec les commandes du jeu.
 * Cette boîte de dialogue comprend les instructions pour poser une tuile, déplacer la vue,
 * faire pivoter une tuile, et des informations sur le chronomètre et le score.
 */
    private void afficherCommandes() {
        String commandes = "Commandes du Jeu :\n\n" +
                "- Clic gauche : Poser une tuile sur une case vide.\n" +
                "- Clic droit maintenu : Déplacer la vue.\n" +
                "- Molette de la souris : Faire pivoter la tuile à placer.\n" +
                "- Temps : Le jeu est chronométré automatiquement.\n" +
                "- Score : Le score dépend de votre performance.\n";

        JOptionPane.showMessageDialog(this, commandes, "Commandes du Jeu", JOptionPane.INFORMATION_MESSAGE);
    }
/**
 * Redéfinit la méthode <code>paintComponent</code> pour gérer le rendu graphique du panneau de jeu.
 * Affiche les tuiles posées, la tuile en cours de placement, le score et le temps écoulé.
 * Utilisée pour dessiner dynamiquement les éléments de jeu et l'interface.
 *
 * @param g Le contexte graphique pour dessiner les éléments du panneau.
 */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isPaused) {
            // Récupère et dessine chaque tuile posée sur le plateau
            List<Tuile> tuilesPosees = partie.getTuilesPosees();
            for (Tuile tuile : tuilesPosees) {
                int[] coords = tuile.axialToCartesian();// Conversion des coordonnées axiales en cartésiennes
                tuile.dessiner(g, coords[0] + cameraOffsetX, coords[1] + cameraOffsetY);
            }
            // Affiche la tuile en cours de placement si le pointeur de la souris est dans le panneau
            if (getMousePosition() != null && tuileEnCours != null) {
                int mouseX = getMousePosition().x - cameraOffsetX;
                int mouseY = getMousePosition().y - cameraOffsetY;
                int[] axialCoords = cartesianToAxial(mouseX, mouseY);// Conversion des coordonnées cartésiennes en axiales
             // Conversion inverse en coordonnées cartésiennes pour le placement de la tuile en cours
                int cartX = (int) (TILE_SIZE * Math.sqrt(3) * (axialCoords[1] + 0.5 * axialCoords[0]));
                int cartY = (int) (TILE_SIZE * 1.5 * axialCoords[0]);

                tuileEnCours.dessiner(g, cartX + cameraOffsetX, cartY + cameraOffsetY);
            }
        }

    // Calcule le temps écoulé en secondes
        long elapsedTime = isPaused ? (pausedTime - startTime) / 1000 : (System.currentTimeMillis() - startTime) / 1000;
        long minutes = elapsedTime / 60;
        long seconds = elapsedTime % 60;
    // Affiche le score et le temps écoulé en haut du panneau
        g.setColor(Color.BLACK);
        g.drawString("Score: " + partie.getScore().getValeur(), 10, 40);
        g.drawString(String.format("Temps écoulé: %d min %d s", minutes, seconds), 10, 20);
    // Affiche la prochaine tuile à jouer
        afficherProchaineTuile(g);
    }
/**
 * Gestionnaire d'événements pour les clics de souris. Lorsque le bouton gauche de la souris
 * est cliqué et que le jeu n'est pas en pause, cette méthode tente de poser une tuile
 * sur la case sélectionnée par l'utilisateur.
 *
 * @param e L'événement de clic de souris contenant les coordonnées de la souris.
 */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && !isPaused) {
            int mouseX = e.getX() - cameraOffsetX;
            int mouseY = e.getY() - cameraOffsetY;

            int[] axialCoords = cartesianToAxial(mouseX, mouseY);
            byte y = (byte) axialCoords[0];
            byte r = (byte) axialCoords[1];
  // Vérifie si la position est valide pour le placement d'une tuile
            if (estPositionValide(y, r)) {
                tuileEnCours.setY(y);
                tuileEnCours.setR(r);
                partie.ajouterTuile(tuileEnCours);// Ajoute la tuile actuelle à la partie
                tuileEnCours = partie.getProchaineTuile();// Charge la prochaine tuile
 // Affiche la fin de la partie si aucune tuile n'est disponible
                if (tuileEnCours == null) {
                    afficherFinDePartie();
                }

                repaint();// Redessine le panneau de jeu
            }
        }
    }
/**
 * Affiche un message de fin de partie à l'utilisateur, avec les options de rejouer ou
 * de revenir à l'accueil. Enregistre le score final et le temps total dans la base de données.
 * Si l'utilisateur choisit de rejouer, une nouvelle partie est lancée.
 */
    private void afficherFinDePartie() {
        long totalTime = (System.currentTimeMillis() - startTime) / 1000;
        int finalScore = partie.getScore().getValeur();
    // Enregistre le score final dans la base de données
        enregistrerScoreBD(partie.getSerie(), finalScore, (int) totalTime);

        String message = "Partie terminée!\nScore final : " + finalScore + "\nTemps total : " + totalTime + " secondes\n";
        String[] options = {"Rejouer", "Retour à l'accueil"};

          // Affiche les options de fin de partie dans une boîte de dialogue
        int choice = JOptionPane.showOptionDialog(this, message, "Fin de la Partie", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0: // Rejouer
                String serieChoisie = choisirSerie();
                if (serieChoisie != null) {
                    partie = new Partie(serieChoisie);
                    Tuile premiereTuile = partie.getProchaineTuile();
                      // Initialise la première tuile de la nouvelle partie
                    if (premiereTuile != null) {
                        premiereTuile.setY((byte) 0);
                        premiereTuile.setR((byte) 0);
                        partie.ajouterTuile(premiereTuile);
                        tuileEnCours = partie.getProchaineTuile();
                    }
                    startTime = System.currentTimeMillis();
                    repaint();
                }
                break;
            case 1: // Retour à l'accueil
                quitterJeu();
                break;
            default:
                break;
        }
    }
/**
 * Affiche une boîte de dialogue permettant à l'utilisateur de sélectionner une série de jeu parmi
 * les options disponibles. Les séries sont récupérées depuis la base de données.
 *
 * @return Le nom de la série sélectionnée par l'utilisateur ou <code>null</code> si aucune sélection n'est faite.
 */
    private String choisirSerie() {
        Serveur serveur = new Serveur();
        List<String> seriesDisponibles = serveur.getSeries();
        serveur.fermerServeur();

        String[] seriesOptions = seriesDisponibles.toArray(new String[0]);
        return (String) JOptionPane.showInputDialog(this, "Choisissez une série pour la nouvelle partie :",
                "Choisir une série", JOptionPane.QUESTION_MESSAGE, null, seriesOptions,
                seriesOptions.length > 0 ? seriesOptions[0] : null);
    }
/**
 * Enregistre le score final et le temps joué dans la base de données pour une série spécifique.
 *
 * @param serieNom Le nom de la série de jeu pour laquelle le score doit être enregistré.
 * @param score La valeur du score à enregistrer.
 * @param tempsJoue Le temps total joué en secondes.
 */
    private void enregistrerScoreBD(String serieNom, int score, int tempsJoue) {
        Serveur serveur = new Serveur();
        serveur.ajouterScore(serieNom, score, tempsJoue);
        serveur.fermerServeur();
    }
/**
 * Convertit des coordonnées cartésiennes en coordonnées axiales pour le placement des tuiles hexagonales.
 *
 * @param x La position X en coordonnées cartésiennes.
 * @param y La position Y en coordonnées cartésiennes.
 * @return Un tableau contenant les coordonnées axiales [y, r] pour la position spécifiée.
 */
    private int[] cartesianToAxial(int x, int y) {
        double q = (x * Math.sqrt(3) / 3 - y / 3.0) / TILE_SIZE;
        double r = (y * 2.0 / 3.0) / TILE_SIZE;

        int axialY = (int) Math.round(r);
        int axialR = (int) Math.round(q);
        return new int[]{axialY, axialR};
    }
/**
 * Vérifie si une position spécifiée par des coordonnées axiales est valide pour le placement d'une tuile.
 * Une position est valide si elle est adjacente à une tuile posée et n'est pas déjà occupée.
 *
 * @param y La coordonnée Y de la position axiale à vérifier.
 * @param r La coordonnée R de la position axiale à vérifier.
 * @return <code>true</code> si la position est valide, sinon <code>false</code>.
 */
    private boolean estPositionValide(byte y, byte r) {
        List<Tuile> tuilesPosees = partie.getTuilesPosees();
        for (Tuile tuile : tuilesPosees) {
            if (tuile.getY() == y && tuile.getR() == r) {
                return false;
            }
        }
        if (tuilesPosees.isEmpty()) {
            return (y == 0 && r == 0);
        }
        for (Tuile tuile : tuilesPosees) {
            if (estAdjacente(tuile, y, r)) {
                return true;
            }
        }
        return false;
    }
/**
 * Vérifie si une position spécifiée par les coordonnées axiales (y, r) est adjacente à une tuile donnée.
 * Une tuile est considérée comme adjacente si elle se trouve dans l'une des six directions hexagonales autour de la tuile spécifiée.
 *
 * @param tuile La tuile de référence pour vérifier l'adjacence.
 * @param y La coordonnée Y de la position à vérifier.
 * @param r La coordonnée R de la position à vérifier.
 * @return <code>true</code> si la position est adjacente à la tuile, sinon <code>false</code>.
 */
    private boolean estAdjacente(Tuile tuile, byte y, byte r) {
        int[][] directions = {{1, 0}, {0, 1}, {-1, 1}, {-1, 0}, {0, -1}, {1, -1}};

        byte tuileY = tuile.getY();
        byte tuileR = tuile.getR();

        for (int[] dir : directions) {
            int adjY = tuileY + dir[0];
            int adjR = tuileR + dir[1];

            if (adjY == y && adjR == r) {
                return true;
            }
        }
        return false;
    }
/**
 * Affiche la prochaine tuile à jouer en haut à droite du panneau de jeu, en utilisant le contexte graphique fourni.
 * Cette tuile est affichée avec une étiquette "Prochaine tuile" et est dessinée dans une position fixe.
 *
 * @param g Le contexte graphique pour dessiner la prochaine tuile.
 */

    private void afficherProchaineTuile(Graphics g) {
        Tuile prochaineTuile = partie.peekProchaineTuile();
        if (prochaineTuile != null) {
            int offsetX = getWidth() - 125;
            int offsetY = 70;
            prochaineTuile.dessiner(g, offsetX, offsetY);

            g.setColor(Color.BLACK);
            g.drawString("Prochaine tuile", offsetX, offsetY - 45);
        }
    }
/**
 * Gère l'événement de pression de bouton de souris. Active le mode de glissement de la vue si le bouton droit de la souris est pressé.
 *
 * @param e L'événement de souris contenant les informations de position.
 */
    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            dragging = true;
            lastMousePosition = e.getPoint();
        }
    }
/**
 * Gère l'événement de relâchement de bouton de souris. Désactive le mode de glissement de la vue si le bouton droit de la souris est relâché.
 *
 * @param e L'événement de souris contenant les informations de position.
 */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            dragging = false;
        }
    }
/**
 * Gère le glissement de la souris lorsque le mode de glissement est activé.
 * Déplace la vue en fonction du mouvement de la souris et redessine le panneau de jeu.
 *
 * @param e L'événement de souris contenant les informations de position.
 */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging) {
            Point currentMousePosition = e.getPoint();
            int deltaX = currentMousePosition.x - lastMousePosition.x;
            int deltaY = currentMousePosition.y - lastMousePosition.y;

            cameraOffsetX += deltaX;
            cameraOffsetY += deltaY;
            lastMousePosition = currentMousePosition;
            repaint();
        }
    }
/**
 * Gère l'événement de rotation de la molette de la souris pour faire pivoter la tuile en cours de placement.
 * La rotation de la molette dans une direction fait pivoter la tuile de 60 degrés.
 *
 * @param e L'événement de molette de souris contenant les informations de rotation.
 */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            orientation = (orientation + 60) % 360;
        } else {
            orientation = (orientation - 60 + 360) % 360;
        }
        tuileEnCours.setOrientation(orientation);
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        repaint();
    }
         
}