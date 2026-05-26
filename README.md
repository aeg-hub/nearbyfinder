# NearbyFinder 🗺️

> Informatik-Leistungskurs · Albert-Einstein-Gymnasium Sankt Augustin  
> Thema: Algorithmen & Datenstrukturen – Binärer Suchbaum und k-d Tree

---

## Was ist NearbyFinder?

NearbyFinder ist ein vereinfachtes Geoinformationssystem: Orte (z. B. Städte)
werden nach ihren Koordinaten (Breiten- und Längengrad) verwaltet und durchsucht.

Im Laufe der Aufgabe entwickelst du die Datenstruktur schrittweise:

- **Aufgabe 1** – Binärer Suchbaum (BST): Orte nach Längengrad verwalten und durchsuchen
- **Aufgabe 2** – k-d Tree: Erweiterung auf zwei Dimensionen (lat + lon)
- **Aufgabe 3** – Reflexion, Komplexitätsanalyse und Transfer

---

## Projektstruktur

```
nearbyfinder/
├── src/
│   └── kdbaum/
│       ├── Location.java          # Datenklasse – vorgegeben, nicht verändern
│       ├── BSTNode.java           # BST-Knoten – vorgegeben, nicht verändern
│       ├── KDNode.java            # k-d-Tree-Knoten – vorgegeben, nicht verändern
│       ├── LocationBST.java       # Aufgabe 1 – hier implementierst du
│       ├── KDTree.java            # Aufgabe 2 – hier implementierst du
│       └── NearbyFinderMain.java  # Testklasse – kannst du frei anpassen
└── README.md
```

---

## Projekt in Eclipse einrichten

### Voraussetzungen

