# Klassen und Objekte

---

## Klassen als Baupläne
In der objektorientierten Programmierung sind Klassen Baupläne für Objekte.  
Eine Klasse definiert Attribute (Eigenschaften) und Methoden (Funktionen), die Objekte später besitzen.

## Attribute
Attribute beschreiben die Eigenschaften eines Objekts.  
Beispiel für ein Haus:
- Bewohner (FamilyName)
- Anzahl Zimmer
- Strasse und Hausnummer
- Wert des Hauses

## Konstruktor
Ein Konstruktor setzt Anfangswerte für die Attribute und erzeugt das eigentliche Objekt.  
Der Konstruktor hat immer den gleichen Namen wie die Klasse.

## Getter und Setter
- Attribute sind in der Regel `private` und somit geschützt.  
- Zugriff erfolgt über **Getter** (lesen) und **Setter** (schreiben).  
- Diese Methoden können in der IDE automatisch generiert werden.

## Objekte erzeugen
Mit `new` wird aus einer Klasse ein Objekt erstellt.  
Beispiel: `House houseSmith = new House();`  
Beachte:
- Klassennamen beginnen mit Grossbuchstaben (CamelCase).
- Objektnamen beginnen klein (ebenfalls CamelCase).
- Die Klasse wird in einer Datei mit gleichem Namen gespeichert (House.java).

## Zustände ändern
- Mit Settern können Attributswerte geändert werden.  
- Mit Gettern können Werte abgefragt und ausgegeben werden.  
Beispiel:
```java
String familyName = houseSmith.getFamilyName();
System.out.println(familyName);
System.out.println(houseSmith.getNumberOfRooms());
```

## Checkpoint
- Ich weiss wie man eine Klasse schreibt  
> Ja.

- Ich kann Objekte aus einer Klasse initialisieren  
> Ja.

- Ich kann den Zustand eines Objekts verändern  
> Ja.
