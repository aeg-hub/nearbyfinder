package kdbaum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * SimpleCanvas – eine vereinfachte Zeichenfläche für den Informatik-Unterricht.
 *
 * DIDAKTISCHER HINWEIS – Entwurfsmuster in dieser Klasse:
 *
 * 1. FASSADEN-MUSTER (Facade Pattern):
 *    Swing ist komplex. Diese Klasse versteckt JFrame, JPanel, paintComponent
 *    und das Event-Dispatch-Thread-Konzept hinter einer einfachen API.
 *
 * 2. BEOBACHTER-MUSTER (Observer Pattern):
 *    Über das Interface CanvasClickListener kann sich beliebiger Code
 *    für Mausklick-Ereignisse registrieren. SimpleCanvas "kennt" den
 *    Listener nicht – es ruft nur click(x, y) auf. Das entkoppelt
 *    die Zeichenfläche von der Anwendungslogik.
 *
 * 3. KOMMANDO-MUSTER (Command Pattern):
 *    Zeichenbefehle werden als Objekte gespeichert und bei jedem
 *    paintComponent erneut ausgeführt. Das ermöglicht korrektes
 *    Neuzeichnen nach Fenster-Resize.
 */
public class SimpleCanvasExtended {
	
	// -------------------------------------------------------
	// innere Klassen für die extended Version
	// -------------------------------------------------------
	public static class Rect {
        public double x, y, width, height;

        public Rect(double x, double y, double width, double height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
        
        public Rect(Point a, Point b) {
        	this.x = Math.min(a.x,b.x);
        	this.y = Math.min(a.y, b.y);
        	this.width = Math.abs(a.x-b.x);
        	this.height = Math.abs(a.y-b.y);
        }
        
        public Point pointToRelative(Point p) {
        	return new Point((p.x-this.x)/this.width, (p.y-this.y)/this.height);
        }
        
        public Point relativeToPoint(Point rel) {
        	return new Point(this.x+rel.x*this.width, this.y+rel.y*this.height);
        }
        
        public Point scaleFrom(Rect fromRect, Point fromPoint) {
        	return this.relativeToPoint(fromRect.pointToRelative(fromPoint));        	
        }

		public Point mirrorY(Point pC) {
			return new Point(pC.x, this.y+this.height-(pC.y-this.y));
		}
    }
	
	public static class Point {
		public double x, y;
		
		public Point(double x, double y) {
			this.x = x; 
			this.y = y;
		}
		
		public double dist(Point p) {
			return Math.sqrt((this.x-p.x)*(this.x-p.x)+(this.y-p.y)*(this.y-p.y));
		}
	}

    // -------------------------------------------------------
    // BEOBACHTER-MUSTER: Das Listener-Interface
    // -------------------------------------------------------

    /**
     * Dieses Interface muss implementiert werden, um auf Mausklicks zu reagieren.
     *
     * DIDAKTISCHER HINWEIS:
     * Das ist das Observer-Pattern in seiner einfachsten Form. Wer auf
     * Ereignisse reagieren möchte, implementiert dieses Interface und
     * registriert sich als Listener. SimpleCanvas weiß nichts über den
     * konkreten Listener – es ruft nur click() auf. Das nennt sich
     * "lose Kopplung" und ist ein zentrales Prinzip guten Designs.
     *
     * In Java selbst wird dieses Prinzip überall verwendet:
     * ActionListener, KeyListener, MouseListener – alles dasselbe Muster.
     */
    public interface CanvasClickListener {
        void click(int x, int y);
    }

    // -------------------------------------------------------
    // INTERNE IMPLEMENTIERUNG – Swing-Details (die Fassade!)
    // -------------------------------------------------------

    private final JFrame frame;
    private final DrawingPanel panel;
    private CanvasClickListener clickListener = null;

    private static class DrawingPanel extends JPanel {

        private final java.util.List<DrawCommand> commands = new java.util.ArrayList<>();
        private BufferedImage backgroundImage = null;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);

            // Hintergrundbild zuerst zeichnen, falls vorhanden
            if (backgroundImage != null) {
                g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }

            for (DrawCommand cmd : commands) {
                cmd.draw(g2);
            }
        }

