package kdbaum;


public class Tools {
	

	public record Point(double x, double y) {

	}

	public static Point koordinateZuPixel(double laengengrad, double breitengrad, int bildBreite, int bildHoehe) {
// Längengrad: -180 bis +180 → 0 bis bildBreite
		int x = (int) ((laengengrad + 180.0) / 360.0 * bildBreite);

// Breitengrad: +90 bis -90 → 0 bis bildHoehe (Y-Achse ist invertiert!)
		int y = (int) ((90.0 - breitengrad) / 180.0 * bildHoehe);

		return new Point(x, y);
	}

}
