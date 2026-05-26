package kdbaum;

/**
 * =============================================================================
 *  NearbyFinder – Klasse Location
 * =============================================================================
 *  Zugehörigkeit : Grundgerüst (wird in allen Aufgaben verwendet)
 *  Beschreibung  : Repräsentiert einen geografischen Ort mit Name,
 *                  Breitengrad (lat) und Längengrad (lon).
 *                  Diese Klasse ist vollständig vorgegeben und muss
 *                  NICHT verändert werden.
 * =============================================================================
 */
public class Location {

    public String name;
    public double lat;   // Breitengrad  (z. B. 50.938 für Köln)
    public double lon;   // Längengrad   (z. B.  6.960 für Köln)

    /**
     * Erstellt einen neuen Ort.
     *
     * @param name Name des Ortes (z. B. "Köln")
     * @param lat  Breitengrad
     * @param lon  Längengrad
     */
    public Location(String name, double lat, double lon) {
        this.name = name;
        this.lat  = lat;
        this.lon  = lon;
    }

    /**
     * Berechnet den euklidischen Abstand zu einem Anfragepunkt.
     * Hinweis: Für echte Geodaten würde man die Haversine-Formel verwenden.
     *
     * @param queryLat Breitengrad des Anfragepunkts
     * @param queryLon Längengrad des Anfragepunkts
     * @return euklidischer Abstand (in Gradeinheiten)
     */
    public double distanceTo(double queryLat, double queryLon) {
        double dLat = this.lat - queryLat;
        double dLon = this.lon - queryLon;
        return Math.sqrt(dLat * dLat + dLon * dLon);
    }

    @Override
    public String toString() {
        return name + " (lat=" + lat + ", lon=" + lon + ")";
    }
}
