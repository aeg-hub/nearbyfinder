package kdbaum;

import java.util.ArrayList;
import java.util.List;

/**
 * =============================================================================
 *  NearbyFinder – Klasse KDTree
 * =============================================================================
 *  Zugehörigkeit : Aufgabe 2 – k-d Tree
 *  Beschreibung  : Erweiterung des BST-Prinzips auf zwei Dimensionen.
 *                  Der Baum teilt den Raum auf jeder Ebene abwechselnd
 *                  nach Längengrad (lon) und Breitengrad (lat):
 *
 *                      Tiefe 0 (Wurzel) : Teilung nach lon
 *                      Tiefe 1          : Teilung nach lat
 *                      Tiefe 2          : Teilung nach lon
 *                      ...
 *
 *  Aufgabe 2.1   : insert() implementieren
 *  Aufgabe 2.2   : nearestNeighbour() implementieren
 *  Aufgabe 2.3   : rangeSearch() implementieren
 *  Aufgabe 2.4   : Schriftliche Analyse (kein Code)
 * =============================================================================
 */
public class KDTree {

    /** Wurzel des k-d Trees. Zu Beginn null (leerer Baum). */
    private KDNode root;

    /**
     * Erstellt einen leeren k-d Tree.
     */
    public KDTree() {
        this.root = null;
    }

    // =========================================================================
    //  Aufgabe 2.1 – insert
    // =========================================================================

    /**
     * Fügt einen neuen Ort in den k-d Tree ein.
     * Die Teilungsachse wechselt mit jeder Tiefe:
     *   depth % 2 == 0  →  Vergleich nach lon
     *   depth % 2 == 1  →  Vergleich nach lat
     * Gleiche Achsenwerte werden nach rechts eingefügt.
     *
     * @param loc der einzufügende Ort
     */
    public void insert(Location loc) {
        root = insertRec(root, loc, 0);
    }

    /**
     * Hilfsmethode: Fügt loc rekursiv in den Teilbaum mit Wurzel node ein.
     * depth gibt die aktuelle Tiefe an und bestimmt die Teilungsachse.
     *
     * @param node  aktueller Knoten (kann null sein)
     * @param loc   einzufügender Ort
     * @param depth aktuelle Tiefe im Baum
     * @return      Wurzel des aktualisierten Teilbaums
     */
    private KDNode insertRec(KDNode node, Location loc, int depth) {
        // TODO 2.1: Implementiere die rekursive Einfüge-Logik für den k-d Tree.
        //
        //  Hinweise:
        //  - Ist node == null, erzeuge einen neuen KDNode(loc, depth) und gib ihn zurück.
        //
        //  - Bestimme den Achsenwert des neuen Ortes:
        //      double newVal = (depth % 2 == 0) ? loc.lon : loc.lat;
        //
        //  - Bestimme den Achsenwert des aktuellen Knotens:
        //      double nodeVal = node.getAxisValue();
        //
        //  - Vergleiche newVal mit nodeVal:
        //      * newVal < nodeVal  → rekursiv links  weiter (depth + 1)
        //      * newVal >= nodeVal → rekursiv rechts weiter (depth + 1)
        //
        //  - Gib am Ende immer node zurück.

        return node; // Platzhalter – bitte ersetzen
    }

    // =========================================================================
    //  Aufgabe 2.2 – nearestNeighbour
    // =========================================================================

    /**
     * Findet den Ort im k-d Tree, der dem Anfragepunkt (queryLat, queryLon)
     * am nächsten liegt (euklidischer Abstand).
     *
     * @param queryLat Breitengrad des Anfragepunkts
     * @param queryLon Längengrad des Anfragepunkts
     * @return         nächstgelegener Ort oder null bei leerem Baum
     */
    public Location nearestNeighbour(double queryLat, double queryLon) {
        if (root == null) return null;
        KDNode[] best = { null };
        double[] bestDist = { Double.MAX_VALUE };
        nearestRec(root, queryLat, queryLon, best, bestDist);
        return best[0].location;
    }

