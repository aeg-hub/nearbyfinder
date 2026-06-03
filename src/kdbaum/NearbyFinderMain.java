package kdbaum;

import java.util.List;

import kdbaum.SimpleCanvasExtended.Point;
import kdbaum.SimpleCanvasExtended.Rect;

import java.awt.Color;

/**
 * =============================================================================
 *  NearbyFinder – Klasse NearbyFinderMain
 * =============================================================================
 *  Zugehörigkeit : Testklasse (alle Aufgaben)
 *  Beschreibung  : Einstiegspunkt des Programms. Enthält Testdaten und
 *                  Aufrufe aller implementierten Methoden.
 *                  Hier könnt ihr eure Implementierungen ausprobieren.
 *
 *  Hinweis       : Diese Datei darf frei angepasst und erweitert werden.
 * =============================================================================
 */
public class NearbyFinderMain {
	
    public static void main(String[] args) {

        // ─────────────────────────────────────────────────────────────────────
        //  Testdaten: 10 deutsche Städte
        // ─────────────────────────────────────────────────────────────────────
        Location[] staedte = {
            new Location("Köln",        50.938, 6.960),
            new Location("Düsseldorf",  51.227, 6.782),
            new Location("Frankfurt",   50.110, 8.682),
            new Location("München",     48.137, 11.576),
            new Location("Hamburg",     53.551, 9.994),
            new Location("Berlin",      52.520, 13.405),
            new Location("Stuttgart",   48.775, 9.182),
            new Location("Leipzig",     51.340, 12.375),
            new Location("Dresden",     51.050, 13.738),
            new Location("Hannover",    52.374, 9.738),
        };

        // ─────────────────────────────────────────────────────────────────────
        //  Aufgabe 1 – BST
        // ─────────────────────────────────────────────────────────────────────
        System.out.println("=== Aufgabe 1: Binärer Suchbaum (BST) ===");
        System.out.println();

        LocationBST bst = new LocationBST();
        for (Location loc : staedte) {
            bst.insert(loc);
        }
        System.out.println("Anzahl eingefügter Orte: " + bst.size());

        // Aufgabe 1.1 c) – Inorder-Ausgabe (sollte nach lon sortiert sein)
        System.out.println("\n-- Inorder (sortiert nach Längengrad) --");
        List<Location> sorted = bst.inorder();
        for (Location loc : sorted) {
            System.out.println("  " + loc);
        }

        // Aufgabe 1.1 b) – Suche
        System.out.println("\n-- Suche nach lon=8.682 (Frankfurt) --");
        Location gefunden = bst.search(8.682);
        System.out.println("  Ergebnis: " + gefunden);

        System.out.println("\n-- Suche nach lon=7.000 (nicht vorhanden) --");
        Location nichtGefunden = bst.search(7.000);
        System.out.println("  Ergebnis: " + nichtGefunden);

        // Aufgabe 1.2 – Bereichssuche
        System.out.println("\n-- Bereichssuche: lon in [8.0, 12.0] --");
        List<Location> bereich1 = bst.rangeSearch(8.0, 12.0);
        System.out.println("  Treffer (" + bereich1.size() + "):");
        for (Location loc : bereich1) {
            System.out.println("    " + loc);
        }

        System.out.println("\n-- Bereichssuche: lon in [6.5, 9.0] --");
        List<Location> bereich2 = bst.rangeSearch(6.5, 9.0);
        System.out.println("  Treffer (" + bereich2.size() + "):");
        for (Location loc : bereich2) {
            System.out.println("    " + loc);
        }

        System.out.println();
        System.out.println("=".repeat(50));

        // ─────────────────────────────────────────────────────────────────────
        //  Aufgabe 2 – k-d Tree
        // ─────────────────────────────────────────────────────────────────────
        System.out.println("\n=== Aufgabe 2: k-d Tree ===");
        System.out.println();

        KDTree kdTree = new KDTree();
        kdTree.canvas.setBackgroundImage("deutschlandkarte.png");
        for (Location loc : staedte) {
            kdTree.insert(loc);
            Point pC = KDTree.pointFromLocation(loc);
            kdTree.canvas.drawPoint(pC, Color.BLUE);
            kdTree.canvas.drawLabel(pC, loc.name, Color.BLACK);

        }
        System.out.println("Anzahl eingefügter Orte: " + kdTree.size());

        // Baum ausgeben
        kdTree.draw();
        
        // Aufgabe 2.2 – Nächster Nachbar
        System.out.println("\n-- Nächster Nachbar zu (51.5, 7.5) --");
        System.out.println("   (Anfragepunkt liegt zwischen Köln, Düsseldorf und Dortmund)");
        Location nn1 = kdTree.nearestNeighbour(51.5, 7.5);
        System.out.println("  Ergebnis: " + nn1);

        System.out.println("\n-- Nächster Nachbar zu (52.0, 10.5) --");
        System.out.println("   (Anfragepunkt liegt zwischen Hannover, Hamburg und Berlin)");
        Location nn2 = kdTree.nearestNeighbour(52.0, 10.5);
        System.out.println("  Ergebnis: " + nn2);

        // Aufgabe 2.3 – 2D-Bereichssuche
        System.out.println("\n-- 2D-Bereichssuche: lat=[48.0, 52.0], lon=[8.0, 14.0] --");
        System.out.println("   (Süd- und Mitteldeutschland, östlich von Frankfurt)");
        List<Location> kdBereich = kdTree.rangeSearch(48.0, 52.0, 8.0, 14.0);
        System.out.println("  Treffer (" + kdBereich.size() + "):");
        for (Location loc : kdBereich) {
            System.out.println("    " + loc);
        }
    }
}