- [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/) installiert
- [Git](https://git-scm.com/downloads) installiert
- Einen Terminal geöffnet (Windows: Git Bash, Mac/Linux: Terminal)

### Schritt 1 – In den Eclipse-Workspace navigieren

Öffne ein Terminal und navigiere in deinen Eclipse-Workspace-Ordner.  
Den Pfad findest du in Eclipse unter **File → Switch Workspace → Other** – dort steht der aktuelle Pfad.

```bash
# Beispiel Windows
cd C:\Users\DeinName\eclipse-workspace

# Beispiel Mac/Linux
cd /Users/DeinName/eclipse-workspace
```

### Schritt 2 – Repository klonen

```bash
git clone https://github.com/aeg-hub/nearbyfinder.git
```

Es wird automatisch ein Ordner `nearbyfinder` in deinem Workspace angelegt.

### Schritt 3 – Projekt in Eclipse importieren

1. Eclipse starten
2. Menü: **File → Import → General → Existing Projects into Workspace**
3. **Next** klicken
4. Bei **Select root directory** den `nearbyfinder`-Ordner auswählen
5. Das Projekt `nearbyfinder` erscheint in der Liste – Haken setzen
6. **Finish** klicken

### Schritt 4 – Prüfen ob alles funktioniert

Im **Package Explorer** links solltest du jetzt sehen:

```
nearbyfinder
└── src
    └── kdbaum
        ├── BSTNode.java
        ├── KDNode.java
        ├── KDTree.java
        ├── Location.java
        ├── LocationBST.java
        └── NearbyFinderMain.java
```

Rechtsklick auf `NearbyFinderMain.java` → **Run As → Java Application**  
Das Programm startet. Die meisten Ausgaben zeigen noch `null` oder leere Listen –
das ist normal, solange du noch nichts implementiert hast.

> ⚠️ Falls Eclipse beim Anlegen eines neuen Projekts fragt **„Create module-info.java?"**  
> → immer **Don't Create** wählen!

---

## Aktuellen Stand holen

Wenn dein Lehrer das Projekt aktualisiert hat, holst du die Änderungen so:

```bash
cd nearbyfinder
git pull
```

Oder direkt in Eclipse: Rechtsklick auf das Projekt → **Team → Pull**

---

## Dateien, die du bearbeiten sollst

| Datei | Aufgabe | Was du implementierst |
|---|---|---|
| `LocationBST.java` | 1.1 | `insertRec()`, `searchRec()`, `inorderRec()` |
| `LocationBST.java` | 1.2 | `rangeSearchRec()` |
| `KDTree.java` | 2.1 | `insertRec()` |
| `KDTree.java` | 2.2 | `nearestRec()` |
| `KDTree.java` | 2.3 | `rangeSearchRec()` |
| `NearbyFinderMain.java` | alle | Testfälle ergänzen |

---

## Testdaten

Im Projekt sind folgende deutsche Städte als Testdaten hinterlegt:

| Stadt | Breitengrad (lat) | Längengrad (lon) |
|---|---|---|
| Köln | 50.938 | 6.960 |
| Düsseldorf | 51.227 | 6.782 |
| Frankfurt | 50.110 | 8.682 |
| München | 48.137 | 11.576 |
| Hamburg | 53.551 | 9.994 |
| Berlin | 52.520 | 13.405 |
| Stuttgart | 48.775 | 9.182 |
| Leipzig | 51.340 | 12.375 |
| Dresden | 51.050 | 13.738 |
| Hannover | 52.374 | 9.738 |

---

## Häufige Probleme

**„Package kdbaum does not exist"**  
→ Der `src`-Ordner ist in Eclipse nicht als Source-Folder markiert.  
Rechtsklick auf `src` → **Build Path → Use as Source Folder**

**„NearbyFinderMain cannot be resolved"**  
→ Projekt einmal neu bauen: Menü **Project → Clean → Clean all projects**

**Git-Befehl nicht gefunden (Windows)**  
→ Git wurde nicht installiert oder nicht zum PATH hinzugefügt.  
Git neu installieren und dabei „Add to PATH" aktivieren.

---

## Lizenz

MIT License · Albert-Einstein-Gymnasium Sankt Augustin  
Dieses Projekt darf für Bildungszwecke frei verwendet und angepasst werden.# NearbyFinder 🗺️

> Informatik-Leistungskurs · Albert-Einstein-Gymnasium Sankt Augustin  
> Thema: Algorithmen & Datenstrukturen – Binärer Suchbaum und k-d Tree

---

## Was ist NearbyFinder?

NearbyFinder ist ein vereinfachtes Geoinformationssystem: Orte (z. B. Städte)
werden nach ihren Koordinaten (Breiten- und Längengrad) verwaltet und durchsucht.

Im Laufe der Aufgabe entwickelst du die Datenstruktur schrittweise:

- **Aufgabe 1** – Binärer Suchbaum (BST): Orte nach Längengrad verwalten und durchsuchen
- **Aufgabe 2** – k-d Tree: Erweiterung auf zwei Dimensionen (lat + lon)
- **Aufgabe 3** – Reflexion, Komplexitätsanalyse und Transfer

---

## Projektstruktur

```
nearbyfinder/
├── src/
│   └── kdbaum/
│       ├── Location.java          # Datenklasse – vorgegeben, nicht verändern
│       ├── BSTNode.java           # BST-Knoten – vorgegeben, nicht verändern
│       ├── KDNode.java            # k-d-Tree-Knoten – vorgegeben, nicht verändern
│       ├── LocationBST.java       # Aufgabe 1 – hier implementierst du
│       ├── KDTree.java            # Aufgabe 2 – hier implementierst du
│       └── NearbyFinderMain.java  # Testklasse – kannst du frei anpassen
└── README.md
```

---

## Projekt in Eclipse einrichten

### Voraussetzungen

- [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/) installiert
- [Git](https://git-scm.com/downloads) installiert
- Ein Terminal / die Git Bash geöffnet

### Schritt 1 – Repository klonen

Öffne ein Terminal (Windows: Git Bash oder CMD, Mac/Linux: Terminal) und gib ein:

```bash
git clone https://github.com/aeg-hub/nearbyfinder.git
```

Danach befindet sich ein Ordner `nearbyfinder` auf deinem Computer.

### Schritt 2 – Projekt in Eclipse importieren

1. Eclipse starten
2. Menü: **File → New → Java Project**
3. **Project name:** `nearbyfinder`  
   ⚠️ Den Haken bei „Use default location" **entfernen**
4. Bei **Location** den geklonten Ordner auswählen (der `nearbyfinder`-Ordner aus Schritt 1)
5. **Finish** klicken
6. Falls Eclipse fragt „Create module-info.java?" → **Don't Create** wählen

### Schritt 3 – Prüfen ob alles funktioniert

Im **Package Explorer** links solltest du jetzt sehen:

```
nearbyfinder
└── src
    └── kdbaum
        ├── BSTNode.java
        ├── KDNode.java
        ├── KDTree.java
        ├── Location.java
        ├── LocationBST.java
        └── NearbyFinderMain.java
```

Klicke mit der rechten Maustaste auf `NearbyFinderMain.java` → **Run As → Java Application**.  
Das Programm sollte starten (die meisten Ausgaben zeigen noch `null` oder leere Listen –
das ist normal, solange du noch nichts implementiert hast).

---

## Aktuellen Stand vom Lehrer holen

Wenn dein Lehrer das Grundgerüst aktualisiert hat, kannst du die Änderungen so holen:

```bash
cd nearbyfinder
git pull
```

---

## Dateien, die du bearbeiten sollst

| Datei | Aufgabe | Was du implementierst |
|---|---|---|
| `LocationBST.java` | 1.1 | `insertRec()`, `searchRec()`, `inorderRec()` |
| `LocationBST.java` | 1.2 | `rangeSearchRec()` |
| `KDTree.java` | 2.1 | `insertRec()` |
| `KDTree.java` | 2.2 | `nearestRec()` |
| `KDTree.java` | 2.3 | `rangeSearchRec()` |
| `NearbyFinderMain.java` | alle | Testfälle ergänzen |

---

## Testdaten

Im Projekt sind folgende deutsche Städte als Testdaten hinterlegt:

| Stadt | Breitengrad (lat) | Längengrad (lon) |
|---|---|---|
| Köln | 50.938 | 6.960 |
| Düsseldorf | 51.227 | 6.782 |
| Frankfurt | 50.110 | 8.682 |
| München | 48.137 | 11.576 |
| Hamburg | 53.551 | 9.994 |
| Berlin | 52.520 | 13.405 |
| Stuttgart | 48.775 | 9.182 |
| Leipzig | 51.340 | 12.375 |
| Dresden | 51.050 | 13.738 |
| Hannover | 52.374 | 9.738 |

---

## Häufige Probleme

**„Package kdbaum does not exist"**  
→ Der `src`-Ordner ist in Eclipse nicht als Source-Folder markiert.  
Rechtsklick auf `src` → **Build Path → Use as Source Folder**

**„NearbyFinderMain cannot be resolved"**  
→ Projekt einmal neu bauen: Menü **Project → Clean → Clean all projects**

**Git-Befehl nicht gefunden (Windows)**  
→ Git wurde nicht installiert oder nicht zum PATH hinzugefügt.  
Git neu installieren und dabei „Add to PATH" aktivieren.

---

## Lizenz

MIT License · Albert-Einstein-Gymnasium Sankt Augustin  
Dieses Projekt darf für Bildungszwecke frei verwendet und angepasst werden.
