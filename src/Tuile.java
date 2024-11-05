/**
 * La classe <code>Tuile</code> représente une tuile avec ses terrains, son orientation et ses coordonnées.
 * Chaque tuile possède une liste de terrains et peut être affichée graphiquement.
 * @version 4.1
 * @author Clémence Ducreux, Clément Jannaire, aurelien
 */
package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import src.Terrain;

public class Tuile {
      /**
     * Taille de la tuile en pixels (hexagone).
     */
    public static final int TILE_SIZE = 40; // Taille de la tuile (hexagone)

    private byte y; // Coordonnée Y de la tuile en position axiale
    private byte r; // Coordonnée R de la tuile en position axiale
    private int orientation; // Orientation de la tuile (en degrés)
    private List<TerrainInfo> terrains; // Liste des terrains présents sur la tuile
    private String code; // Code unique de la tuile

    // Classe interne pour stocker les informations des terrains
    public static class TerrainInfo {
        private Terrain type; // Type de terrain (enum)
        private int nombreTriangles; // Nombre de triangles de ce terrain

        public TerrainInfo(Terrain type, int nombreTriangles) {
            this.type = type;
            this.nombreTriangles = nombreTriangles;
        }

        public Terrain getType() {
            return type;
        }

        public int getNombreTriangles() {
            return nombreTriangles;
        }

        @Override
        public String toString() {
            return "Terrain{" + "type=" + type + ", triangles=" + nombreTriangles + "}";
        }
    }

     /**
     * Constructeur de la classe <code>Tuile</code>.
     *
     * @param y Coordonnée Y de la tuile.
     * @param r Coordonnée R de la tuile.
     * @param orientation Orientation de la tuile en degrés.
     */
    public Tuile(byte y, byte r, int orientation) {
        this.y = y;
        this.r = r;
        this.orientation = orientation;
        this.terrains = new ArrayList<>();
        System.out.println("[DEBUG] Tuile créée avec coordonnées axiales y=" + y + ", r=" + r + ", orientation=" + orientation);
    }

       /**
     * Définit le code unique de la tuile.
     *
     * @param code Code unique de la tuile.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Retourne le code de la tuile.
     *
     * @return Code unique de la tuile.
     */
    public String getCode() {
        return code;
    }

     /**
     * Ajoute un terrain à la tuile.
     *
     * @param type Type de terrain sous forme de chaîne de caractères.
     * @param nombreTriangles Nombre de triangles de ce terrain.
     */
    public void ajouterTerrain(String type, int nombreTriangles) {
        try {
            Terrain terrainType = Terrain.valueOf(type.toUpperCase());
            TerrainInfo terrainInfo = new TerrainInfo(terrainType, nombreTriangles);
            terrains.add(terrainInfo);
            System.out.println("[DEBUG] Terrain ajouté : " + type + " avec " + nombreTriangles + " triangles.");
        } catch (IllegalArgumentException e) {
            System.err.println("[ERREUR] Type de terrain invalide : " + type);
        }
    }

