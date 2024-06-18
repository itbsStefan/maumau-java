# Mau-Mau

https://en.wikipedia.org/wiki/Mau-Mau_(card_game)

Schreibe eine Klasse `Player` im Package `de.nielshoppe.maumau` mit
* einer Eigenschaft `name`
* einem Konstruktor, der den Namen als Parameter hat und der Eigenschaft zuweist
* einer Methode `getName`, die den Namen zurück gibt.

Schreibe eine Klasse `Game` im Package `de.nielshoppe.maumau` mit
* einer Eigenschaft `players`, die ein Array von Spielern (`Player[]`) beinhaltet
* einer Eigenschaft `state`, die den Spielstands (`GameState`) beinhaltet
* einem Konstruktor, der ein Array aus Spielernamen (`String[]`) als Parameter hat und die Eigenschaften `players` und `state` enstrechend zuweist
* einer Methode `getPlayer`, die den Index eines Spielers als Parameter hat und den passenden Spieler zurück gibt
* einer Methode `getPlayers`, die alle Spieler zurück gibt
* einer Methode `getState`, die den Spielstand zurück gibt

Ergänze die Klasse `Card` mit
* zwei Eigenschaften `suit` und `rank`
* einem Konstruktor, der Farbe und Wert einer Karte als Parameter hat und den Eigenschaften zuweist
* einer Methode `toString`, die eine Karte in der Form `RANG/FARBE` repräsentiert
* einer Methode `getSuit`, die die Farbe zurück gibt
* einer Methode `getRank`, die den Wert zurück gibt
* einer Methode `equals`, die eine Karte mit einer anderen Karte anhand von Farbe und Wert vergleicht 
* einer statischen Methode `getDeck`, die einen Stapel Karten erzeugt und zurück gibt.

Ergänze die Klasse `GameController` mit
* einer Methode `getPlayerOnTurn`, die den Index des aktuellen Spielers zurück gibt
* einer Methode `getNextMustTake`, die die Anzahl Karten zurück gibt, die der nächste Spieler ziehen muss
* einer Methode `getHand`, die den Index eines Spielers als Parameter hat und dessen Hand zurück gibt
* einer Methode `isGameOver`, die ermittelt, ob wenigstens ein Spieler keine Karten mehr auf der Hand hat

Ergänze die Methode `setup` in der Klasse `GameController`, sodass sie
1. ein neues Kartendeck erzeugt und auf den Nachziehstapel legt,
2. an jeden Spieler fünf Karten austeilt (Hinweis: `dealCards`)
3. die oberste Karte vom Nachziehstapel auf den Ablagestapel verschiebt

Ergänze die Methode `handleMove` in der Klasse `GameController`,
sodass sie je nach Art des übergebenen Spielzuges die passende Methode aufruft.

**Note**: There is a bug, when a JAKE is played, followed by a SEVEN and then cards are drawn. Details missing.