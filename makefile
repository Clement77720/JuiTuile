# Variables
JAVAC = javac
JAVA = java
JAR = jar
SRC = src
BIN = bin/src
LIB = lib/mariadb-java-client-3.1.2.jar
JAR_NAME = SAE31-2024.jar

# Liste des fichiers Java à compiler
SOURCES := $(wildcard $(SRC)/*.java)

# Création des fichiers .class dans le répertoire bin/src
CLASSES := $(SOURCES:$(SRC)/%.java=$(BIN)/%.class)

# Dépendances explicites
$(BIN)/PageAccueil.class: $(BIN)/Partie.class $(BIN)/Serveur.class $(BIN)/GamePanel.class
$(BIN)/GamePanel.class: $(BIN)/Partie.class $(BIN)/Serveur.class $(BIN)/Tuile.class
$(BIN)/Main.class: $(BIN)/GamePanel.class $(BIN)/PageAccueil.class
$(BIN)/Serveur.class: $(BIN)/Clé.class $(BIN)/Tuile.class $(BIN)/Terrain.class
$(BIN)/Partie.class: $(BIN)/Score.class $(BIN)/Serveur.class $(BIN)/Tuile.class $(BIN)/Terrain.class
$(BIN)/Tuile.class: $(BIN)/Terrain.class

# La règle par défaut
all: clean $(CLASSES)

# Compilation des fichiers .java
$(BIN)/%.class: $(SRC)/%.java
	@mkdir -p $(BIN) # Crée le dossier bin/src s'il n'existe pas
	$(JAVAC) -d bin -cp "$(LIB):$(SRC):bin" $<

# Création de l'archive JAR
jar: all
	echo "Main-Class: src.Main" > manifest.txt
	$(JAR) cfm $(JAR_NAME) manifest.txt -C $(BIN) .
	rm manifest.txt # Supprime le fichier manifest temporaire

# Pour exécuter la classe Main
run: all
	$(JAVA) -cp "$(LIB):bin" src.Main

# Nettoyage des fichiers compilés
clean:
	rm -rf $(BIN)/*.class
	rm -f $(JAR_NAME) # Supprime l'archive JAR
