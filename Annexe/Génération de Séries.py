#!/usr/bin/python3.11.8
# -*- coding: UTF-8 -*-
"""
Génération de Séries
"""

# ---------- Programme Principal ----------

from secrets import randbelow

TERRAIN = ('o','c','p','f','m')
NUMSÉRIE = 2

précédent = ''

print(f"INSERT INTO `Tuiles` (`NumSérie`, `NumTuile`, `CodeTuile`) VALUES ")
for t in range(1, 51):
	if(randbelow(10) < 3):  #Tuile simple : 30%
		print(f"('{NUMSÉRIE}', '{t}', '{TERRAIN[randbelow(5)]}'),")
	else:
		terrain2 = []
		for c in TERRAIN :
			terrain2.append(c)
		précédent = TERRAIN[randbelow(5)]
		terrain2.pop(terrain2.index(précédent))
		#print(terrain2)  #debug
		print(f"('{NUMSÉRIE}', '{t}', '{randbelow(3)+1}{précédent}{terrain2[randbelow(4)]}'),")


#('1', '1', '2op')