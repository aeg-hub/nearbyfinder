package kdbaum;

import java.util.ArrayList;
import java.util.List;

/**
 * =============================================================================
 *  NearbyFinder – Klasse LocationBST
 * =============================================================================
 *  Zugehörigkeit : Aufgabe 1 – Binärer Suchbaum (BST)
 *  Beschreibung  : Verwaltet eine Menge von Orten in einem binären Suchbaum.
 *                  Sortierkriterium ist ausschließlich der Längengrad (lon).
 *
 *  Aufgabe 1.1   : insert(), search(), inorder() implementieren
 *  Aufgabe 1.2   : rangeSearch() implementieren
 *  Aufgabe 1.3   : Schriftliche Analyse (kein Code)
 * =============================================================================
 */
public class LocationBST {

    /** Wurzel des Baums. Zu Beginn null (leerer Baum). */
    private BSTNode root;

    /**
     * Erstellt einen leeren Baum.
     */
    public LocationBST() {
        this.root = null;
    }

    // =========================================================================
    //  Aufgabe 1.1 a) – insert
    // =========================================================================

    /**
     * Fügt einen neuen Ort in den BST ein.
     * Sortierkriterium: Längengrad (loc.lon).
     * Gleiche Längengrade werden nach rechts eingefügt.
     *
     * @param loc der einzufügende Ort
     */
    public void insert(Location loc) {
        root = insertRec(root, loc);
    }

    /**
     * Hilfsmethode: Fügt loc rekursiv in den Teilbaum mit Wurzel node ein
     * und gibt den (ggf. neuen) Teilbaum zurück.
     *
     * @param node aktueller Knoten (kann null sein)
     * @param loc  einzufügender Ort
     * @return     Wurzel des aktualisierten Teilbaums
     */
    private BSTNode insertRec(BSTNode node, Location loc) {
        // TODO 1.1 a): Implementiere die rekursive Einfüge-Logik.
        //
        //  Hinweise:
        //  - Ist node == null, erzeuge einen neuen BSTNode und gib ihn zurück.
        //  - Vergleiche loc.lon mit node.location.lon:
        //      * loc.lon < node.location.lon  → rekursiv links weiter
        //      * loc.lon >= node.location.lon → rekursiv rechts weiter
        //  - Gib am Ende immer node zurück (wichtig für die Verkettung).

        return node; // Platzhalter – bitte ersetzen
    }

    // =========================================================================
    //  Aufgabe 1.1 b) – search
    // =========================================================================

    /**
     * Sucht den Ort mit genau dem angegebenen Längengrad.
     *
     * @param lon gesuchter Längengrad
     * @return    gefundener Ort oder null, falls nicht vorhanden
     */
    public Location search(double lon) {
        return searchRec(root, lon);
    }

    /**
     * Hilfsmethode: Sucht rekursiv im Teilbaum mit Wurzel node.
     *
     * @param node aktueller Knoten
     * @param lon  gesuchter Längengrad
     * @return     gefundener Ort oder null
     */
    private Location searchRec(BSTNode node, double lon) {
        // TODO 1.1 b): Implementiere die binäre Suche.
        //
        //  Hinweise:
        //  - Ist node == null, wurde der Ort nicht gefunden → return null.
        //  - Vergleiche lon mit node.location.lon:
        //      * Gleich  → gefunden, return node.location
        //      * Kleiner → links weitersuchen
        //      * Größer  → rechts weitersuchen

        return null; // Platzhalter – bitte ersetzen
    }

    // =========================================================================
    //  Aufgabe 1.1 c) – inorder
    // =========================================================================

    /**
     * Gibt alle Orte in aufsteigender Reihenfolge nach Längengrad zurück
     * (Inorder-Traversierung: links – Wurzel – rechts).
     *
     * @return sortierte Liste aller Orte
     */
    public List<Location> inorder() {
        List<Location> result = new ArrayList<>();
        inorderRec(root, result);
        return result;
    }

    /**
     * Hilfsmethode: Traversiert den Teilbaum inorder und fügt
     * alle Orte zur Liste result hinzu.
     *
     * @param node   aktueller Knoten
     * @param result Ergebnisliste (wird befüllt)
     */
    private void inorderRec(BSTNode node, List<Location> result) {
        // TODO 1.1 c): Implementiere die Inorder-Traversierung.
        //
        //  Hinweise:
        //  - Abbruchbedingung: node == null → nichts tun.
        //  - Reihenfolge: linker Teilbaum → node.location → rechter Teilbaum
    }

    // =========================================================================
    //  Aufgabe 1.2 – rangeSearch
    // =========================================================================

    /**
     * Gibt alle Orte zurück, deren Längengrad im Intervall [lonMin, lonMax] liegt.
     * Nutze die BST-Eigenschaft für effizientes Pruning:
     * Teilbäume, die keine Treffer enthalten können, werden übersprungen.
     *
     * @param lonMin untere Grenze des Längengrads (inklusiv)
     * @param lonMax obere  Grenze des Längengrads (inklusiv)
     * @return       Liste aller Orte im angegebenen Bereich
     */
    public List<Location> rangeSearch(double lonMin, double lonMax) {
        List<Location> result = new ArrayList<>();
        rangeSearchRec(root, lonMin, lonMax, result);
        return result;
    }

    /**
     * Hilfsmethode: Durchsucht rekursiv den Teilbaum mit Wurzel node.
     *
     * @param node   aktueller Knoten
     * @param lonMin untere Grenze
     * @param lonMax obere  Grenze
     * @param result Ergebnisliste (wird befüllt)
     */
    private void rangeSearchRec(BSTNode node, double lonMin, double lonMax,
                                 List<Location> result) {
        // TODO 1.2: Implementiere die Bereichssuche mit Pruning.
        //
        //  Hinweise:
        //  - Abbruchbedingung: node == null → nichts tun.
        //
        //  - Pruning (Teilbaum überspringen):
        //      * node.location.lon < lonMin → linker  Teilbaum kann keine Treffer enthalten
        //      * node.location.lon > lonMax → rechter Teilbaum kann keine Treffer enthalten
        //
        //  - Liegt node.location.lon im Bereich [lonMin, lonMax]?
        //      → node.location zur Ergebnisliste hinzufügen
        //
        //  - Rekursiv beide (oder nur einen) Teilbaum durchsuchen,
        //    abhängig von den Pruning-Bedingungen oben.
        //
        //  Ziel: Vermeide unnötiges Traversieren ganzer Teilbäume!
    }

    // =========================================================================
    //  Hilfsmethoden (vorgegeben)
    // =========================================================================

    /**
     * Gibt die Anzahl der gespeicherten Orte zurück.
     *
     * @return Anzahl der Knoten im Baum
     */
    public int size() {
        return sizeRec(root);
    }

    private int sizeRec(BSTNode node) {
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
