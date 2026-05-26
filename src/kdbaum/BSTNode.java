package kdbaum;

/**
 * =============================================================================
 *  NearbyFinder – Klasse BSTNode
 * =============================================================================
 *  Zugehörigkeit : Aufgabe 1 – Binärer Suchbaum (BST)
 *  Beschreibung  : Knoten des binären Suchbaums. Jeder Knoten speichert
 *                  ein Location-Objekt sowie Referenzen auf den linken
 *                  und rechten Teilbaum.
 *                  Diese Klasse ist vollständig vorgegeben und muss
 *                  NICHT verändert werden.
 * =============================================================================
 */
public class BSTNode {

    public Location location;   // gespeicherter Ort
    public BSTNode  left;       // linker  Teilbaum (kleinerer Längengrad)
    public BSTNode  right;      // rechter Teilbaum (größerer Längengrad)

    /**
     * Erstellt einen neuen Knoten mit dem angegebenen Ort.
     * left und right werden auf null gesetzt.
     *
     * @param location der zu speichernde Ort
     */
    public BSTNode(Location location) {
        this.location = location;
        this.left     = null;
        this.right    = null;
    }
}
