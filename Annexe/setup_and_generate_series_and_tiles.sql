
-- Création de la table Séries
CREATE TABLE IF NOT EXISTS Séries (
    NumSérie INT AUTO_INCREMENT PRIMARY KEY,
    Nom VARCHAR(100) NOT NULL
);

-- Création de la table Tuiles
CREATE TABLE IF NOT EXISTS Tuiles (
    NumTuile INT AUTO_INCREMENT PRIMARY KEY,
    CodeTuile VARCHAR(100) NOT NULL,
    NumSérie INT,
    Orientation INT DEFAULT 0,
    FOREIGN KEY (NumSérie) REFERENCES Séries(NumSérie) ON DELETE CASCADE
);

-- Création de la table CompositionTuiles
CREATE TABLE IF NOT EXISTS CompositionTuiles (
    NumTuile INT,
    TerrainType VARCHAR(50),
    NombreTriangles INT,
    PRIMARY KEY (NumTuile, TerrainType),
    FOREIGN KEY (NumTuile) REFERENCES Tuiles(NumTuile) ON DELETE CASCADE
);
--Création de la table Score
CREATE TABLE Score (
    IdScore INT PRIMARY KEY AUTO_INCREMENT,
    NumSérie INT,
    Score INT NOT NULL,
    TempsJoué INT NOT NULL,
    DateJeu TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX (NumSérie)
);

-- Création des 3 séries
INSERT INTO Séries (Nom) VALUES
('Série 1'),
('Série 2'),
('Série 3');




-- Ajout des scores de référence
INSERT INTO Score (NumSérie, Score, TempsJoué, DateJeu) VALUES
    (1, 250, 300, '2024-10-27 02:01:45'),
    (1, 320, 400, '2024-10-27 02:01:45'),
    (1, 290, 350, '2024-10-27 02:01:45'),
    (2, 180, 280, '2024-10-27 02:01:45'),
    (2, 210, 360, '2024-10-27 02:01:45'),
    (2, 330, 420, '2024-10-27 02:01:45'),
    (3, 500, 600, '2024-10-27 02:01:45'),
    (3, 420, 520, '2024-10-27 02:01:45'),
    (3, 390, 480, '2024-10-27 02:01:45');


-- Ajout de 50 tuiles pour la Série 1
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T1S1', 1, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T2S1', 1, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T3S1', 1, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T4S1', 1, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T5S1', 1, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T6S1', 1, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T7S1', 1, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T8S1', 1, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T9S1', 1, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T10S1', 1, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T11S1', 1, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T12S1', 1, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T13S1', 1, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T14S1', 1, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T15S1', 1, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T16S1', 1, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T17S1', 1, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T18S1', 1, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T19S1', 1, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T20S1', 1, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T21S1', 1, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T22S1', 1, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T23S1', 1, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T24S1', 1, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T25S1', 1, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T26S1', 1, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T27S1', 1, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T28S1', 1, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T29S1', 1, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T30S1', 1, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T31S1', 1, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T32S1', 1, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T33S1', 1, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T34S1', 1, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T35S1', 1, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T36S1', 1, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T37S1', 1, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T38S1', 1, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T39S1', 1, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T40S1', 1, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T41S1', 1, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T42S1', 1, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T43S1', 1, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T44S1', 1, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T45S1', 1, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T46S1', 1, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T47S1', 1, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T48S1', 1, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T49S1', 1, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T50S1', 1, 0);

