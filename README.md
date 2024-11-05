# Projet Paysage

Bonjour Monsieur Hernandez,

Voici notre projet **"Paysage"**. Vous trouverez ici plusieurs répertoires :

- **src** : qui contient le code source de notre projet.
- **bin** : qui contient lui-même un répertoire **src**, qui contiendra les fichiers `.class`.
- **lib** : qui contient `mariadb-java-client-3.1.2.jar`.
- **Annexes** : où l'on trouve différents fichiers qui nous ont permis de réaliser ce projet, ainsi que la structure de la base de données.
- **Makefile** : qui permet de compiler et d'exécuter notre projet, et d'en faire une archive JAR.

Vous trouverez aussi notre rapport de ce projet.

## Commandes pour le Makefile

Voici les commandes que vous pouvez utiliser avec le `Makefile` :

- **`make`** : Compile tous les fichiers sources et crée les fichiers `.class` nécessaires dans le répertoire `bin/src`.
  
- **`make jar`** : Compile les fichiers (si nécessaire) et crée l'archive JAR nommée `SAE31-2024.jar`, contenant tous les fichiers `.class` compilés.

- **`make run`** : Exécute le programme en lançant la classe principale `src.Main`.

- **`make clean`** : Supprime tous les fichiers `.class` du répertoire `bin/src` ainsi que l'archive JAR `SAE31-2024.jar`, permettant de repartir d'une base propre.
