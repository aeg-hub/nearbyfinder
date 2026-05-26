package kdbaum;

import java.awt.Color;

import kdbaum.Tools.Point;

public class KDTreeVisualization {

    // -------------------------------------------------------
    // VARIANTE 1: Explizite Klasse, die das Interface implementiert
    //
    // DIDAKTISCHER HINWEIS – Beobachter-Muster (Observer Pattern):
    // Diese Klasse ist ein konkreter "Beobachter" (Observer).
    // Sie implementiert das Interface CanvasClickListener, das in
    // SimpleCanvas definiert ist. Der Zugriff auf ein inneres Interface
    // funktioniert genauso wie bei inneren Klassen: SimpleCanvas.CanvasClickListener
    // Das Interface ist "static" in dem Sinne, dass man kein SimpleCanvas-
    // Objekt braucht, um es zu implementieren – es gehört zur Klasse, nicht
    // zur Instanz.
    // -------------------------------------------------------
    static class PunktHinzufuegenListener implements SimpleCanvas.CanvasClickListener {

        private final SimpleCanvas canvas;

        public PunktHinzufuegenListener(SimpleCanvas canvas) {
            this.canvas = canvas;
        }

        @Override
        public void click(int x, int y) {
            System.out.println("Neuer Punkt bei: " + x + ", " + y);
            canvas.drawPoint(x, y, Color.BLUE);
            canvas.drawLabel(x, y, "(" + x + ", " + y + ")", Color.WHITE);
        }
    }

    // -------------------------------------------------------
    // HAUPTPROGRAMM – beide Varianten im Vergleich
    // -------------------------------------------------------
    public static void main(String[] args) {

        SimpleCanvas canvas = new SimpleCanvas(1200, 600, "KD-Baum Visualisierung");
        canvas.setBackgroundImage("weltkarte.jpg");

        // Einige Beispielpunkte zeichnen
        double[][] punkte = {{200, 150}, {400, 300}, {600, 100}, {300, 450}};
        for (double[] p : punkte) {
            canvas.drawPoint(p[0], p[1], Color.RED);
            canvas.drawLabel(p[0], p[1], "(" + (int)p[0] + ", " + (int)p[1] + ")", Color.BLACK);
        }
       
        // Vertikale Split-Linie des KD-Baums
        canvas.drawLine(600, 0, 600, 600, Color.RED);

        // ---------------------------------------------------
        // VARIANTE 1: Listener als explizites Interface-Objekt
        //
        // Ein Objekt vom Typ PunktHinzufuegenListener wird erstellt
        // und als Beobachter registriert. Das macht das Muster sehr
        // explizit sichtbar: Es gibt einen "Sender" (SimpleCanvas) und
        // einen "Empfänger" (PunktHinzufuegenListener).
        // ---------------------------------------------------
        SimpleCanvas.CanvasClickListener listener1 = new PunktHinzufuegenListener(canvas);
        canvas.setClickListener(listener1);

        // ---------------------------------------------------
        // VARIANTE 2: Listener als Lambda-Ausdruck
        //
        // DIDAKTISCHER HINWEIS:
        // Das Lambda ist eine Kurzschreibweise für genau dasselbe Konzept.
        // Java erkennt, dass CanvasClickListener ein "funktionales Interface"
        // ist (genau eine abstrakte Methode), und erlaubt daher diese kompakte
        // Schreibweise. Intern erstellt Java trotzdem ein Objekt – das Lambda
        // ist syntaktischer Zucker, kein anderes Konzept.
        //
        // Diskussionsfrage für die Klasse:
        // Wann nimmt man eine eigene Klasse, wann ein Lambda?
        // Faustregel: Lambda wenn die Logik kurz und einmalig ist.
        // Eigene Klasse wenn der Listener Zustand speichert oder
        // mehrfach verwendet wird.
        // ---------------------------------------------------
        canvas.setClickListener((x, y) -> {
            System.out.println("Klick bei: " + x + ", " + y);
            canvas.drawPoint(x, y, Color.GREEN);
            canvas.drawLabel(x, y, "(" + x + ", " + y + ")", Color.BLACK);
        });
    }
}