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
                // -- bereits vorhanden --
                new Location("Köln",                50.938,  6.960),
                new Location("Düsseldorf",          51.227,  6.782),
                new Location("Frankfurt",           50.110,  8.682),
                new Location("München",             48.137, 11.576),
                new Location("Hamburg",             53.551,  9.994),
                new Location("Berlin",              52.520, 13.405),
                new Location("Stuttgart",           48.775,  9.182),
                new Location("Leipzig",             51.340, 12.375),
                new Location("Dresden",             51.050, 13.738),
                new Location("Hannover",            52.374,  9.738),

                // -- Nordrhein-Westfalen --
                new Location("Dortmund",            51.514,  7.468),
                new Location("Essen",               51.457,  7.012),
                new Location("Duisburg",            51.435,  6.762),
                new Location("Bochum",              51.482,  7.217),
                new Location("Wuppertal",           51.257,  7.150),
                new Location("Bielefeld",           52.021,  8.532),
                new Location("Bonn",                50.733,  7.099),
                new Location("Münster",             51.962,  7.626),
                new Location("Aachen",              50.776,  6.084),
                new Location("Gelsenkirchen",       51.517,  7.085),
                new Location("Mönchengladbach",     51.195,  6.441),
                new Location("Krefeld",             51.338,  6.585),
                new Location("Oberhausen",          51.470,  6.851),
                new Location("Hagen",               51.360,  7.473),
                new Location("Hamm",                51.680,  7.815),
                new Location("Solingen",            51.178,  7.083),
                new Location("Leverkusen",          51.045,  6.984),
                new Location("Paderborn",           51.719,  8.754),
                new Location("Siegen",              50.875,  8.024),

                // -- Bayern --
                new Location("Nürnberg",            49.454, 11.078),
                new Location("Augsburg",            48.370, 10.898),
                new Location("Regensburg",          49.013, 12.102),
                new Location("Ingolstadt",          48.763, 11.426),
                new Location("Würzburg",            49.792,  9.934),
                new Location("Fürth",               49.477, 10.989),
                new Location("Erlangen",            49.598, 11.004),
                new Location("Bayreuth",            49.945, 11.578),
                new Location("Bamberg",             49.899, 10.899),
                new Location("Landshut",            48.537, 12.152),
                new Location("Rosenheim",           47.857, 12.128),
                new Location("Kempten",             47.726, 10.315),

                // -- Baden-Württemberg --
                new Location("Karlsruhe",           49.006,  8.404),
                new Location("Mannheim",            49.487,  8.466),
                new Location("Freiburg",            47.997,  7.842),
                new Location("Heidelberg",          49.399,  8.673),
                new Location("Heilbronn",           49.143,  9.220),
                new Location("Ulm",                 48.398,  9.991),
                new Location("Pforzheim",           48.891,  8.695),
                new Location("Reutlingen",          48.491,  9.204),
                new Location("Tübingen",            48.521,  9.058),
                new Location("Konstanz",            47.663,  9.175),
                new Location("Ravensburg",          47.782,  9.614),

                // -- Hessen --
                new Location("Wiesbaden",           50.078,  8.239),
                new Location("Kassel",              51.316,  9.499),
                new Location("Darmstadt",           49.872,  8.651),
                new Location("Fulda",               50.556,  9.676),
                new Location("Marburg",             50.809,  8.771),
                new Location("Gießen",              50.584,  8.678),
                new Location("Offenbach",           50.095,  8.776),

                // -- Niedersachsen --
                new Location("Braunschweig",        52.267, 10.527),
                new Location("Osnabrück",           52.279,  8.047),
                new Location("Oldenburg",           53.143,  8.214),
                new Location("Wolfsburg",           52.423, 10.787),
                new Location("Göttingen",           51.534,  9.933),
                new Location("Hildesheim",          52.152,  9.957),
                new Location("Salzgitter",          52.150, 10.329),
                new Location("Delmenhorst",         53.051,  8.632),

                // -- Sachsen --
                new Location("Chemnitz",            50.833, 12.917),
                new Location("Zwickau",             50.718, 12.496),
                new Location("Plauen",              50.499, 12.135),
                new Location("Görlitz",             51.154, 14.987),
                new Location("Erfurt",              50.978, 11.029),

                // -- Thüringen --
                new Location("Jena",                50.927, 11.586),
                new Location("Gera",                50.878, 12.081),
                new Location("Weimar",              50.979, 11.330),
                new Location("Eisenach",            50.975, 10.324),

                // -- Sachsen-Anhalt --
                new Location("Magdeburg",           52.131, 11.640),
                new Location("Halle",               51.483, 11.970),
                new Location("Dessau",              51.836, 12.243),

                // -- Brandenburg & Berlin-Umland --
                new Location("Potsdam",             52.396, 13.058),
                new Location("Cottbus",             51.761, 14.334),
                new Location("Frankfurt (Oder)",    52.343, 14.553),

                // -- Mecklenburg-Vorpommern --
                new Location("Rostock",             54.092, 12.100),
                new Location("Schwerin",            53.635, 11.401),
                new Location("Greifswald",          54.096, 13.387),
                new Location("Stralsund",           54.309, 13.082),

                // -- Schleswig-Holstein --
                new Location("Kiel",                54.323, 10.123),
                new Location("Lübeck",              53.870, 10.686),
                new Location("Flensburg",           54.783,  9.436),
                new Location("Neumünster",          54.074,  9.981),

                // -- Bremen & Umland --
                new Location("Bremen",              53.079,  8.801),
                new Location("Bremerhaven",         53.540,  8.580),

                // -- Rheinland-Pfalz & Saarland --
                new Location("Mainz",               49.999,  8.274),
                new Location("Ludwigshafen",        49.477,  8.445),
                new Location("Koblenz",             50.356,  7.589),
                new Location("Trier",               49.749,  6.637),
                new Location("Kaiserslautern",      49.444,  7.769),
                new Location("Saarbrücken",         49.235,  7.003),
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
