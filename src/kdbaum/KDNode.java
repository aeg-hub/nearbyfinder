package kdbaum;

/**
 * =============================================================================
 *  NearbyFinder – Klasse KDNode
 * =============================================================================
 *  Zugehörigkeit : Aufgabe 2 – k-d Tree
 *  Beschreibung  : Knoten des k-d Trees. Wie BSTNode, aber mit einem
 *                  zusätzlichen depth-Attribut, das die Tiefe des Knotens
 *                  im Baum angibt. Die Tiefe bestimmt die Teilungsachse:
 *
 *                      depth % 2 == 0  →  Teilung nach Längengrad  (lon)
 *                      depth % 2 == 1  →  Teilung nach Breitengrad (lat)
 *
 *                  Diese Klasse ist vollständig vorgegeben und muss
 *                  NICHT verändert werden.
 * =============================================================================
 */
public class KDNode {

    public Location location;   // gespeicherter Ort
    public KDNode   left;       // linker  Teilbaum (kleinerer Achsenwert)
    public KDNode   right;      // rechter Teilbaum (größerer Achsenwert)
    public int      depth;      // Tiefe dieses Knotens im Baum (Wurzel = 0)

    /**
     * Erstellt einen neuen k-d-Tree-Knoten.
     *
     * @param location der zu speichernde Ort
     * @param depth    Tiefe dieses Knotens im Baum
     */
    public KDNode(Location location, int depth) {
        this.location = location;
        this.depth    = depth;
        this.left     = null;
        this.right    = null;
    }

    /**
     * Gibt den Achsenwert des gespeicherten Ortes zurück,
     * der für die Teilung auf dieser Ebene relevant ist.
     *
     * @return lon, wenn depth gerade; lat, wenn depth ungerade
     */
    public double getAxisValue() {
        return (depth % 2 == 0) ? location.lon : location.lat;
    }
}
