import java.awt.Shape;
/**
 * Write a description of class Möbel here.
 * 
 * @author philippe s. 
 * @version (a version number or a date)
 */
public abstract class Möbel
{
    // instance variables - replace the example below with your own
    private boolean istSichtbar;
    private int xPosition;
    private int yPosition;
    private int orientierung;
    private String farbe;
    private int breite;
    private int tiefe;
     /**
     * Mache dieses Objekt sichtbar. Wenn es bereits sichtbar ist, tue nichts.
     */
  
    
    /**
     * Mache dieses Objekt unsichtbar. Wenn es bereits unsichtbar ist, tue nichts.
     */
    public void verberge() {
        loesche(); // "tue nichts" wird in loesche() abgefangen.
        istSichtbar = false;
    }
    
    /**
     * Zeichne dieses Objekt mit seinen aktuellen Werten auf den Bildschirm.
     */
    protected void zeichne(Shape shape)
    	{if (istSichtbar) {
            Shape figur = shape;
            Leinwand leinwand = Leinwand.gibLeinwand();
            leinwand.zeichne (
              this,           // leinwand kennt das Objekt
              farbe,          // definiert seine Zeichenfarbe
              figur);         // definiert seinen grafischen Aspekt
            leinwand.warte(10);
        }}
        
    
    
    /**
     * Loesche dieses Objekt vom Bildschirm.
     */
    protected void loesche() {
        if (istSichtbar){
            Leinwand leinwand = Leinwand.gibLeinwand();
            leinwand.entferne(this);
        }
    }
}