        void addCommand(DrawCommand cmd) {
            // EDT = Event Dispatch Thread: Swing's einziger Thread, der die
            // Benutzeroberfläche zeichnet und Ereignisse (Klicks, Tastatur etc.)
            // verarbeitet. Alle Änderungen an UI-Elementen müssen auf diesem
            // Thread stattfinden – sonst drohen Fehler wie ConcurrentModificationException.
            // invokeLater() stellt sicher, dass der Code auf dem EDT ausgeführt wird.
            SwingUtilities.invokeLater(() -> {
                commands.add(cmd);
                // repaint(); // wird vermutlich auch an anderer STelle aufgerufen
            });
        }
        void clearCommands() { commands.clear(); }
        void setBackgroundImage(BufferedImage img) { backgroundImage = img; }
    }

    @FunctionalInterface
    private interface DrawCommand {
        void draw(Graphics2D g);
    }

    // -------------------------------------------------------
    // ÖFFENTLICHE SCHNITTSTELLE (API) – das sehen die Schüler
    // -------------------------------------------------------

    /**
     * Erstellt und öffnet ein Zeichenfenster.
     */
    public SimpleCanvasExtended(int width, int height, String title) {
        panel = new DrawingPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(width, height));

        // BEOBACHTER-MUSTER: Swing's eigener MouseListener wird intern
        // verwendet und auf unseren einfachen CanvasClickListener weitergeleitet.
        // Die Schüler sehen davon nichts – sie implementieren nur click(x, y).
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { 
            	// mouseClicked kann man auch mal ausprobieren...
                if (clickListener != null) {
                    clickListener.click(e.getX(), e.getY());
                }
            }
        });

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Registriert einen Listener für Mausklicks.
     *
     * DIDAKTISCHER HINWEIS:
     * Das ist die "register"-Methode des Observer-Patterns. Ab diesem
     * Moment wird click(x, y) bei jedem Mausklick aufgerufen.
     */
    public void setClickListener(CanvasClickListener listener) {
        this.clickListener = listener;
    }

    /**
     * Lädt ein Hintergrundbild aus einer Datei.
     * Unterstützte Formate: PNG, JPG
     */
    public void setBackgroundImage(String filePath) {
        try {
            BufferedImage img = ImageIO.read(new File(filePath));
            panel.setBackgroundImage(img);
            refresh();
        } catch (IOException e) {
            System.err.println("Bild konnte nicht geladen werden: " + filePath);
        }
    }

    /**
     * Zeichnet einen Punkt an Position (x, y).
     */
    public void drawPoint(double x, double y, Color color) {
        panel.addCommand(g -> {
            g.setColor(color);
            g.fillOval((int) x - 4, (int) y - 4, 8, 8);
        });
        refresh();
    }

    /**
     * Zeichnet eine Linie von (x1, y1) nach (x2, y2).
     */
    public void drawLine(double x1, double y1, double x2, double y2, Color color) {
        panel.addCommand(g -> {
            g.setColor(color);
            g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        });
        refresh();
    }

    /**
     * Beschriftet eine Position mit einem Text.
     */
    public void drawLabel(double x, double y, String text, Color color) {
        panel.addCommand(g -> {
            g.setColor(color);
            g.drawString(text, (int) x + 5, (int) y - 5);
        });
        refresh();
    }

    /**
     * Zeichnet ein Rechteck (z.B. für KD-Baum-Regionen).
     */
    public void drawRect(double x, double y, double width, double height, Color color) {
        panel.addCommand(g -> {
            g.setColor(color);
            g.drawRect((int) x, (int) y, (int) width, (int) height);
        });
        refresh();
    }

    /**
     * Löscht die gesamte Zeichenfläche (Hintergrundbild bleibt erhalten).
     */
    public void clear() {
        panel.clearCommands();
        refresh();
    }

    private void refresh() {
        SwingUtilities.invokeLater(panel::repaint);
    }
    
    // ----------------------------------------------------
    // Methoden für Extended überarbeitet
    // ----------------------------------------------------
    /**
     * Letzte Zeichenaktion rückgängig
     */
    public void undoLast() {
    	panel.commands.removeLast();
    	refresh();
    }
    
    /**
     * Zeichnet einen Punkt 
     */
    public void drawPoint(Point a, Color color) {
    	drawPoint(a.x, a.y, color);
    }
    
    /**
     * Zeichnet eine Linie von (x1, y1) nach (x2, y2).
     */
    public void drawLine(Point a, Point b, Color color) {
    	drawLine(a.x,a.y,b.x,b.y,color);
    }

    /**
     * Beschriftet eine Position mit einem Text.
     */
    public void drawLabel(Point p, String text, Color color) {
    	drawLabel(p.x, p.y, text, color);
    }

    /**
     * Zeichnet ein Rechteck (z.B. für KD-Baum-Regionen).
     */
    public void drawRect(Rect r, Color color) {
    	drawRect(r.x, r.y, r.width, r.height, color);
    }
}