-- Composition des tuiles pour Série 1
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (1, 'FORET', 2), (1, 'OCEAN', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (2, 'FORET', 4), (2, 'MONTAGNE', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (3, 'OCEAN', 5), (3, 'CHAMP', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (4, 'PRE', 3), (4, 'CHAMP', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (5, 'CHAMP', 1), (5, 'PRE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (6, 'PRE', 1), (6, 'CHAMP', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (7, 'CHAMP', 2), (7, 'PRE', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (8, 'CHAMP', 5), (8, 'FORET', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (9, 'MONTAGNE', 3), (9, 'CHAMP', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (10, 'FORET', 2), (10, 'OCEAN', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (11, 'OCEAN', 5), (11, 'FORET', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (12, 'OCEAN', 1), (12, 'CHAMP', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (13, 'CHAMP', 5), (13, 'MONTAGNE', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (14, 'FORET', 3), (14, 'MONTAGNE', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (15, 'OCEAN', 4), (15, 'FORET', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (16, 'MONTAGNE', 1), (16, 'OCEAN', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (17, 'FORET', 5), (17, 'MONTAGNE', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (18, 'OCEAN', 1), (18, 'MONTAGNE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (19, 'OCEAN', 2), (19, 'CHAMP', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (20, 'OCEAN', 5), (20, 'FORET', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (21, 'PRE', 2), (21, 'FORET', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (22, 'FORET', 1), (22, 'OCEAN', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (23, 'PRE', 2), (23, 'OCEAN', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (24, 'PRE', 3), (24, 'FORET', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (25, 'PRE', 4), (25, 'OCEAN', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (26, 'OCEAN', 5), (26, 'CHAMP', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (27, 'FORET', 3), (27, 'MONTAGNE', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (28, 'MONTAGNE', 2), (28, 'FORET', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (29, 'CHAMP', 5), (29, 'PRE', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (30, 'FORET', 3), (30, 'PRE', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (31, 'OCEAN', 4), (31, 'FORET', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (32, 'MONTAGNE', 4), (32, 'FORET', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (33, 'OCEAN', 1), (33, 'MONTAGNE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (34, 'PRE', 1), (34, 'CHAMP', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (35, 'CHAMP', 4), (35, 'FORET', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (36, 'CHAMP', 2), (36, 'FORET', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (37, 'OCEAN', 2), (37, 'CHAMP', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (38, 'OCEAN', 5), (38, 'FORET', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (39, 'OCEAN', 3), (39, 'PRE', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (40, 'CHAMP', 2), (40, 'OCEAN', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (41, 'MONTAGNE', 4), (41, 'CHAMP', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (42, 'MONTAGNE', 1), (42, 'CHAMP', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (43, 'MONTAGNE', 3), (43, 'FORET', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (44, 'PRE', 5), (44, 'CHAMP', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (45, 'PRE', 5), (45, 'CHAMP', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (46, 'CHAMP', 3), (46, 'PRE', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (47, 'OCEAN', 1), (47, 'MONTAGNE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (48, 'FORET', 5), (48, 'CHAMP', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (49, 'CHAMP', 4), (49, 'FORET', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (50, 'CHAMP', 1), (50, 'OCEAN', 5);

-- Ajout de 50 tuiles pour la Série 2
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T51S2', 2, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T52S2', 2, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T53S2', 2, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T54S2', 2, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T55S2', 2, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T56S2', 2, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T57S2', 2, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T58S2', 2, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T59S2', 2, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T60S2', 2, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T61S2', 2, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T62S2', 2, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T63S2', 2, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T64S2', 2, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T65S2', 2, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T66S2', 2, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T67S2', 2, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T68S2', 2, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T69S2', 2, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T70S2', 2, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T71S2', 2, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T72S2', 2, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T73S2', 2, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T74S2', 2, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T75S2', 2, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T76S2', 2, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T77S2', 2, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T78S2', 2, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T79S2', 2, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T80S2', 2, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T81S2', 2, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T82S2', 2, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T83S2', 2, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T84S2', 2, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T85S2', 2, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T86S2', 2, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T87S2', 2, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T88S2', 2, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T89S2', 2, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T90S2', 2, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T91S2', 2, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T92S2', 2, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T93S2', 2, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T94S2', 2, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T95S2', 2, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T96S2', 2, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T97S2', 2, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T98S2', 2, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T99S2', 2, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T100S2', 2, 120);

-- Composition des tuiles pour Série 2
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (51, 'PRE', 2), (51, 'CHAMP', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (52, 'MONTAGNE', 1), (52, 'CHAMP', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (53, 'MONTAGNE', 5), (53, 'CHAMP', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (54, 'OCEAN', 1), (54, 'FORET', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (55, 'PRE', 1), (55, 'OCEAN', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (56, 'OCEAN', 3), (56, 'PRE', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (57, 'CHAMP', 1), (57, 'MONTAGNE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (58, 'CHAMP', 1), (58, 'PRE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (59, 'FORET', 5), (59, 'PRE', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (60, 'FORET', 5), (60, 'MONTAGNE', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (61, 'MONTAGNE', 3), (61, 'FORET', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (62, 'OCEAN', 5), (62, 'CHAMP', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (63, 'OCEAN', 2), (63, 'CHAMP', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (64, 'PRE', 4), (64, 'MONTAGNE', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (65, 'CHAMP', 5), (65, 'OCEAN', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (66, 'PRE', 3), (66, 'CHAMP', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (67, 'FORET', 3), (67, 'MONTAGNE', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (68, 'MONTAGNE', 2), (68, 'CHAMP', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (69, 'MONTAGNE', 1), (69, 'PRE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (70, 'OCEAN', 1), (70, 'PRE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (71, 'MONTAGNE', 2), (71, 'CHAMP', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (72, 'CHAMP', 2), (72, 'FORET', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (73, 'OCEAN', 5), (73, 'CHAMP', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (74, 'MONTAGNE', 3), (74, 'OCEAN', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (75, 'PRE', 3), (75, 'MONTAGNE', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (76, 'FORET', 4), (76, 'CHAMP', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (77, 'MONTAGNE', 3), (77, 'CHAMP', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (78, 'CHAMP', 4), (78, 'PRE', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (79, 'OCEAN', 4), (79, 'FORET', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (80, 'PRE', 5), (80, 'MONTAGNE', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (81, 'PRE', 2), (81, 'OCEAN', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (82, 'MONTAGNE', 4), (82, 'PRE', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (83, 'FORET', 3), (83, 'PRE', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (84, 'OCEAN', 1), (84, 'PRE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (85, 'OCEAN', 1), (85, 'PRE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (86, 'PRE', 2), (86, 'CHAMP', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (87, 'FORET', 4), (87, 'PRE', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (88, 'PRE', 2), (88, 'MONTAGNE', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (89, 'MONTAGNE', 1), (89, 'PRE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (90, 'MONTAGNE', 3), (90, 'FORET', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (91, 'OCEAN', 3), (91, 'CHAMP', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (92, 'MONTAGNE', 3), (92, 'FORET', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (93, 'PRE', 3), (93, 'FORET', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (94, 'OCEAN', 2), (94, 'CHAMP', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (95, 'CHAMP', 4), (95, 'FORET', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (96, 'OCEAN', 3), (96, 'CHAMP', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (97, 'FORET', 3), (97, 'MONTAGNE', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (98, 'OCEAN', 5), (98, 'PRE', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (99, 'MONTAGNE', 4), (99, 'OCEAN', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (100, 'CHAMP', 1), (100, 'FORET', 5);

-- Ajout de 50 tuiles pour la Série 3
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T101S3', 3, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T102S3', 3, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T103S3', 3, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T104S3', 3, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T105S3', 3, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T106S3', 3, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T107S3', 3, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T108S3', 3, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T109S3', 3, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T110S3', 3, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T111S3', 3, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T112S3', 3, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T113S3', 3, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T114S3', 3, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T115S3', 3, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T116S3', 3, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T117S3', 3, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T118S3', 3, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T119S3', 3, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T120S3', 3, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T121S3', 3, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T122S3', 3, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T123S3', 3, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T124S3', 3, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T125S3', 3, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T126S3', 3, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T127S3', 3, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T128S3', 3, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T129S3', 3, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T130S3', 3, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T131S3', 3, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T132S3', 3, 60);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T133S3', 3, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T134S3', 3, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T135S3', 3, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T136S3', 3, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T137S3', 3, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T138S3', 3, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T139S3', 3, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T140S3', 3, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T141S3', 3, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T142S3', 3, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T143S3', 3, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T144S3', 3, 300);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T145S3', 3, 240);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T146S3', 3, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T147S3', 3, 180);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T148S3', 3, 0);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T149S3', 3, 120);
INSERT INTO Tuiles (CodeTuile, NumSérie, Orientation) VALUES ('T150S3', 3, 120);

-- Composition des tuiles pour Série 3
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (101, 'FORET', 1), (101, 'MONTAGNE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (102, 'FORET', 4), (102, 'MONTAGNE', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (103, 'MONTAGNE', 4), (103, 'PRE', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (104, 'FORET', 4), (104, 'OCEAN', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (105, 'PRE', 2), (105, 'MONTAGNE', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (106, 'OCEAN', 1), (106, 'PRE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (107, 'CHAMP', 3), (107, 'MONTAGNE', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (108, 'OCEAN', 1), (108, 'FORET', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (109, 'CHAMP', 5), (109, 'MONTAGNE', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (110, 'PRE', 1), (110, 'OCEAN', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (111, 'MONTAGNE', 1), (111, 'PRE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (112, 'MONTAGNE', 5), (112, 'CHAMP', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (113, 'CHAMP', 4), (113, 'MONTAGNE', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (114, 'PRE', 4), (114, 'OCEAN', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (115, 'PRE', 2), (115, 'FORET', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (116, 'PRE', 2), (116, 'MONTAGNE', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (117, 'OCEAN', 5), (117, 'MONTAGNE', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (118, 'PRE', 5), (118, 'FORET', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (119, 'PRE', 5), (119, 'MONTAGNE', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (120, 'CHAMP', 2), (120, 'MONTAGNE', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (121, 'OCEAN', 1), (121, 'MONTAGNE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (122, 'CHAMP', 5), (122, 'MONTAGNE', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (123, 'FORET', 2), (123, 'MONTAGNE', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (124, 'FORET', 4), (124, 'PRE', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (125, 'MONTAGNE', 2), (125, 'FORET', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (126, 'CHAMP', 5), (126, 'MONTAGNE', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (127, 'CHAMP', 3), (127, 'PRE', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (128, 'MONTAGNE', 5), (128, 'FORET', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (129, 'MONTAGNE', 5), (129, 'CHAMP', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (130, 'MONTAGNE', 3), (130, 'FORET', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (131, 'PRE', 4), (131, 'FORET', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (132, 'CHAMP', 5), (132, 'PRE', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (133, 'OCEAN', 1), (133, 'FORET', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (134, 'FORET', 5), (134, 'OCEAN', 1);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (135, 'OCEAN', 1), (135, 'MONTAGNE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (136, 'OCEAN', 4), (136, 'CHAMP', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (137, 'FORET', 3), (137, 'MONTAGNE', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (138, 'FORET', 4), (138, 'MONTAGNE', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (139, 'PRE', 1), (139, 'CHAMP', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (140, 'CHAMP', 1), (140, 'OCEAN', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (141, 'PRE', 4), (141, 'CHAMP', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (142, 'FORET', 1), (142, 'MONTAGNE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (143, 'MONTAGNE', 3), (143, 'OCEAN', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (144, 'FORET', 3), (144, 'OCEAN', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (145, 'FORET', 2), (145, 'PRE', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (146, 'MONTAGNE', 4), (146, 'FORET', 2);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (147, 'MONTAGNE', 3), (147, 'PRE', 3);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (148, 'OCEAN', 1), (148, 'PRE', 5);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (149, 'CHAMP', 2), (149, 'MONTAGNE', 4);
INSERT INTO CompositionTuiles (NumTuile, TerrainType, NombreTriangles) VALUES (150, 'FORET', 3), (150, 'PRE', 3);