 /**
     * Dessine la tuile hexagonale avec ses terrains.
     *
     * @param g Contexte graphique pour le dessin.
     * @param x Position X pour le rendu.
     * @param y Position Y pour le rendu.
     */
    public void dessiner(Graphics g, int x, int y) {
        System.out.println("[DEBUG] Dessin de la tuile (" + code + ") avec les terrains : " + this.terrains);

        int radius = TILE_SIZE; // Taille du rayon de l'hexagone
        int[] hexX = new int[6];
        int[] hexY = new int[6];

        // Calculer les points de l'hexagone
        for (int i = 0; i < 6; i++) {
            hexX[i] = (int) (x + radius * Math.cos(Math.toRadians(30 + orientation + 60 * i)));
            hexY[i] = (int) (y + radius * Math.sin(Math.toRadians(30 + orientation + 60 * i)));
        }

        int triangleCount = 0;
        for (TerrainInfo terrain : terrains) {
            Color terrainColor = getColorForTerrain(terrain.getType());

            for (int i = 0; i < terrain.getNombreTriangles() && triangleCount < 6; i++, triangleCount++) {
                g.setColor(terrainColor);

                // Dessiner le triangle
                int[] triX = {x, hexX[triangleCount], hexX[(triangleCount + 1) % 6]};
                int[] triY = {y, hexY[triangleCount], hexY[(triangleCount + 1) % 6]};
                g.fillPolygon(triX, triY, 3);

                // Log pour chaque triangle dessiné
                System.out.println("[DEBUG] Terrain dessiné : " + terrain.getType() + " avec triangle n°" + (triangleCount + 1));
            }
        }

        // Compléter les triangles restants avec une couleur par défaut si nécessaire
        while (triangleCount < 6) {
            g.setColor(terrains.size() > 0 ? getColorForTerrain(terrains.get(0).getType()) : Color.LIGHT_GRAY);
            int[] triX = {x, hexX[triangleCount], hexX[(triangleCount + 1) % 6]};
            int[] triY = {y, hexY[triangleCount], hexY[(triangleCount + 1) % 6]};
            g.fillPolygon(triX, triY, 3);
            triangleCount++;
        }

        // Dessiner le contour de l'hexagone
        g.setColor(Color.BLACK);
        g.drawPolygon(hexX, hexY, 6);
    }

    // Méthode pour associer une couleur en fonction du type de terrain
    private Color getColorForTerrain(Terrain type) {
        switch (type) {
            case OCEAN:
                return new Color(66, 135, 245); // Un bleu clair et vif
            case CHAMP:
                return new Color(252, 212, 64); // Un jaune lumineux et agréable
            case PRE:
                return new Color(144, 238, 144); // Un vert tendre et naturel
            case FORET:
                return new Color(34, 139, 34); // Un vert foncé et profond
            case MONTAGNE:
                return new Color(169, 169, 169); // Un gris doux mais bien visible
            default:
                return Color.BLACK; // Couleur par défaut si le terrain est inconnu
        }
    }

     /**
     * Convertit les coordonnées axiales de la tuile en coordonnées cartésiennes.
     *
     * @return Tableau contenant les coordonnées cartésiennes [x, y].
     */
    public int[] axialToCartesian() {
        // Ajustement de l'alignement en hexagones en utilisant TILE_SIZE pour un rendu hexagonal
        double x = TILE_SIZE * Math.sqrt(3) * (r + y / 2.0);  // Décalage en x
        double yCoord = TILE_SIZE * 1.5 * y;                   // Décalage en y
        return new int[]{(int) Math.round(x), (int) Math.round(yCoord)};
    }
  /**
     * Retourne l'orientation actuelle de la tuile.
     *
     * @return Orientation en degrés.
     */
    public int getOrientation() {
        return orientation;
    }
/**
     * Définit une nouvelle orientation pour la tuile.
     *
     * @param orientation Nouvelle orientation en degrés.*/

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
 /**
     * Retourne la coordonnée Y de la tuile.
     *
     * @return Coordonnée Y en position axiale.
     */
    public byte getY() {
        return y;
    }
  /**
     * Retourne la coordonnée R de la tuile.
     *
     * @return Coordonnée R en position axiale.
     */

    public byte getR() {
        return r;
    }
   /**
     * Définit la coordonnée Y de la tuile.
     *
     * @param y Nouvelle coordonnée Y.
     */

    public void setY(byte y) {
        this.y = y;
        System.out.println("[DEBUG] Coordonnée Y de la tuile définie à : " + y);
    }
  /**
     * Définit la coordonnée R de la tuile.
     *
     * @param r Nouvelle coordonnée R.
     */

    public void setR(byte r) {
        this.r = r;
        System.out.println("[DEBUG] Coordonnée R de la tuile définie à : " + r);
    }

    @Override
    public String toString() {
        return "Tuile{" +
                "y=" + y +
                ", r=" + r +
                ", orientation=" + orientation +
                ", terrains=" + terrains +
                ", code='" + code + '\'' +
                '}';
    }

   /**
     * Retourne la liste des terrains de la tuile.
     *
     * @return Liste des terrains présents sur la tuile.
     */
    public List<TerrainInfo> getTerrains() {
        return terrains;
    }
}