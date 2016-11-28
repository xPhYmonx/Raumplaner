import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;


/**
 * Ein Stuhl, der manipuliert werden kann und sich selbst auf einer Leinwand zeichnet.
 * 
 * @author philippe simon
 * @version 2.2  (aug 07)
 */
public class Tisch_eckig extends Möbel
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
    public Tisch_eckig() {
        xPosition = 160;
        yPosition = 60;
        farbe = "red";
        orientierung = 0;
        istSichtbar = false;
        breite = 120;
        tiefe  = 80;
    }
    
    /**
     * Berechnet das zu zeichnende Shape anhand der gegebenen Daten
     * [ Zum Anzeigen der Attributwerte über Inspect muss hier die Sichtbarkeit 
     *  auf public gesetzt werden. ]
     */
    private Shape gibAktuelleFigur() {
        // einen GeneralPath definieren
        GeneralPath Tisch_eckig = new GeneralPath();
        Tisch_eckig.moveTo(0 , 0);
        Tisch_eckig.lineTo(breite, 0);
        Tisch_eckig.lineTo(breite, tiefe);
        Tisch_eckig.lineTo(-breite, tiefe);
        Tisch_eckig.lineTo(-breite, 0);
        Tisch_eckig.lineTo(0 , 0);
        // Das ist die Umrandung. Das Stuhl bekommt noch eine Lehne:   
        // transformieren:
        AffineTransform t = new AffineTransform();
        t.translate(xPosition, yPosition);
        Rectangle2D umriss = Tisch_eckig.getBounds2D();
        t.rotate(Math.toRadians(orientierung),umriss.getX()+umriss.getWidth()/2,umriss.getY()+umriss.getHeight()/2);
        return  t.createTransformedShape(Tisch_eckig);
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