    /**
     * Hilfsmethode: Durchsucht rekursiv den Teilbaum nach dem nächsten Nachbarn.
     *
     * best[0]     : bisher bester Knoten (wird aktualisiert)
     * bestDist[0] : Abstand zum bisher besten Knoten (wird aktualisiert)
     *
     * @param node      aktueller Knoten
     * @param queryLat  Breitengrad des Anfragepunkts
     * @param queryLon  Längengrad des Anfragepunkts
     * @param best      Array der Länge 1 – speichert den besten Knoten
     * @param bestDist  Array der Länge 1 – speichert den besten Abstand
     */
    private void nearestRec(KDNode node, double queryLat, double queryLon,
                             KDNode[] best, double[] bestDist) {
        // TODO 2.2: Implementiere die rekursive Nearest-Neighbour-Suche.
        //
        //  Algorithmus (3 Schritte):
        //
        //  Schritt 1 – Abbruch
        //      Ist node == null → return (nichts zu tun).
        //
        //  Schritt 2 – Aktuellen Knoten prüfen
        //      Berechne den Abstand von node.location zum Anfragepunkt:
        //          double dist = node.location.distanceTo(queryLat, queryLon);
        //      Ist dist < bestDist[0]:
        //          best[0]    = node;
        //          bestDist[0] = dist;
        //
        //  Schritt 3 – Teilbäume durchsuchen
        //      a) Bestimme den Achsenwert des Anfragepunkts:
        //             double queryVal = (node.depth % 2 == 0) ? queryLon : queryLat;
        //         Vergleiche queryVal mit node.getAxisValue():
        //             queryVal < nodeVal  → zuerst linken  Teilbaum besuchen
        //             queryVal >= nodeVal → zuerst rechten Teilbaum besuchen
        //         (Den anderen Teilbaum nennen wir "other".)
        //
        //      b) Rekursiv den "näheren" Teilbaum durchsuchen (immer).
        //
        //      c) Pruning für den anderen Teilbaum:
        //             double axisDistance = Math.abs(queryVal - node.getAxisValue());
        //         Ist axisDistance < bestDist[0]:
        //             → anderen Teilbaum ebenfalls durchsuchen
        //         Sonst:
        //             → anderen Teilbaum überspringen (Pruning!)
    }

    // =========================================================================
    //  Aufgabe 2.3 – rangeSearch
    // =========================================================================

    /**
     * Gibt alle Orte zurück, die im angegebenen rechteckigen Bereich liegen,
     * d. h. deren Breiten- UND Längengrad im jeweiligen Intervall liegen.
     *
     * @param latMin untere Grenze des Breitengrads  (inklusiv)
     * @param latMax obere  Grenze des Breitengrads  (inklusiv)
     * @param lonMin untere Grenze des Längengrads   (inklusiv)
     * @param lonMax obere  Grenze des Längengrads   (inklusiv)
     * @return       Liste aller Orte im angegebenen Rechteck
     */
    public List<Location> rangeSearch(double latMin, double latMax,
                                       double lonMin, double lonMax) {
        List<Location> result = new ArrayList<>();
        rangeSearchRec(root, latMin, latMax, lonMin, lonMax, result);
        return result;
    }

    /**
     * Hilfsmethode: Durchsucht rekursiv den Teilbaum nach Orten im Bereich.
     *
     * @param node   aktueller Knoten
     * @param latMin untere Breitengrad-Grenze
     * @param latMax obere  Breitengrad-Grenze
     * @param lonMin untere Längengrad-Grenze
     * @param lonMax obere  Längengrad-Grenze
     * @param result Ergebnisliste (wird befüllt)
     */
    private void rangeSearchRec(KDNode node,
                                  double latMin, double latMax,
                                  double lonMin, double lonMax,
                                  List<Location> result) {
        // TODO 2.3: Implementiere die 2D-Bereichssuche mit Pruning.
        //
        //  Hinweise:
        //  - Abbruchbedingung: node == null → return.
        //
        //  - Prüfe, ob node.location im Bereich liegt (beide Dimensionen!):
        //      node.location.lat >= latMin && node.location.lat <= latMax
        //      node.location.lon >= lonMin && node.location.lon <= lonMax
        //    → Falls ja: node.location zur Ergebnisliste hinzufügen.
        //
        //  - Pruning abhängig von der Teilungsachse (node.depth % 2):
        //
        //    Lon-Ebene (depth % 2 == 0):
        //      node.location.lon < lonMin → nur rechten  Teilbaum durchsuchen
        //      node.location.lon > lonMax → nur linken   Teilbaum durchsuchen
        //      Sonst                      → beide Teilbäume durchsuchen
        //
        //    Lat-Ebene (depth % 2 == 1):
        //      node.location.lat < latMin → nur rechten  Teilbaum durchsuchen
        //      node.location.lat > latMax → nur linken   Teilbaum durchsuchen
        //      Sonst                      → beide Teilbäume durchsuchen
    }

    // =========================================================================
    //  Hilfsmethoden (vorgegeben)
    // =========================================================================

    /**
     * Gibt die Anzahl der gespeicherten Orte zurück.
     */
    public int size() {
        return sizeRec(root);
    }

    private int sizeRec(KDNode node) {
        if (node == null) return 0;
        return 1 + sizeRec(node.left) + sizeRec(node.right);
    }

    /**
     * Gibt true zurück, wenn der Baum leer ist.
     */
    public boolean isEmpty() {
        return root == null;
    }
}
