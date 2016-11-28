import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
/**
 * Ein Stuhl, der manipuliert werden kann und sich selbst auf einer Leinwand zeichnet.
 * 
 * @author philippe simon
 * @version 2.2  (aug 07)
 */
public class badewanne extends Möbel
{
    private int xPosition;
    private int yPosition;
    private int orientierung;
    private String farbe;
    private boolean istSichtbar;
    private int breite;
    private int tiefe;


    /**
     * Erzeuge einen neuen Stuhl mit einer Standardfarbe und Standardgroesse
     * an einer Standardposition. (Standardkonstruktor)
     */
    public badewanne() {
        xPosition = 160;
        yPosition = 80;
        farbe = "blau";
        orientierung = 0;
        istSichtbar = false;
        breite = 40;
        tiefe  = 40;
    }
    
    /**
     * Berechnet das zu zeichnende Shape anhand der gegebenen Daten
     * [ Zum Anzeigen der Attributwerte über Inspect muss hier die Sichtbarkeit 
     *  auf public gesetzt werden. ]
     */
    private Shape gibAktuelleFigur() {
        // einen GeneralPath definieren
        GeneralPath badewanne = new GeneralPath();
        Rectangle2D umriss = new Rectangle2D.Double (0 , 0, breite, tiefe);
        badewanne.append (umriss, false) ;
        Line2D obererRand = new Line2D.Double(0.1*tiefe, 0.1*tiefe, breite - 0.5*tiefe,0.1*tiefe);
        Line2D linkerRand = new Line2D.Double(0.1*tiefe, 0.1*tiefe,0.1*tiefe,0.9*tiefe);
        Line2D untererRand = new Line2D.Double(0.1*tiefe, 0.9*tiefe, breite - 0.5*tiefe,0.9*tiefe);
        Arc2D bogen = new Arc2D.Double (breite - 0.9*tiefe, 0.1*tiefe, 0.8*tiefe, 0.8*tiefe, 270, 180, Arc2D.OPEN);
        badewanne.append(obererRand, false);
        badewanne.append(linkerRand, false);
        badewanne.append(untererRand, false);
        badewanne.append(bogen, false);
        Ellipse2D ablauf = new Ellipse2D.Double (0.1*breite, 0.5*tiefe-2, 4, 4);
        badewanne.append (ablauf, false);
        return  badewanne;
    }
    
    /**
     * Drehe auf den angegebenen Winkel
     */
    public void dreheAuf(int neuerWinkel) {
        loesche();
        orientierung = neuerWinkel;
        zeichne();
    }
    
    /**
     * Bewege dieses Objekt horizontal um 'entfernung' Bildschirmpunkte.
     */
    public void bewegeHorizontal(int entfernung) {
        loesche();
        xPosition += entfernung;
        zeichne();
    }
    
    /**
     * Bewege dieses objekt vertikal um 'entfernung' Bildschirmpunkte.
     */
    public void bewegeVertikal(int entfernung) {
        loesche();
        yPosition += entfernung;
        zeichne();
    }
    
    
    /**
     * Aendere die Farbe dieses Objektes in 'neueFarbe'.
     * Gueltige Angaben sind "rot", "gelb", "blau", "gruen",
     * "lila" und "schwarz".
     */
    public void aendereFarbe(String neueFarbe) {
        loesche();
        farbe = neueFarbe;
        zeichne();
    }
    
    /**
     * Zeichne dieses Objekt mit seinen aktuellen Werten auf den Bildschirm.
     */
    protected void zeichne() {
        if (istSichtbar) {
            Shape figur = gibAktuelleFigur();
            Leinwand leinwand = Leinwand.gibLeinwand();
            leinwand.zeichne (
              this,           // leinwand kennt das Objekt
              farbe,          // definiert seine Zeichenfarbe
              figur);         // definiert seinen grafischen Aspekt
            leinwand.warte(10);
        }
    }
    
   
}
